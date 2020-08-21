package com.example.challengeaccepted.platform.base

abstract class BaseMapper<DATA, MAPPER> {
    abstract fun map(data: DATA?): MAPPER
}
