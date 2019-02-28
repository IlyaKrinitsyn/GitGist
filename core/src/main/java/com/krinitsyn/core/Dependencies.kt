package com.krinitsyn.core

import com.krinitsyn.utils.logger.Logger
import com.krinitsyn.utils.schedulers.Schedulers

data class Dependencies(
    val repository: Repository,
    val schedulers: Schedulers,
    val logger: Logger
)

