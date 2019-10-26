package com.valderas.leonardo.nexaasdesafio.main.mvvm.view.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.valderas.leonardo.nexaasdesafio.R
import com.valderas.leonardo.nexaasdesafio.main.mvvm.model.Repo
import com.valderas.leonardo.nexaasdesafio.main.mvvm.view.pull.PullRequestActivity
import com.valderas.leonardo.nexaasdesafio.main.mvvm.viewmodel.MainViewModel
import com.valderas.leonardo.nexaasdesafio.main.utils.EndlessRecyclerOnScrollListener
import com.valderas.leonardo.nexaasdesafio.main.utils.GenericEntityTransactionState
import com.valderas.leonardo.nexaasdesafio.main.utils.Helpers
import com.valderas.leonardo.nexaasdesafio.main.utils.Page
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), MainAdapter.OnItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: MainViewModel

    var recyclerAdapter = MainAdapter(mutableListOf(), this)

    private var page = Page()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        init()
    }

    private fun init() {
        initViewModel()
        initRecyclerView()
        stateObserver()
        getRepoListFromViewModel()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MainViewModel::class.java)
    }

    private fun stateObserver() {
        viewModel.reposStateLive.observe(this,
                Observer<GenericEntityTransactionState> {
                    it?.let {
                        when (it) {
                            is GenericEntityTransactionState.IsLoading -> {
                                pb_loading.visibility = if (it.isloading) View.VISIBLE else View.GONE
                            }
                            is GenericEntityTransactionState.IsSuccess<*> -> {
                                @Suppress("UNCHECKED_CAST")
                                recyclerAdapter.rangeItems(it.entity as MutableList<Repo>)
                            }
                            is GenericEntityTransactionState.Error -> {
                                it.idResource
                                // showMessage(it.msg)
                            }
                        }
                    }
                })
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(Helpers.PAGE_NUMBER, page.pageNumber)
    }

    override fun onItemClick(repo: Repo, view: View) {
        Intent(this, PullRequestActivity::class.java).apply {
            putExtra(Helpers.OWNER_EXTRA, repo.owner.login)
            putExtra(Helpers.NAME_EXTRA, repo.name)
            startActivity(this)
        }
    }

    private fun initRecyclerView() {
        with(recycler_view) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(false)
            adapter = recyclerAdapter
            clearOnScrollListeners()
            addOnScrollListener(object : EndlessRecyclerOnScrollListener() {
                override fun scrollUp(isUp: Boolean) {
                    //for buttom float
                }

                override fun onLoadMore() {
                    getRepoListFromViewModel()
                }
            })
        }
    }

    private fun getRepoListFromViewModel() {
        page.pageNumber++
        viewModel.getReposList(page)
    }
}
