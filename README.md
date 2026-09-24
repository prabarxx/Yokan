# Yokan 🎬

> Aplicación moderna de anime para Android con streaming de torrents P2P de baja latencia y catálogo impulsado por AniList.

---

## ✨ Características

- 🧠 **Cerebro AniList**: Catálogo global, tendencias en emisión, animes más populares y buscador rápido usando directamente la API GraphQL oficial de AniList (`https://graphql.anilist.co`).
- ⚡ **Streaming P2P de Alta Velocidad**: Motor BitTorrent optimizado (`libtorrent4j`) con pre-buffer de 32 MB, asignación de urgencia inmediata (`deadline = 0`) para reproducción fluida sin esperas.
- 🇪🇸 **Filtro de Subtítulos en Español y Latino**: Integración con búsqueda RSS de Nyaa con reconocimiento inteligente de versiones en español y multilenguaje (SubsPlease, Erai-raws, etc.), priorizando siempre la mejor calidad y mayor número de sembradores.
- 🎥 **Reproductor Avanzado**: Basado en Jetpack Media3 / ExoPlayer con soporte para subtítulos ASS/SSA y controles táctiles de volumen, brillo y seek.
- 🎨 **Interfaz Compose Limpia**: Diseño moderno con Material 3, tema oscuro/claro y adaptativo para teléfonos panorámicos.

---

## 🏗 Arquitectura del Proyecto

```
Yokan/
├── app/
│   ├── android/                # Aplicación Android (MainActivity, Compose UI, Koin DI)
│   └── shared/
│       ├── ui-foundation/      # Tema visual Material 3 y componentes base
│       ├── ui-adaptive/        # Adaptabilidad de pantalla y orientación
│       ├── ui-mediaselect/     # Selector de fuentes
│       └── video-player/       # Reproductor Media3 + subtítulos + puente torrent
├── anilist/                    # Cliente GraphQL limpio de AniList (Ktor)
├── datasource/
│   ├── datasource-api/         # Modelos de Media, Episodios e Idiomas
│   └── nyaa/                   # Buscador RSS de Nyaa con filtro de subtítulos en español
└── torrent/
    ├── torrent-api/            # Contratos e interfaces del motor de torrents
    └── anitorrent/             # Motor libtorrent4j con pre-buffer de 32MB y servidor HTTP
```

---

## 🚀 Compilación y Descarga de APKs

El proyecto está configurado para compilarse y empaquetarse automáticamente a través de **GitHub Actions** en cada commit a la rama principal (`main`) o al publicar un release con tag.

Los APKs generados se publican directamente en la sección de **Releases** de este repositorio:
- **`yokan-arm64-v8a.apk`**: APK optimizado para procesadores ARM de 64 bits (la mayoría de dispositivos Android actuales).
- **`yokan-universal.apk`**: APK universal compatible con cualquier procesador.

---

## 📜 Licencia

Distribuido bajo la licencia GNU Affero General Public License v3.0 (AGPLv3).
