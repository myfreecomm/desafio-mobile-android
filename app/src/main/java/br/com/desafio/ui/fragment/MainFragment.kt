package br.com.desafio.ui.fragment

import android.os.Build
import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionInflater
import android.transition.TransitionSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.desafio.R
import br.com.desafio.databinding.MainFragmentBinding
import br.com.desafio.ui.model.Item
import br.com.desafio.util.MainAdapter
import br.com.desafio.vm.MainViewModel
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.main_item.*
import kotlinx.android.synthetic.main.navigation_view.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment: Fragment() {

    private val MOVE_DEFAULT_TIME: Long = 1000
    private val FADE_DEFAULT_TIME: Long = 300

    private val viewModel: MainViewModel by viewModel()

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
        const val FRAGMENT_TAG = "main"
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initItems()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: MainFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        binding.viewModel = viewModel
        lifecycle.addObserver(viewModel)
        binding.lifecycleOwner = this
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        viewModel.itens.observe(viewLifecycleOwner, Observer { it ->
            it?.let {
                with(recycler_view) {
                    this.layoutManager = LinearLayoutManager(activity)
                    this.addItemDecoration(
                        DividerItemDecoration(
                            this.context,
                            DividerItemDecoration.VERTICAL
                        )
                    )
                    this.adapter = MainAdapter(it) { item ->
                        activity?.let {
                            performTransition(item)
                        }
                    }
                }
            }
        })
        return binding.root
    }

    private fun initItems() {
        val toggle = ActionBarDrawerToggle(
            activity,
            drawer_layout,
            material_toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        material_toolbar.title = context?.getString(R.string.app_name)
        material_toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.search -> {
                    Toast.makeText(context, it.title.toString(), Toast.LENGTH_SHORT).show()
                }
                R.id._3d -> {
                    Toast.makeText(context, it.title.toString(), Toast.LENGTH_SHORT).show()
                }
                R.id.accelerator -> {
                    Toast.makeText(context, it.title.toString(), Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(context, it.title.toString(), Toast.LENGTH_SHORT).show()
                }
            }
            true
        }

        nav_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.viagem -> {
                    Toast.makeText(context, it.title.toString(), Toast.LENGTH_SHORT).show()
                }
                R.id.som -> {
                    Toast.makeText(context, it.title.toString(), Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(context, it.title.toString(), Toast.LENGTH_SHORT).show()
                }
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            false
        }
    }

    fun showFragmentWithTransition(newFragment: Fragment, tag: String?, sharedView: View, sharedElementName: String) {
        val previousFragment = activity?.let {
            it.supportFragmentManager.findFragmentById(R.id.content_frame)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            previousFragment!!.sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(R.transition.default_transition)
            previousFragment!!.exitTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.no_transition)
            newFragment.sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(R.transition.default_transition)
            newFragment.enterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.no_transition)
        }
        val fragmentTransaction = activity?.let { it.supportFragmentManager.beginTransaction() }
        //val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction!!.replace(R.id.content_frame, newFragment, tag)
        fragmentTransaction!!.addToBackStack(tag)
        fragmentTransaction!!.addSharedElement(sharedView, sharedElementName)
        fragmentTransaction!!.commit()
    }

    private fun performTransition(item: Item) {
        val previousFragment = activity?.let {
            it.supportFragmentManager.findFragmentById(R.id.content_frame)
        }
        val nextFragment = DetailFragment(item)
        val fragmentTransaction = activity?.let { it.supportFragmentManager.beginTransaction() }

        val exitFade = Fade()
        exitFade.duration = FADE_DEFAULT_TIME
        previousFragment!!.exitTransition = exitFade

        val enterTransitionSet = TransitionSet()
        enterTransitionSet.addTransition(
            TransitionInflater.from(activity).inflateTransition(android.R.transition.move)
        )
        enterTransitionSet.duration = MOVE_DEFAULT_TIME
        enterTransitionSet.startDelay = FADE_DEFAULT_TIME
        nextFragment.sharedElementEnterTransition = enterTransitionSet

        val enterFade = Fade()
        enterFade.startDelay = MOVE_DEFAULT_TIME + FADE_DEFAULT_TIME
        enterFade.duration = FADE_DEFAULT_TIME
        nextFragment.enterTransition = enterFade

        val logo: View = image_item
        val name = name
        val price = price
        val desc = desc
        fragmentTransaction?.let {
            it.addSharedElement(logo, logo.transitionName)
            //it.addSharedElement(name, name.transitionName)
            //it.addSharedElement(price, price.transitionName)
            //it.addSharedElement(desc, desc.transitionName)
            it.replace(R.id.content_frame, nextFragment)
            it.addToBackStack(MainFragment.toString())
            it.commitAllowingStateLoss()
        }
    }
}