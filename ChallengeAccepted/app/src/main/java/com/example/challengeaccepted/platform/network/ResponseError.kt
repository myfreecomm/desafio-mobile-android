package com.example.challengeaccepted.platform.network

import java.io.Serializable

data class ResponseError(var _errors: Any, var message: String? = null) : Serializable