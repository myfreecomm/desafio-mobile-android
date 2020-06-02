package com.appdesafio.cart.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import android.view.View
import android.view.ViewGroup
import com.appdesafio.cart.model.DbHelper
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.appdesafio.cart.model.Produto
import com.appdesafio.cart.R
import com.appdesafio.cart.services.produtoId
import com.bumptech.glide.Glide
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.detalhes_produtos.*
import java.text.DecimalFormat

class detalhesProdutosActivity : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.detalhes_produtos,
            container,
            false
        )
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        verDetalhes()
        detalhesProdutos()
    }
    fun detalhesProdutos() {
        materialToolbar.setNavigationOnClickListener {
            findNavController().navigate(
                R.id.transicaoVerProdutosId
            )
        }
    }
    fun setProduto(produto: Produto?) {
        txt_detalheId.text = produto?.nome
        txtEstoqueId.text = when (produto?.estoque) {
            0 -> resources.getString(R.string.out_stock)
            1 -> resources.getString(R.string.only_one)
            else -> resources.getString(R.string.in_stock)
        }
        val dec = DecimalFormat("#,##")
        val valor = dec.format(produto?.preco)
        txtPreco
            .text = "$" + "${valor}"
        txt_descricao
            .text = produto?.descricao
        Glide.with(this)
            .load(produto?.imageUrl)
            .override(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                300
            )
            .into(ImageId)
    }
    @SuppressLint("CheckResult")
    fun verDetalhes() {
        val produtoId: String = arguments?.getString(produtoId) ?: "CARREGANDO..."
        val db = DbHelper.create(requireContext()).daoProd()
        db.getProdutoById(produtoId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                setProduto(data)
            }, {
                Log.e("Erro Ver detalhes", it.message.toString())
            })
    }
}
