package com.hotmail.fignunes.desafio_mobile.repository

import android.content.Context
import com.hotmail.fignunes.desafio_mobile.repository.remote.RemoteFactory
import com.hotmail.fignunes.desafio_mobile.repository.remote.RemoteRepository

class Repository(applicationContext: Context) : RepositoryFactory {

    override val remote: RemoteFactory = RemoteRepository()
}