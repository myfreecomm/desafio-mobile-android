package com.example.desafioandroid.domain.shared

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class UseCase<out Type, in Params> where Type : Any {

    protected abstract suspend fun run (params: Params) : Either<Throwable,Type>

    suspend fun execute(params: Params) : Either<Throwable,Type>{
        return withContext(Dispatchers.IO){
            run(params)
        }
    }

}