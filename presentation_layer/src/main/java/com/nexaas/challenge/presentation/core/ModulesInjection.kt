package com.nexaas.challenge.presentation.core

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.dsl.module

/* Objects common to the entire application */
val commonModule = module {
    single<Gson> { GsonBuilder().setPrettyPrinting().serializeNulls().create() }
}

/**
 * Data Module
 */
val dataModule = module {
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

