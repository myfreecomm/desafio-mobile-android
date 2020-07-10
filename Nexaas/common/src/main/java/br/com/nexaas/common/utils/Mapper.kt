package br.com.nexaas.common.utils

interface Mapper<in T, out R> {
    fun transform(from: T) : R
    fun transform(from: List<T>) : List<R>
}