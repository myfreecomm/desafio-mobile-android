package br.com.desafioandroid.popularrepoapp.entity;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VersaoBanco implements Serializable {

	@SerializedName("versao")
	public int versao;

	public VersaoBanco() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VersaoBanco(int versao) {
		super();
		this.versao = versao;
	}

	public int getVersao() {
		return versao;
	}

	public void setVersao(int versao) {
		this.versao = versao;
	}

}
