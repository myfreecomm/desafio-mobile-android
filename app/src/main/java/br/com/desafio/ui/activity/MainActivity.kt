package br.com.desafio.ui.activity

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.desafio.R
import br.com.desafio.ui.fragment.MainFragment
import br.com.desafio.util.Util.Companion.addFragmentTo

class MainActivity : AppCompatActivity() {

    companion object {
        private var back_pressed: Long = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
//        showFragment(
//            MainFragment(),
//            MainFragment.FRAGMENT_TAG
//        )
        addFragmentTo(R.id.content_frame, createFragment())
    }

    private fun createFragment(): MainFragment {
        return MainFragment.newInstance()
    }

    // Teste
    private fun showFragment(name: Fragment, tag: String) {
        val fragmentManager = supportFragmentManager
        val fragmentPopped = fragmentManager.popBackStackImmediate(tag, 0)
        if (fragmentPopped) {
           Log.d("Back",fragmentPopped.toString())
        } else {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.content_frame, name, tag)
            fragmentTransaction.addToBackStack(tag)
            fragmentTransaction.commit()
        }
    }

    fun showFragmentWithTransition(current: Fragment, newFragment: Fragment, tag: String?, sharedView: View, sharedElementName: String) {
        val fragmentManager = supportFragmentManager
        val fragmentPopped = fragmentManager.popBackStackImmediate(tag, 0)
        if (fragmentPopped) {
            Log.d("Bask stack ",fragmentPopped.toString())
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                current.sharedElementReturnTransition = TransitionInflater.from(this).inflateTransition(R.transition.default_transition)
                current.exitTransition = TransitionInflater.from(this).inflateTransition(android.R.transition.no_transition)
                newFragment.sharedElementEnterTransition = TransitionInflater.from(this).inflateTransition(R.transition.default_transition)
                newFragment.enterTransition = TransitionInflater.from(this).inflateTransition(android.R.transition.no_transition)
            }
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.content_frame, newFragment, tag)
            fragmentTransaction.addToBackStack(tag)
            fragmentTransaction.addSharedElement(sharedView, sharedElementName)
            fragmentTransaction.commit()
        }
    }

    override fun onBackPressed() {
        oneStepBack()
    }

    private fun oneStepBack() {
        val fts = supportFragmentManager.beginTransaction()
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount >= 2) {
            fragmentManager.popBackStackImmediate()
            fts.commit()
        } else {
            doubleClickToExit()
        }
    }

    private fun doubleClickToExit() {
        if (back_pressed + 2000 > System.currentTimeMillis()) finish() else Toast.makeText(this@MainActivity, "Click again to exit", Toast.LENGTH_SHORT).show()
        back_pressed = System.currentTimeMillis()
    }
}