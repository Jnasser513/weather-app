package com.jnasser.core.domain.extensions

import com.jnasser.core.domain.constants.DefaultValues

fun Boolean?.textOrAlternative(text: String, alternative: String = DefaultValues.EMPTY_STRING) =
    if (this != true) alternative
    else text