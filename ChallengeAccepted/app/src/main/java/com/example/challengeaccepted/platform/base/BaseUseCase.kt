package com.example.challengeaccepted.platform.base

import com.example.challengeaccepted.platform.data.network.Resource
import org.koin.core.KoinComponent

open class BaseUseCase : KoinComponent {

    protected fun <DATA, MAPPER> mapResource(
        resourceData: Resource<DATA>,
        mapper: BaseMapper<DATA, MAPPER>
    ): Resource<MAPPER> {
        return Resource(
            resourceData.state,
            mapper.map(resourceData.data),
            resourceData.message
        )
    }
}