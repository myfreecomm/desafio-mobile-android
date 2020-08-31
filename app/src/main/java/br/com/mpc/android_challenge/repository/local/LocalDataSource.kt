package br.com.mpc.android_challenge.repository.local

import br.com.mpc.android_challenge.db.AppDatabase

class LocalDataSource(private val db: AppDatabase) {

    fun getItems() = db.nexaasDao().getItems()
}