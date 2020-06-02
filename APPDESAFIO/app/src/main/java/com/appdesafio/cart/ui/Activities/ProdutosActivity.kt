package com.appdesafio.cart.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.appdesafio.cart.model.DbHelper
import com.appdesafio.cart.model.Repositorio
import com.appdesafio.cart.model.ProdutoModel
import com.appdesafio.cart.R
import com.appdesafio.cart.services.produtoId
import com.appdesafio.cart.ui.adapters.ProdutoAdapter
import com.appdesafio.cart.model.ProdutoFactory
import kotlinx.android.synthetic.main.produtos.*

class ProdutosActivity : Fragment() {
    private lateinit var Padapter: ProdutoAdapter
    private lateinit var model: ProdutoModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.produtos, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Padapter =
            ProdutoAdapter(
                clickListener = {
                    val data = Bundle()
                    data.putString(produtoId, it.toString())
                    findNavController().navigate(
                        R.id.transicaoParaDetalhesId,data)
                }
            )
        setmodel()
        setRview()
        UiSubs()
    }

    override fun onResume() {
        super.onResume()
        model.getProdutos()
    }
    fun setmodel() {
        val db = DbHelper.create(requireContext()).daoProd()
        model = ViewModelProvider(
            requireActivity(),
            ProdutoFactory(
                Repositorio(db)
            )
        ).get(ProdutoModel::class.java)
    }
    fun setRview() {
        with(listarProdutosView) {
            setHasFixedSize(true)
            adapter = Padapter
            layoutAnimation =
                AnimationUtils
                    .loadLayoutAnimation(this.context, R.anim.anima_tela_inicial)
        }
    }
    fun UiSubs() {
        with(model) {

           onLoadFinished.observe(viewLifecycleOwner,
               Observer {
                loadingLayout.visibility = View.GONE
                contentLayout.visibility = View.VISIBLE
            })
            onError.observe(viewLifecycleOwner,
                Observer { erro ->
                Toast.makeText(context, "não foi possivel atualizar informações", Toast.LENGTH_SHORT)
                    .show()
                Log.e("ERRO UiSubs", "Erro ao buscar produtos: $erro")
            })

            prod.observe(viewLifecycleOwner,
                Observer { data ->
                    Padapter.update(data)
                textProductsQtd.text = getString(R.string.qtdProds)
            })
        }
    }

}
