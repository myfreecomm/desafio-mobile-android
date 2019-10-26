package com.valderas.leonardo.nexaasdesafio

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.content.Context
import com.google.common.truth.Truth
import com.valderas.leonardo.nexaasdesafio.main.libs.api.repo.RepoResponse
import com.valderas.leonardo.nexaasdesafio.main.libs.api.repo.RepoService
import com.valderas.leonardo.nexaasdesafio.main.mvvm.model.Repo
import com.valderas.leonardo.nexaasdesafio.main.mvvm.viewmodel.MainViewModel
import com.valderas.leonardo.nexaasdesafio.main.repositry.MainRepository
import com.valderas.leonardo.nexaasdesafio.main.utils.GenericEntityTransactionState
import com.valderas.leonardo.nexaasdesafio.main.utils.NetManager
import com.valderas.leonardo.nexaasdesafio.main.utils.Page
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.mockito.*
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnit


class MainViewModelTest {

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()
    @Mock
    lateinit var state: GenericEntityTransactionState
    @Mock
    lateinit var context: Context
    @Mock
    lateinit var netManager: NetManager
    @Mock
    lateinit var service: RepoService
    @Mock
    lateinit var page: Page
    lateinit var repositoryTest: MainRepository
    lateinit var viewModelTest: MainViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repositoryTest = MainRepository(netManager, service)
        viewModelTest = MainViewModel(repositoryTest)
    }

    @Test
    fun validateTransactionStateList_shouldBeEmpty() {
        val states = viewModelTest.reposStateLive.testObserver()
        Truth.assert_()
                .that(states.observedValues)
                .isEqualTo(mutableListOf<GenericEntityTransactionState>())
    }

    @Test
    fun validateLoadingTransactionState_shouldBeTrue() {
        val states = viewModelTest.reposStateLive.testObserver()
        val loading = GenericEntityTransactionState.IsLoading(true)
        viewModelTest.getReposList(page)

        val state = states.observedValues.first() as GenericEntityTransactionState.IsLoading
        Truth.assert_()
                .that(state.isloading)
                .isEqualTo(loading.isloading)
    }

    @Test
    fun validateRepos_shouldBeSuccess() {
        val json = TestUtils.getFileString("/mock/get_repos.json")
        val model = TestUtils.getGson<MutableList<Repo>>(json)
        val list = GenericEntityTransactionState.IsSuccess(model)
        `when`(netManager.isConnectedToInternet).thenReturn(true)
        `when`(repositoryTest.getReposList(page)).thenReturn(Single.just(RepoResponse(model)))

        val states = viewModelTest.reposStateLive.testObserver()
        viewModelTest.getReposList(page)
        val state = states.observedValues[1] as GenericEntityTransactionState.IsSuccess<MutableList<Repo>>

        Truth.assert_()
                .that(state.entity)
                .isEqualTo(list.entity)
    }

    @Test
    fun validateRepos_shouldBeConnectionError() {
        val message = "Please, verify your network connection"
        `when`(netManager.isConnectedToInternet).thenReturn(true)
        `when`(context.getString(R.string.connection_error)).thenReturn(message)
        `when`(repositoryTest.getReposList(page)).thenReturn(Single.error(Throwable(message)))

        val states = viewModelTest.reposStateLive.testObserver()
        viewModelTest.getReposList(page)
        val state = states.observedValues[1] as GenericEntityTransactionState.Error

        Truth.assert_()
                .that(state.msg)
                .isEqualTo(message)
    }

    @Test
    fun validateLoadingTransactionState_shouldBeFalseComplete() {
        val states = viewModelTest.reposStateLive.testObserver()
        val loading = GenericEntityTransactionState.IsLoading(false)
        viewModelTest.getReposList(page)

        val state = states.observedValues[2] as GenericEntityTransactionState.IsLoading
        Truth.assert_()
                .that(state.isloading)
                .isEqualTo(loading.isloading)
    }

    class RxImmediateSchedulerRule : TestRule {

        override fun apply(base: Statement, d: Description): Statement {
            return object : Statement() {
                @Throws(Throwable::class)
                override fun evaluate() {
                    RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
                    RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
                    RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
                    RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

                    try {
                        base.evaluate()
                    } finally {
                        RxJavaPlugins.reset()
                        RxAndroidPlugins.reset()
                    }
                }
            }
        }
    }
}