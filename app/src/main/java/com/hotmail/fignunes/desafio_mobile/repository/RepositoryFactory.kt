package com.hotmail.fignunes.desafio_mobile.repository

import com.hotmail.fignunes.desafio_mobile.repository.remote.RemoteFactory

interface RepositoryFactory {
    val remote: RemoteFactory
}