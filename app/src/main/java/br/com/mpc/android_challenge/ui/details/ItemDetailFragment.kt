package br.com.mpc.android_challenge.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.mpc.android_challenge.R
import br.com.mpc.android_challenge.ui.MainViewModel
import br.com.mpc.android_challenge.utils.getItemAvailableSituation
import br.com.mpc.android_challenge.utils.toMoneyFormat
import coil.load
import kotlinx.android.synthetic.main.fragment_item_detail.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ItemDetailFragment : Fragment() {
    private val sharedViewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupContent()
    }

    private fun setupContent() {
        with(sharedViewModel){
            itemImageView.load(item.image_url)
            itemNameTextView.text = item.name
            itemValueTextView.text = item.price.toMoneyFormat()
            itemStateTextView.text = item.getItemAvailableSituation()
            itemDescriptionTextView.text = item.description
        }
    }

}