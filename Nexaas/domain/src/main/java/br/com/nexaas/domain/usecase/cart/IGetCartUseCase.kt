package br.com.nexaas.domain.usecase.cart

import br.com.nexaas.domain.entity.CartModel
import br.com.nexaas.domain.usecase.core.UseCase
import kotlinx.coroutines.flow.Flow

interface IGetCartUseCase : UseCase.WithoutParameter<Flow<List<CartModel>>>