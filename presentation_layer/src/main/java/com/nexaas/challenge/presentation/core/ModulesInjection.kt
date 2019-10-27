package com.nexaas.challenge.presentation.core

import com.nexaas.challenge.data.remote.ApiRepositoryImpl
import com.nexaas.challenge.domain.repository.ApiRepository
import org.koin.dsl.module

/* Objects common to the entire application */
val commonModule = module {
}

/**
 * Data Module
 */
val dataModule = module {
    single<ApiRepository> { ApiRepositoryImpl("https://raw.githubusercontent.com") }
}

/**
 * Domain Module
 */
val domainModule = module {
}

/**
 * Presentation Module
 */
val presentationModule = module {
}

