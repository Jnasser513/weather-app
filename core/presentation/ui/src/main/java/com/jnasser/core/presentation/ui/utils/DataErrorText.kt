package com.jnasser.core.presentation.ui.utils

import com.jnasser.core.domain.util.error_handler.DataError
import com.jnasser.core.presentation.ui.R
import com.jnasser.core.presentation.ui.UiText

fun DataError.asUiText(): UiText {
    return when(this) {
        DataError.Local.DISK_FULL -> UiText.StringResource(
            R.string.error_disk_full
        )
        DataError.Network.REQUEST_TIMEOUT -> UiText.StringResource(
            R.string.error_request_timeout
        )
        DataError.Network.TOO_MANY_REQUEST -> UiText.StringResource(
            R.string.error_too_many_request
        )
        DataError.Network.NO_INTERNET -> UiText.StringResource(
            R.string.error_no_internet
        )
        DataError.Network.SERVER_ERROR -> UiText.StringResource(
            R.string.error_server_error
        )
        DataError.Network.SERIALIZATION -> UiText.StringResource(
            R.string.error_error_serialization
        )
        else -> UiText.StringResource(
            R.string.error_unknown
        )
    }
}