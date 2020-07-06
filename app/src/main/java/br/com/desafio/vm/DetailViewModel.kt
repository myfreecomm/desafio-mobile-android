package br.com.desafio.vm

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import br.com.desafio.R

class DetailViewModel(private val application: Application) : ViewModel(), LifecycleObserver, View.OnClickListener {

    var resultImageUrl = ObservableField<String>()
    var name = ObservableField<String>()
    var price = ObservableField<String>()
    var description = ObservableField<String>()
    var voltar = MutableLiveData<Boolean>()

    override fun onClick(v: View) {
        when(v.id){
            R.id.remove -> {Toast.makeText(application.applicationContext, "Remove item", Toast.LENGTH_SHORT).show()}
            R.id.clean -> {voltar.value = true}
            R.id.search -> {Toast.makeText(application.applicationContext, "Search", Toast.LENGTH_SHORT).show()}
            else -> {Toast.makeText(application.applicationContext, "More", Toast.LENGTH_SHORT).show()}
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        Log.d("onResume", "onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Log.d("onPause", "onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Log.d("onStart", "onStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.d("onCreate", "onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Log.d("onStop", "onStop")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        Log.d("onDestroy", "onDestroy")
    }
}