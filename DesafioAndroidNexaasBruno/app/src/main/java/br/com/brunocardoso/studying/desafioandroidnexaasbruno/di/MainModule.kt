package br.com.brunocardoso.studying.desafioandroidnexaasbruno.di

import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.model.Product
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.repository.ShoppingCartRepository
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.data.repository.ShoppingCartRepositoryImpl
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.ui.shoppingcart.ShoppingCartAdapter
import br.com.brunocardoso.studying.desafioandroidnexaasbruno.ui.shoppingcart.ShoppingCartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        ShoppingCartViewModel(
            ShoppingCartRepository()
        )
    }
}

val viewModelModuleTest = module {
    viewModel { (repo: ShoppingCartRepositoryImpl) ->
        ShoppingCartViewModel(repo)
    }
}

val adapterModule = module {
    single { (data: List<Product>, onItemClickListener: ((product: Product) -> Unit)) ->
        ShoppingCartAdapter(
            data, onItemClickListener
        )
    }
}