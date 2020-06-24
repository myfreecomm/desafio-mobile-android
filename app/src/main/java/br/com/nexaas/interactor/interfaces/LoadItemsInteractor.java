package br.com.nexaas.interactor.interfaces;

import br.com.nexaas.model.Cart;

import java.util.ArrayList;
import java.util.List;

public interface LoadItemsInteractor {

    interface OnFinishedListener {
        void onFinished(ArrayList<Cart> cartArrayList);
        void onFailure(Throwable t);
    }

    void fetchCart(OnFinishedListener onFinishedListener);
}
