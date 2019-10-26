package com.nexaas.challenge.domain.core

/**
 * Base interface for interactors to force an execute method
 * with a generic response.
 * If the Interactor should've not return anything, the generic type
 * should be a Kotlin::Unit.
 */
interface Interactor<T> {
    fun execute(): T
}