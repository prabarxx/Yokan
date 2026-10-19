package app.yokan.datasource.animeav1

import java.util.concurrent.ConcurrentHashMap

/**
 * Caché en memoria con expiración (TTL) y tamaño máximo.
 *
 * Necesaria para las respuestas de AnimeAV1: las URLs de stream HLS llevan tokens que caducan,
 * así que servir una entrada antigua provoca fallos de reproducción. Al superar [maxSize] se
 * eliminan primero las entradas caducadas y, si aún no hay hueco, las más próximas a expirar.
 *
 * @param clock inyectable para poder testear la expiración sin esperas reales.
 */
class TtlCache<K : Any, V : Any>(
    private val ttlMillis: Long,
    private val maxSize: Int = 256,
    private val clock: () -> Long = System::currentTimeMillis,
) {
    private class Entry<V>(val value: V, val expiresAt: Long)

    private val map = ConcurrentHashMap<K, Entry<V>>()

    operator fun get(key: K): V? {
        val entry = map[key] ?: return null
        if (entry.expiresAt <= clock()) {
            map.remove(key, entry)
            return null
        }
        return entry.value
    }

    fun put(key: K, value: V) {
        if (map.size >= maxSize) {
            evict()
        }
        map[key] = Entry(value, clock() + ttlMillis)
    }

    operator fun set(key: K, value: V) = put(key, value)

    /** Elimina las entradas cuyo valor cumpla [predicate]. Devuelve cuántas se eliminaron. */
    fun removeIf(predicate: (V) -> Boolean): Int {
        var removed = 0
        val iterator = map.entries.iterator()
        while (iterator.hasNext()) {
            if (predicate(iterator.next().value.value)) {
                iterator.remove()
                removed++
            }
        }
        return removed
    }

    private fun evict() {
        val now = clock()
        val iterator = map.entries.iterator()
        while (iterator.hasNext()) {
            if (iterator.next().value.expiresAt <= now) {
                iterator.remove()
            }
        }
        if (map.size >= maxSize) {
            map.entries.sortedBy { it.value.expiresAt }
                .take((maxSize / 4).coerceAtLeast(1))
                .forEach { map.remove(it.key, it.value) }
        }
    }
}
