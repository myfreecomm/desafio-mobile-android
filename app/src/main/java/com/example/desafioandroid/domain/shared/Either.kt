package com.example.desafioandroid.domain.shared

typealias ErrorEvent<L> = Either.Left<L>
typealias SuccesEvent<R> = Either.Right<R>

sealed class Either<out L,out R> {

    data class Left<out L>(val errorLeft : L) : Either<L,Nothing>()

    data class Right<out R>(val successRight : R) : Either<Nothing,R>()

    val left : L? get() = (this as? Left<L>)?.errorLeft
    val right : R? get() = (this as? Right<R>)?.successRight

    fun either(errorFunction : (L) -> Any, successFunction : (R) -> Any) : Any =
        when (this) {
            is Left -> errorFunction(errorLeft)
            is Right -> successFunction(successRight)
    }

}