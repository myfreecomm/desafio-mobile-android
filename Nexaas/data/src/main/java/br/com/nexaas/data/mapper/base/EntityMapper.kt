package br.com.nexaas.data.mapper.base

interface EntityMapper<T, Entity> {
    fun toEntity(from: T) : Entity
    fun toEntity(from: List<T>) : List<Entity>
    fun reverseEntity(from: Entity): T
    fun reverseEntity(from: List<Entity>): List<T>
}