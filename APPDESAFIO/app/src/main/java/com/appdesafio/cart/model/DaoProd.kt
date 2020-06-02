package com.appdesafio.cart.model
import io.reactivex.Observable
import androidx.room.*

@Dao
interface DaoProd {
        @Transaction
        fun refatorarBD(produtos: List<Produto>) {
            delete()
            save(produtos)
        }
        @Query("DELETE"
                    + " FROM "
                    + " produtos"
        )
        fun delete()
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun save(produtos: List<Produto>)
        @Query(
            "SELECT"
                    + " *"
                    + " FROM"
                    + " produtos "
                    + " WHERE ID=:id"
        )
        fun getProdutoById(id: String): Observable<Produto>

        @Query(
            "SELECT"
                    + " *"
                    + " FROM"
                    + " produtos"
        )
        fun getProdutos(): Observable<List<Produto>>
}