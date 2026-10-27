package app.yokan.media

import android.net.Uri
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DataSpec
import androidx.media3.datasource.TransferListener

/**
 * Añade parámetros de query a todas las peticiones dirigidas a los hosts indicados.
 *
 * Algunos CDN (el de UPNShare / `uns.bio`) protegen el manifest, los playlists de variante y
 * cada segmento con un token de acceso (`k`/`kx`). Los playlists referencian los segmentos con
 * URLs **relativas**, así que el reproductor las resuelve contra la URL base y el query original
 * se pierde: el CDN responde `403` y la reproducción no arranca (o se queda "cargando" hasta que
 * expira el timeout).
 *
 * Este DataSource reinyecta los parámetros en cada petición, sustituyendo los que ya vinieran
 * en la URL para no duplicarlos.
 */
class QueryParamDataSourceFactory(
    private val delegate: DataSource.Factory,
    private val params: Map<String, String>,
    private val hosts: Set<String>,
) : DataSource.Factory {

    override fun createDataSource(): DataSource =
        QueryParamDataSource(delegate.createDataSource(), params, hosts)
}

private class QueryParamDataSource(
    private val delegate: DataSource,
    private val params: Map<String, String>,
    private val hosts: Set<String>,
) : DataSource {

    private var openedUri: Uri? = null

    override fun addTransferListener(transferListener: TransferListener) {
        delegate.addTransferListener(transferListener)
    }

    override fun open(dataSpec: DataSpec): Long {
        val effectiveSpec = dataSpec.withQueryParams(params, hosts)
        openedUri = effectiveSpec.uri
        return delegate.open(effectiveSpec)
    }

    override fun read(buffer: ByteArray, offset: Int, length: Int): Int =
        delegate.read(buffer, offset, length)

    override fun getUri(): Uri = openedUri ?: delegate.getUri()

    override fun getResponseHeaders(): Map<String, List<String>> = delegate.getResponseHeaders()

    override fun close() = delegate.close()
}

/**
 * Devuelve el [DataSpec] con los parámetros añadidos a la URI, sin tocar el resto de campos
 * (posición, longitud, cabeceras...). Si la URI no apunta a ninguno de los [hosts] o ya trae
 * esos parámetros, se devuelve intacto.
 */
private fun DataSpec.withQueryParams(params: Map<String, String>, hosts: Set<String>): DataSpec {
    if (params.isEmpty() || hosts.isEmpty()) return this
    val host = uri.host ?: return this
    if (host !in hosts) return this
    val missing = params.filter { (key, value) -> uri.getQueryParameter(key) != value }
    if (missing.isEmpty()) return this

    val builder = uri.buildUpon()
    missing.forEach { (key, value) ->
        if (uri.getQueryParameter(key) != null) {
            builder.setQueryParameter(key, value)
        } else {
            builder.appendQueryParameter(key, value)
        }
    }
    return withUri(builder.build())
}
