package com.valderas.leonardo.nexaasdesafio

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.valderas.leonardo.nexaasdesafio.main.libs.api.repo.RepoResponse
import com.valderas.leonardo.nexaasdesafio.main.libs.api.repo.RepoService
import com.valderas.leonardo.nexaasdesafio.main.mvvm.model.Repo
import com.valderas.leonardo.nexaasdesafio.main.repositry.MainRepository
import com.valderas.leonardo.nexaasdesafio.main.utils.NetManager
import com.valderas.leonardo.nexaasdesafio.main.utils.Page
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


class MainRepositoryTest {

    lateinit var repositoryTest: MainRepository
    @Mock
    lateinit var netManager: NetManager
    @Mock
    lateinit var service: RepoService
    @Mock
    lateinit var page: Page

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repositoryTest = MainRepository(netManager, service)
    }

    @Test
    fun getReposToService_ShouldReturnOneObject() {

        val json = TestUtils.getFileString("/mock/get_repos.json")
        val model = TestUtils.getGson<MutableList<Repo>>(json)

        `when`(netManager.isConnectedToInternet!!).thenReturn(true)
        `when`(service.getReposList(page.pageNumber, page.pageSize)).thenReturn(Single.just(RepoResponse(model)))

        repositoryTest.getReposList(page).test().assertValue {
            verify(netManager).isConnectedToInternet
            verify(service).getReposList(page.pageNumber, page.pageSize)
            it.repos == model
        }
    }

    @Test
    fun getReposToDataBases_ShouldReturnEmptyList() {

        val json = TestUtils.getFileString("/mock/get_repos.json")
        val model = TestUtils.getGson<MutableList<Repo>>(json)

        `when`(netManager.isConnectedToInternet!!).thenReturn(false)

        service.getReposList(page.pageNumber, page.pageSize)
        repositoryTest.getReposList(page).test().assertValue {
            verify(netManager).isConnectedToInternet
            verify(service).getReposList(page.pageNumber, page.pageSize)
            verifyZeroInteractions(service)
            (it.repos == arrayListOf<Repo>() && it.repos != model)
        }
    }
    @Test
    fun validateInternetConnection_shouldReturnFalseAndThrowException() {
        `when`(netManager.isConnectedToInternet).thenReturn(null)
        service.getReposList(page.pageNumber, page.pageSize)

        repositoryTest.getReposList(page).test().assertError {
            it is Throwable
        }
    }
}