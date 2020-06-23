package com.welbertsoft.androidteste.repository.response

/**
Created By Welbert Moreira on 23/06/2020 : 20:19
 */


typealias DataResponse = ArrayList<DataResponseElement>

data class DataResponseElement(
    val name: String,
    val quantity: Long,
    val stock: Long,
    val imageURL: String,
    val price: Long,
    val tax: Long,
    val shipping: Long,
    val description: String
)
