package br.com.desafio.ui.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import br.com.desafio.util.Util
import com.nostra13.universalimageloader.core.ImageLoader
import java.io.Serializable

@BindingAdapter("bind:imageUrl")
fun loadImage(view: ImageView, url: String?) {
    ImageLoader.getInstance().displayImage(url, view, Util.getOptions())
}

data class Item(
    var name: String,
    var quantity: Int,
    var stock: Int,
    var image_url: String,
    var price: Double,
    var tax: Double,
    var shipping: Int,
    var description: String) : Serializable