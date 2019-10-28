package com.nexaas.challenge.domain.core

interface DomainMappable<DomainClass: DomainModel> {
    fun asDomainObject() : DomainClass
}