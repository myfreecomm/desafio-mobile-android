//package com.example.rxlistdetail.repository
//
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import com.example.rxlistdetail.base.BaseKoinTest
//import com.example.rxlistdetail.di.configureTestAppComponent
//import com.example.rxlistdetail.feature.product.data.api.ProductRepository
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.junit.runners.JUnit4
//import org.koin.core.context.startKoin
//import org.koin.test.inject
//
//@RunWith(JUnit4::class)
//class ProductRepositoryTest() : BaseKoinTest() {
//
//    private val mRepo: ProductRepository by inject()
//
//    @get:Rule
//    var instantExecutorRule = InstantTaskExecutorRule()
//
//    @Before
//    fun start() {
//        super.setUp()
//        startKoin {
//            modules(configureTestAppComponent())
//        }
//    }
//
//    @Test
//    fun testCall() {
//        mRepo.getProducts()
//            .test()
//            .assertSubscribed()
//            .assertNoTimeout()
//            .assertComplete()
//            .assertValue {
//                it.isNotEmpty()
//            }
//            .assertValue {
//                it.getOrNull(0)?.name == "Pencil"
//            }
//            .assertNoErrors()
//
//    }
//
//}