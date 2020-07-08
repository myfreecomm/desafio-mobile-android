package br.com.nexaas.domain.usecase.cart

import br.com.nexaas.domain.entity.CartModel
import br.com.nexaas.domain.usecase.core.UseCase

interface IGetCartUseCase : UseCase.WithoutParameter<List<CartModel>>