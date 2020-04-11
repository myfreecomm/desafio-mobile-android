package com.globo.raphaelbgr.desafio.brasileirao

import com.globo.raphaelbgr.desafio.brasileirao.main.MainActivityPresenter
import com.globo.raphaelbgr.desafio.brasileirao.main.MainActivityPresenterImpl
import com.globo.raphaelbgr.desafio.brasileirao.main.MainView
import com.globo.raphaelbgr.desafio.data.local.LocalRepository
import com.globo.raphaelbgr.desafio.data.network.ApiService
import com.globo.raphaelbgr.desafio.data.network.response.MatchList
import com.globo.raphaelbgr.desafio.data.network.response.matchlist.Match
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.exceptions.base.MockitoException
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class MainActivityPresenterUnitTest : BaseUnitTest() {

    private lateinit var presenter: MainActivityPresenter

    @Mock
    private lateinit var view: MainView

    @Mock
    private lateinit var api: ApiService

    @Mock
    private lateinit var local: LocalRepository

    @Before
    fun instantiateMainActivityPresenter() {
        presenter = MainActivityPresenterImpl(managedCoroutineScope, api, local)
        presenter.setView(view)
    }

    @Test
    fun testMainActivityPresenterGetMatchDataFromWithLocalAndApiLogic() = runBlocking {
        val mockList = TestUtil.loadBrasileiraoMockResponse();
        val emptyList = ArrayList<Match>()

        // Mock DB response
        whenever(local.loadMatchListAsync())
            .thenReturn(CompletableDeferred(emptyList))
        whenever(local.saveMatchesToDbAsync(anyList()))
            .thenReturn(CompletableDeferred())

        // Mock API response
        whenever(api.getMatchListAsync())
            .thenReturn(CompletableDeferred(Response.success(MatchList(mockList))))

        presenter.getMatchList()

        // Expected results
        Mockito.verify(view).onMatchListApiSuccess(mockList)
        Mockito.verify(view).showLoading(true)
        Mockito.verify(view).showLoading(false)
    }

    @Test
    fun testMainActivityPresenterGetMatchDataFromCache() = runBlocking {
        val mockList = TestUtil.loadBrasileiraoMockResponse()

        // Mock API response
        whenever(local.loadMatchListAsync())
            .thenReturn(CompletableDeferred(mockList))

        presenter.getMatchList()

        // Expected results
        Mockito.verify(view).onMatchListCacheSuccess(mockList)
        Mockito.verify(view).showLoading(true)
        Mockito.verify(view).showLoading(false)
    }

    @Test
    fun testMainActivityPresenterGetMatchDataWithCacheAndApiLogicIoError() = runBlocking {
        val emptyList = ArrayList<Match>()

        // Mock DB response
        whenever(local.loadMatchListAsync())
            .thenReturn(CompletableDeferred(emptyList))

        // Mock API response
        whenever(api.getMatchListAsync())
            .thenThrow(MockitoException::class.java)

        presenter.getMatchList()

        // Expected results
        Mockito.verify(view).onMatchListApiFailure()
        Mockito.verify(view).showLoading(true)
        Mockito.verify(view).showLoading(false)
    }
}