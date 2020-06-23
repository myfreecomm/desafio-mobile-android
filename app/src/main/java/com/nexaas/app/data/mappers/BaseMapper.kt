package com.nexaas.app.data.mappers

abstract class BaseMapper<IN, OUT> {

    /**
     * Maps a single [entity]
     */
    abstract fun transform(entity: IN): OUT

    /**
     * Maps a list of [entities].
     */
    fun transform(entities: List<IN>?): List<OUT> {
        return entities?.let { list ->
            list.map(::transform)
        } ?: emptyList()
    }
}
