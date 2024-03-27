package xyz.teamgravity.errorhandlinglayers.domain.error

sealed interface DataError : Error {
    enum class Network : DataError {
        RequestTimeout,
        TooManyRequests,
        NoInternet,
        PayloadTooLarge,
        ServerError,
        Serialization,
        Unknown;
    }

    enum class Local : DataError {
        DiskFull;
    }
}