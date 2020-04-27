package com.hotmail.fignunes.desafio_mobile.repository.remote

import com.hotmail.fignunes.desafio_mobile.repository.remote.product.resources.RemoteProductResources

interface RemoteFactory {
    val product: RemoteProductResources
}