package br.com.nexaas.presenter

import br.com.nexaas.interactor.interfaces.LoadItemsInteractor
import br.com.nexaas.model.Cart
import br.com.nexaas.presenter.interfaces.MainPresenter
import br.com.nexaas.view.interfaces.MainView

class MainPresenterImpl(mainView: MainView, private val findItemsInteractor: LoadItemsInteractor) : MainPresenter, LoadItemsInteractor.OnFinishedListener {

    override fun fetchCart() {
        findItemsInteractor.fetchCart(this)
    }

    private var mainView: MainView? = null

    override fun onItemLongClick(position: Int) {
        if (mainView != null) {
            mainView!!.showMessage("Position " + (position + 1) + " clicked")
        }
    }

    init {
        this.mainView = mainView
    }

    override fun onResume() {
        if (mainView != null) {
            mainView!!.showProgress()
        }
        findItemsInteractor.fetchCart(this)
    }

    override fun onItemClicked(position: Int) {
        if (mainView != null) {
            mainView!!.showMessage("Position" + (position + 1) + " Clicked")
        }
    }

    override fun onDestroy() {
        mainView = null
    }

    override fun onFinished(items: ArrayList<Cart>?) {
        if (mainView != null && items != null) {
            mainView!!.populateRecyclerCart(items)
            mainView!!.hideProgress()
        }

    }

    override fun onFailure(t: Throwable) {
        if (mainView != null) {
            mainView!!.hideProgress()
        }
    }
}
