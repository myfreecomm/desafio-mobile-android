package br.com.desafio.nexaas.data

class ProductRepository(private val service: ProductService, private val dao: ProductDAO) {

    suspend fun getAll(): List<Product> {
        return try {
            val products = service.getAll()
            dao.saveAll(products)
            dao.getAll()
        } catch (e: Exception) {
            dao.getAll()
        }
    }

}