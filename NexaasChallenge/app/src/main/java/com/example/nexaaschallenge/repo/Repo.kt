package com.example.nexaaschallenge.repo

import com.example.nexaaschallenge.repo.retrofit.Api
import com.example.nexaaschallenge.repo.retrofit.Resource
import com.example.nexaaschallenge.repo.retrofit.ResponseHandler

class Repo(
    private val api: Api,
    private val responseHandler: ResponseHandler
) {
    suspend fun getCart(): Resource<List<ItemCart>> {
        return try {
            return responseHandler.handleSuccess(api.getCart())
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}