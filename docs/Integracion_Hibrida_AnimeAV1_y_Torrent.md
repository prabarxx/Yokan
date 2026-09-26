# Arquitectura Híbrida: Torrent + Web Streaming (AnimeAV1) — Yokan

## 1. Contexto y Decisión Arquitectónica

La transición de un cliente exclusivamente P2P a una **arquitectura híbrida (Torrent + Web Streaming)** —siguiendo el modelo probado de proyectos como Cloudstream y Aniyomi— optimiza la disponibilidad y resuelve el espectro completo de casos de uso:

* **Vía Torrent (Erai-raws / Nyaa):** Orientada a usuarios que priorizan máxima fidelidad visual (1080p sin recomprimir, múltiples pistas de audio/subtítulos) en animes en emisión o series populares con alta densidad de *seeders*.
* **Vía Web Streaming (AnimeAV1):** Orientada a resolver series antiguas o de nicho con escasez o ausencia de semillas, conexiones de datos móviles reducidas (archivos ligeros entre 150 y 300 MB) y reproducción inmediata sin latencia de descubrimiento de pares (*peers*).

---

## 2. Análisis Interno de AnimeAV1

Para diseñar el *scraper* y los módulos de extracción, se identifican tres componentes estructurales del sitio:

### 2.1. Estructura de URLs
* **Detalle del anime:** `https://animeav1.com/media/{slug}`
* **Episodio individual:** `https://animeav1.com/media/{slug}/{episodio}`  
  *(Ejemplos: `/media/shingeki-no-kyojin/1`, `/media/fullmetal-alchemist/20`)*

### 2.2. Proveedores de Video Embebidos
AnimeAV1 no aloja video directamente; incrusta reproductores mediante `iframe` de proveedores externos y servidores HLS directos:
* HLS Directo (AV1 / H.264)
* UPNShare
* Voe
* Mega
* YourUpload
* MP4Upload
* VidHide
* StreamTape

### 2.3. Pistas de Audio y Modalidad
Presenta pestañas separadas según la pista de audio:
* **SUB:** Audio japonés con subtítulos fijos (*hardsub*) en español.
* **DUB:** Doblaje al español (Latino o Castellano).

---

## 3. Plan de Implementación en Kotlin

### Paso 1: Mapeo de AniList a AnimeAV1 (Slug Resolver)
El catálogo de AniList provee títulos (`romaji`, `english`), pero AnimeAV1 requiere el slug exacto de la URL:

#### Opción A: Consulta al buscador interno (Recomendada para precisión)
Realizar una petición GET al buscador de AnimeAV1 y extraer el enlace del primer resultado coincidente mediante JSoup:
* Endpoint: `https://animeav1.com/catalogo?search={nombre}`

#### Opción B: Generación determinista de Slug
Para resolución inmediata sin peticiones previas:

```kotlin
fun titleToSlug(title: String): String {
    return title.lowercase()
        .replace(Regex("[^a-z0-9\\s-]"), "")
        .trim()
        .replace(Regex("\\s+"), "-")
}
// Ejemplo: "Attack on Titan" -> "attack-on-titan"
```

---

### Paso 2: Scraping del Episodio y Extracción de Iframes
Obtención del documento HTML del episodio para parsear los contenedores de los reproductores:

```kotlin
import org.jsoup.Jsoup

// Petición al episodio específico
val doc = Jsoup.connect("https://animeav1.com/media/$slug/$episode")
    .userAgent("Mozilla/5.0 (Linux; Android 10) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36")
    .get()

// Selector de servidores embebidos / iframes
val serverElements = doc.select(".server-item, .play-video, iframe")
```

---

### Paso 3: Extractores de Servidores (*Video Extractors*)
La URL del `iframe` incrustado (ej. `https://streamtape.com/e/xxxx` o `https://voe.sx/e/xxxx`) requiere resolverse hacia la URL final de streaming (`.mp4` o `.m3u8`):

* **Reutilización de código abierto:** Integrar la lógica modular de extractores existentes de la comunidad (proyectos Cloudstream3 / Aniyomi), los cuales ya gestionan tokens, JavaScript ofuscado y cabeceras dinámicas:
  * `StreamTapeExtractor.kt`
  * `VoeExtractor.kt`
  * `Mp4UploadExtractor.kt`
  * `VidHideExtractor.kt`
* Cada extractor recibe la URL del embed y devuelve la URL limpia del flujo junto con las cabeceras HTTP necesarias para la petición.

---

### Paso 4: Integración en ExoPlayer (Headers y HLS)
Muchos servidores aplican protección contra *hotlinking* y descartan peticiones que no provengan del dominio correspondiente:

```kotlin
import androidx.media3.common.MediaItem
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory

// Configuración de fábrica de origen de datos HTTP con cabeceras requeridas
val httpDataSourceFactory = DefaultHttpDataSource.Factory()
    .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
    .setDefaultRequestProperties(
        mapOf(
            "Referer" to "https://animeav1.com/", // o el dominio del hoster correspondiente
            "Origin" to "https://animeav1.com"
        )
    )

val mediaSourceFactory = DefaultMediaSourceFactory(httpDataSourceFactory)
val player = ExoPlayer.Builder(context)
    .setMediaSourceFactory(mediaSourceFactory)
    .build()

// Reproducción de enlace directo (HLS .m3u8 o MP4 progresivo)
val mediaItem = MediaItem.fromUri(directStreamUrl)
player.setMediaItem(mediaItem)
player.prepare()
player.play()
```

---

## 4. Gestión y Decodificación del Códec AV1

AnimeAV1 utiliza compresión AV1 principalmente en su servidor nativo HLS para ofrecer alta fidelidad con bajo consumo de ancho de banda:

1. **Dispositivos modernos (Android 12+ / SoCs recientes):** Cuentan con soporte por hardware nativo para decodificación AV1, garantizando bajo consumo de batería y fluidez de fotogramas.
2. **Dispositivos heredados:** Para evitar excepciones del reproductor (como `NO_EXCEEDS_CAPABILITIES`), se debe incluir el decodificador por software de Media3 en `build.gradle.kts`:

```kotlin
dependencies {
    implementation("androidx.media3:media3-decoder-libgav1:1.X.X")
}
```

3. **Alternativa en caso de saturación de CPU:** Si el hardware no decodifica AV1 con soltura, el usuario puede seleccionar opciones como MP4Upload o Voe, que entregan streams estándar en H.264 (AVC).

---

## 5. Diseño de Interfaz de Selección de Fuentes

En la vista de selección de fuentes previa a la reproducción, se estructuran las opciones en dos categorías explícitas:

```text
[ Fuentes Web / Streaming Rápido ]
 ├── [AnimeAV1] Servidor HLS (AV1 1080p - Carga Inmediata)
 ├── [AnimeAV1] Voe (720p - Sub Español)
 └── [AnimeAV1] MP4Upload (1080p - Sub Español)

[ Fuentes Torrent / Calidad Máxima ]
 ├── [Erai-raws] 1080p [Multi-Sub] (184 Seeds)
 └── [LostYears] 1080p [Dual Audio] (45 Seeds)
```

Esta separación garantiza claridad operativa para el usuario final y dota al reproductor de una vía de escape instantánea ante caídas de red P2P o falta de almacenamiento local.