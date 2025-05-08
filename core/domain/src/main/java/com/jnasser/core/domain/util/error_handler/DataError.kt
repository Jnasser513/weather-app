package com.jnasser.core.domain.util.error_handler

sealed interface DataError: Error {

    enum class Network: DataError {
        REQUEST_TIMEOUT,
        UNAUTHORIZED,
        NO_INTERNET,
        SERVER_ERROR,
        SERIALIZATION,
        NOT_FOUND,
        TOO_MANY_REQUEST,
        UNKNOWN
    }

    enum class Local: DataError {
        DISK_FULL
    }
}