package br.com.nexaas.domain.usecase.cart

import br.com.nexaas.domain.entity.CartModel
import br.com.nexaas.domain.repository.ICartRepository

class GetCartUseCaseImpl(private val repository: ICartRepository) : IGetCartUseCase {
    override suspend fun execute(): List<CartModel> {
        return repository.getCart()
    }
}