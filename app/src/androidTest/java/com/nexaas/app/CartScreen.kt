package com.nexaas.app

import android.content.ClipData.Item
import android.view.View
import com.agoda.kakao.common.views.KView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import org.hamcrest.Matcher

class Item(parent: Matcher<View>) : KRecyclerItem<Item>(parent) {
    val container = KView(parent) { withId(R.id.container) }
    val name = KTextView(parent) { withId(R.id.name) }
    val stock = KTextView(parent) { withId(R.id.stock) }
}

class CartScreen : Screen<CartScreen>() {
    val recycler: KRecyclerView = KRecyclerView({
        withId(R.id.cartList)
    }, itemTypeBuilder = {
        itemType(::Item)
    })
}