package com.example.challengeaccepted.platform.base

import com.example.challengeaccepted.platform.network.Network
import org.koin.core.KoinComponent
import org.koin.core.inject

open class BaseUseCase : KoinComponent{

    val network: Network by inject()
}