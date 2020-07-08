package br.com.nexaas.domain.usecase.core

interface UseCase {

    interface WithParameter<in P, out R> {
        suspend fun execute(params: P): R
    }

    interface WithoutParameter<out R> {
        suspend fun execute(): R
    }
}