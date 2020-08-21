package com.example.challengeaccepted.platform.data.network

import com.example.challengeaccepted.R
import com.example.challengeaccepted.platform.data.network.exception.ConnectionTimeoutException
import com.example.challengeaccepted.platform.data.network.exception.GenericException
import com.example.challengeaccepted.platform.data.network.exception.NoConnectionException
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import retrofit2.Response
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

inline fun <CALL> callResourceDataFromRemote(
    crossinline remote: suspend () -> Response<CALL>,
    crossinline onFinish: () -> Unit = { }
) = flow {

    if (remote().isSuccessful) {
        val body = remote().body()
        emit(Resource.success(body))
    } else {
        emit(Resource.error(GenericException(R.string.error_generic)))
    }

}.catch {
    catch(it)
}.onCompletion {
    onFinish()
}

inline fun <reified REMOTE, LOCAL> callResourceDataWithCache(
    crossinline local: suspend () -> LOCAL,
    crossinline remote: suspend () -> Response<REMOTE>,
    crossinline shouldFetchFromRemote: (LOCAL?) -> Boolean = { true },
    crossinline onRemoteResource: (REMOTE?) -> Resource<LOCAL>,
    crossinline onFinish: () -> Unit = { }
) = flow {

    val localData: LOCAL? = local()
    val fromRemote = shouldFetchFromRemote(localData)

    if (fromRemote) {
        if (remote().isSuccessful) {
            val remoteData = remote().body()
            emit(onRemoteResource(remoteData))
        } else {
            emit(Resource.error(GenericException(R.string.error_generic)))
        }
    } else {
        emit(Resource.success(localData))
    }

}.catch {
    catch(it)
}.onCompletion {
    onFinish()
}

suspend fun <CALL> FlowCollector<Resource<CALL>>.catch(it: Throwable) {
    when (it) {
        is UnknownHostException -> {
            emit(Resource.error(NoConnectionException(R.string.no_connection)))
        }
        is TimeoutException -> {
            emit(Resource.error(ConnectionTimeoutException(R.string.error_timeout)))
        }
        else -> {
            emit(Resource.error(GenericException(R.string.error_generic)))
        }
    }
}