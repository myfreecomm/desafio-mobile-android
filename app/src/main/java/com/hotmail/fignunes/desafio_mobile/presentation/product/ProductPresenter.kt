package com.hotmail.fignunes.desafio_mobile.presentation.product

import android.view.View
import com.hotmail.fignunes.desafio_mobile.R
import com.hotmail.fignunes.desafio_mobile.common.BasePresenter
import com.hotmail.fignunes.desafio_mobile.common.toEuaStringAmount
import com.hotmail.fignunes.desafio_mobile.presentation.common.CheckIsOnline
import com.hotmail.fignunes.desafio_mobile.presentation.common.StringHelper
import com.hotmail.fignunes.desafio_mobile.presentation.movie.actions.GetProducts
import com.hotmail.fignunes.desafio_mobile.presentation.movie.actions.ResponsesToProducts
import com.hotmail.fignunes.desafio_mobile.repository.remote.product.responses.ProductErrorCodes
import com.hotmail.fignunes.desafio_mobile.repository.remote.product.responses.ProductResponses
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.math.BigDecimal

class ProductPresenter(
    private val contract: ProductContract,
    private val getProducts: GetProducts,
    private val responsesToProducts: ResponsesToProducts,
    private val checkIsOnline: CheckIsOnline,
    private val stringHelper: StringHelper
) : BasePresenter() {

    var showLoading: Int = View.GONE
        set(value) {
            field = value
            notifyChange()
        }

    var size: String = ""
    set(value) {
        field = value
        notifyChange()
    }

    var total: String = ""
    set(value) {
        field = value
        notifyChange()
    }

    var subtotal: String = ""
    set(value) {
        field = value
        notifyChange()
    }

    var shipping: String = ""
    set(value) {
        field = value
        notifyChange()
    }

    var tax: String = ""
    set(value) {
        field = value
        notifyChange()
    }

    fun onCreate() {
        contract.initializeSwipe()
        searchProducts()
    }

    fun searchProducts() {
        if(!checkIsOnline.execute()) {
            contract.message(R.string.without_internet_connection)
            contract.swipeOff()
            return
        }

        showLoading = View.VISIBLE

        getProducts.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                contract.swipeOff()
                showLoading = View.GONE
                contract.initializeAdapter(responsesToProducts.execute(it))
                displayTotals(it)
            }, {
                showLoading = View.GONE
                contract.swipeOff()
                when (it) {
                    is HttpException -> httpExceptionHanding(it.code())
                    else -> R.string.erro_get_product
                }.apply { contract.message(this) }

            })
            .also { addDisposable(it) }
    }

    private fun displayTotals(responses: List<ProductResponses>) {
        size = responses.size.toString() + " " + stringHelper.getString(R.string.items_in_your_cart)

        var totalizationSubtotal: BigDecimal = BigDecimal.ZERO
        var totalizationShipping: Int = 0
        var totalizationTax: Int = 0

        responses.map {
            totalizationSubtotal += it.price
            totalizationShipping += it.shipping
            totalizationTax += it.tax

        }

        total = (totalizationSubtotal + totalizationShipping.toBigDecimal() + totalizationTax.toBigDecimal()).toEuaStringAmount()
        subtotal = totalizationSubtotal.toEuaStringAmount()
        shipping = totalizationShipping.toBigDecimal().toEuaStringAmount()
        tax = totalizationTax.toBigDecimal().toEuaStringAmount()
    }

    fun checkout() {
        contract.message(R.string.checkout)
    }

    private fun httpExceptionHanding(code: Int) =
        when(code) {
            ProductErrorCodes.UNAVAILABLE_SERVICE.code -> R.string.unavailable_service
            ProductErrorCodes.ERROR_UNEXPECTED.code -> R.string.error_unexpected
            else -> R.string.service_unhandled_error
        }
}