package com.nexaas.gradleconfig

object Libs {

    object Tools {
        const val gradle: String = "com.android.tools.build:gradle:" + Versions.gradle
    }

    object Plugins {
        const val kotlin: String = "org.jetbrains.kotlin:kotlin-gradle-plugin:" + Versions.kotlin

        /**
         * https://github.com/pinterest/ktlint
         */
        const val ktlint: String = "com.pinterest:ktlint:" + Versions.ktlint

        /**
         * https://github.com/ben-manes/gradle-versions-plugin
         */
        const val gradle_versions: String = "com.github.ben-manes:gradle-versions-plugin:" +
                Versions.gradle_versions

    }

    object Core {
        /**
         * https://kotlinlang.org/
         */
        const val kotlin_stdlib: String = "org.jetbrains.kotlin:kotlin-stdlib:" + Versions.kotlin

        const val kotlin_stdlib_jdk8: String =
                "org.jetbrains.kotlin:kotlin-stdlib-jdk8:" + Versions.kotlin

        const val lifecycle_extensions: String = "androidx.lifecycle:lifecycle-extensions:" +
                Versions.lifecycle_extensions

        /**
         * https://developer.android.com/jetpack/androidx
         */

        const val appcompat: String = "androidx.appcompat:appcompat:" + Versions.appcompat

        /**
         * https://github.com/Kotlin/kotlinx.coroutines
         */
        const val kotlinx_coroutines_android: String =
                "org.jetbrains.kotlinx:kotlinx-coroutines-android:" + Versions.kotlinx_coroutines

        const val kotlinx_coroutines_core: String =
                "org.jetbrains.kotlinx:kotlinx-coroutines-core:" +
                        Versions.kotlinx_coroutines

        /**
         * http://developer.android.com/tools/extras/support-library.html
         */
        const val core_ktx: String = "androidx.core:core-ktx:" + Versions.core_ktx

        /**
         * MultiDex
         */
        const val multidex: String = "com.android.support:multidex:" + Versions.multidex
    }

    object DI {
        /**
         * https://github.com/InsertKoinIO/koin
         */
        const val koin_android: String = "org.koin:koin-android:" + Versions.koin

        const val koin_androidx_viewmodel: String =
                "org.koin:koin-androidx-viewmodel:" + Versions.koin
    }

    object Data {
        /**
         * https://github.com/kittinunf/Result
         */
        const val result: String = "com.github.kittinunf.result:result:" + Versions.kittinunf_result

        const val result_coroutines: String = "com.github.kittinunf.result:result-coroutines:" +
                Versions.kittinunf_result

        /**
         * https://github.com/square/okhttp
         */
        const val okhttp: String = "com.squareup.okhttp3:okhttp:" + Versions.okhttp

        const val logging_interceptor: String = "com.squareup.okhttp3:logging-interceptor:" +
                Versions.okhttp

        /**
         * https://github.com/square/retrofit/
         */
        const val retrofit: String = "com.squareup.retrofit2:retrofit:" + Versions.retrofit

        const val converter_jackson: String = "com.squareup.retrofit2:converter-jackson:" +
                Versions.converter_jackson

        const val converter_gson: String = "com.squareup.retrofit2:converter-gson:" +
                Versions.converter_gson

        /**
         * http://facebook.github.io/stetho/
         */
        const val stetho: String = "com.facebook.stetho:stetho:" + Versions.stetho
        const val stetho_Okhttp3: String = "com.facebook.stetho:stetho-okhttp3:" + Versions.stetho


        /**
         * https://github.com/JakeWharton/retrofit2-kotlin-coroutines-adapter/
         */
        const val retrofit2_coroutines_adapter: String =
                "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:" +
                        Versions.retrofit2_coroutines_adapter
        
        const val room_runtine: String = "androidx.room:room-runtime:" + Versions.room
        const val room_compiler: String = "androidx.room:room-compiler:" + Versions.room
    }

    object UI {
        /**
         * https://github.com/bumptech/glide
         */
        const val glide: String = "com.github.bumptech.glide:glide:" + Versions.glide

        const val glide_annotations: String =
                "com.github.bumptech.glide:annotations:" + Versions.glide

        const val glide_compiler: String = "com.github.bumptech.glide:compiler:" + Versions.glide

        /**
         * AndroidX Components and Tools
         * https://developer.android.com/jetpack/androidx
         */
        const val fragment_ktx: String = "androidx.fragment:fragment-ktx:" + Versions.fragment_ktx

        const val recyclerview: String =
                "androidx.recyclerview:recyclerview:" + Versions.recyclerview

        const val constraintlayout: String = "androidx.constraintlayout:constraintlayout:" +
                Versions.constraintlayout

        /**
         * Material Design
         */
        const val material: String = "com.google.android.material:material:" + Versions.material

        /**
         * http://facebook.github.io/shimmer-android
         */
        const val shimmer: String = "com.facebook.shimmer:shimmer:" + Versions.shimmer

    }

    object Testing {
        /**
         * https://developer.android.com/testing
         */
        const val core_testing: String = "android.arch.core:core-testing:" + Versions.core_testing

        const val androidx_core: String = "androidx.test:core:" + Versions.androidx_test

        const val androidx_test_runner: String = "androidx.test:runner:" + Versions.androidx_test

        const val androidx_test_rules: String = "androidx.test:rules:" + Versions.androidx_test

        const val androidx_espresso_core: String = "androidx.test.espresso:espresso-core:" +
                Versions.espresso_core
        /**
         * https://github.com/agoda-com/Kakao
         */
        const val kakao: String = "com.agoda.kakao:kakao:" + Versions.kakao

        /**
         * http://junit.org
         */
        const val junit: String = "junit:junit:" + Versions.junit

        /**
         * http://mockk.io
         */
        const val mockk: String = "io.mockk:mockk:" + Versions.mockk

        /**
         * https://github.com/Kotlin/kotlinx.coroutines
         */
        const val kotlinx_coroutines_test: String =
                "org.jetbrains.kotlinx:kotlinx-coroutines-test:" +
                        Versions.kotlinx_coroutines_test

        /**
         * https://github.com/square/okhttp/tree/master/mockwebserver
         */
        const val mockwebserver: String =
                "com.squareup.okhttp3:mockwebserver:" + Versions.mockwebserver

        const val room_testing: String = "androidx.room:room-testing:" + Versions.room
    }

}