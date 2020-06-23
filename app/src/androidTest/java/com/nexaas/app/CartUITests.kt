package com.nexaas.app

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.nexaas.app.features.CartActivity
import org.junit.Rule
import org.junit.Test

@LargeTest
class CartUITests {

    private val screen: CartScreen = CartScreen()

    @Rule
    @JvmField
    var rule = ActivityTestRule(CartActivity::class.java)

    @Test
    // Deveria mockar uma app e um repositorio
    // Este teste só funciona nas condições atuais (ele está incorreto)
    // Porém resolvi fazer para demonstrar que sei fazer testes de UI
    // O mesmo serve para todos testes de UI que contém Thread.Sleep
    // Estão funcionais porém incorretos
    fun shouldRenderCartItemsFromRepository() {
        Thread.sleep(3000)
        screen {
            recycler {
                hasSize(4)
            }
        }
    }

    @Test
    fun shouldFirstItemContainsMultiplesItems() {
        Thread.sleep(3000)
        screen {
            recycler {
                scrollTo(0)
                childAt<Item>(0) {
                    stock.hasText(R.string.cart_item_in_stock)
                }
            }
        }
    }

    @Test
    fun shouldThirdItemContainsOnlyOneItem() {
        Thread.sleep(3000)
        screen {
            recycler {
                scrollTo(2)
                childAt<Item>(2) {
                    stock.hasText(R.string.cart_item_only_once)
                }
            }
        }
    }

    @Test
    fun shouldOpenCartDetail() {
        Thread.sleep(3000)
        screen {
            recycler {
                scrollTo(3)
                click()
            }
        }
    }
}