package com.valderas.leonardo.nexaasdesafio.main.mvvm.view.pull

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.webkit.URLUtil
import com.valderas.leonardo.nexaasdesafio.R
import com.valderas.leonardo.nexaasdesafio.main.mvvm.model.Contributor
import com.valderas.leonardo.nexaasdesafio.main.mvvm.model.Repo
import com.valderas.leonardo.nexaasdesafio.main.mvvm.view.main.MainAdapter
import com.valderas.leonardo.nexaasdesafio.main.mvvm.viewmodel.MainViewModel
import com.valderas.leonardo.nexaasdesafio.main.mvvm.viewmodel.PullRequestViewModel
import com.valderas.leonardo.nexaasdesafio.main.utils.EndlessRecyclerOnScrollListener
import com.valderas.leonardo.nexaasdesafio.main.utils.GenericEntityTransactionState
import com.valderas.leonardo.nexaasdesafio.main.utils.Helpers
import com.valderas.leonardo.nexaasdesafio.main.utils.Page
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class PullRequestActivity : DaggerAppCompatActivity(), PullRequestAdapter.OnItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: PullRequestViewModel

    var recyclerAdapter = PullRequestAdapter(mutableListOf(), this)

    private var page = Page()


    private var name = ""
    private var owner = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        init()
    }

    private fun init(){
        getStringsIntentExtra()
        initViewModel()
        initRecyclerView()
        getRepoListFromViewModel()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(PullRequestViewModel::class.java)

        viewModel.pullStateLive.observe(this,
                Observer<GenericEntityTransactionState> {
                    it?.let {
                        when (it) {
                            is GenericEntityTransactionState.IsLoading -> {
                                pb_loading.visibility = if (it.isloading) View.VISIBLE else View.GONE
                            }
                            is GenericEntityTransactionState.IsSuccess<*> -> {
                                @Suppress("UNCHECKED_CAST")
                                recyclerAdapter.rangeItems(it.entity as MutableList<Contributor>)
                            }
                            is GenericEntityTransactionState.Error -> {
                                it.idResource
                                // showMessage(it.msg)
                            }
                        }
                    }
                })
    }

    fun getStringsIntentExtra() {
        owner = intent.getStringExtra(Helpers.OWNER_EXTRA)
        name = intent.getStringExtra(Helpers.NAME_EXTRA)
    }

    override fun onItemClick(url: String, view: View) {
        if (URLUtil.isValidUrl(url))
            startImpliciteIntent(url)
        else
            error("Url invalida.")
    }

    private fun startImpliciteIntent(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
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
                    page.pageNumber++
                    getRepoListFromViewModel()
                }
            })
        }
    }

    private fun getRepoListFromViewModel() = viewModel.getPullRequestList(owner, name, page)
}
