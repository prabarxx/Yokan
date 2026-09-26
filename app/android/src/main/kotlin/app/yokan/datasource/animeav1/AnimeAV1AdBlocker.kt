package app.yokan.datasource.animeav1

object AnimeAV1AdBlocker {
    private val BLOCKED_DOMAINS = setOf(
        "aphacicfable.com",
        "prahmnatured.com",
        "brigadedelegatesandbox.com",
        "gigglemagnetismunaired.com",
        "cacklegrievingtank.com",
        "attirecideryeah.com",
        "talpiddullity.com",
        "dopattaoutcant.com",
        "googletagmanager.com",
        "google-analytics.com",
        "yandex.ru",
        "sxlzbz.com",
        "popads.net",
        "adsterra.com",
        "doubleclick.net",
        "adservice",
        "popunder",
    )

    fun isAdOrTracker(url: String): Boolean {
        val lower = url.lowercase()
        return BLOCKED_DOMAINS.any { lower.contains(it) } ||
                lower.contains("/ads/") ||
                lower.contains("/ad/") ||
                lower.contains("popads") ||
                lower.contains("clicktag")
    }

    /**
     * Script de inyección para suprimir popups, ventanas emergentes y listeners publicitarios
     * en caso de renderizado webview.
     */
    val INJECTION_CLEANUP_SCRIPT = """
        (function() {
            window.open = function() { return null; };
            window.alert = function() {};
            window.confirm = function() { return false; };
            window.prompt = function() { return null; };
            // Eliminar overlays con z-index excesivo
            setInterval(function() {
                var els = document.querySelectorAll('div, a');
                for (var i = 0; i < els.length; i++) {
                    var z = window.getComputedStyle(els[i]).zIndex;
                    if (parseInt(z) > 100000) {
                        els[i].remove();
                    }
                }
            }, 500);
        })();
    """.trimIndent()
}
