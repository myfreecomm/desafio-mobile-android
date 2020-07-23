package com.example.nexaaschallenge.repo

import com.example.nexaaschallenge.model.ItemCartData

fun mapItem(item: ItemCart): ItemCartData {
    return ItemCartData(
        name = item.name,
        stock = item.stock,
        imageUrl = item.imageUrl,
        price = item.price,
        description = item.description
    )
}

fun mapItems(items: List<ItemCart>): List<ItemCartData> {
    return items.map { mapItem(it) }
}
