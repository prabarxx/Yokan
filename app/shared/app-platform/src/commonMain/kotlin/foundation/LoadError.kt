package me.him188.ani.app.domain.foundation

sealed interface LoadError {
    data object NetworkError : LoadError
    data object NoResults : LoadError
    data object RateLimited : LoadError
    data object RequiresLogin : LoadError
    data object ServiceUnavailable : LoadError
    data class UnknownError(val throwable: Throwable) : LoadError
    data class RequestError(val localized: String) : LoadError

    companion object {
        fun fromException(throwable: Throwable): LoadError = UnknownError(throwable)
    }
}
