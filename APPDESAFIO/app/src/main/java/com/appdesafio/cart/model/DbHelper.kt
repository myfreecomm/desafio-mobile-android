package com.appdesafio.cart.model
import android.content.Context
import android.util.Log
import androidx.room.*
import java.io.IOException

@Database(
    entities = [Produto::class],
    version = 1
)
abstract class DbHelper : RoomDatabase(){
    abstract fun daoProd(): DaoProd
    companion object {
        fun create(context: Context): DbHelper {
            try{
                return Room.databaseBuilder(
                    context,
                    DbHelper::class.java,
                    "produtos"
                ).build()
            }catch (e: IOException) {
                Log.e("ERRO Dbhelper", "Erro ao salvar dados")
                throw RuntimeException(e)
            }
        }
    }
}