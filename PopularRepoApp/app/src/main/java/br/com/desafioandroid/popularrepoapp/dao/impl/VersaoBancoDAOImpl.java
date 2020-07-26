package br.com.desafioandroid.popularrepoapp.dao.impl;

import android.content.Context;

import br.com.desafioandroid.popularrepoapp.dao.VersaoBancoDAO;
import br.com.desafioandroid.popularrepoapp.entity.VersaoBanco;

public class VersaoBancoDAOImpl extends GenericDAOImpl<VersaoBanco> implements VersaoBancoDAO {

	public VersaoBancoDAOImpl() {
		super("VersaoBanco");
		// TODO Auto-generated constructor stub
	}
	
	public VersaoBancoDAOImpl(Context context){
		super("VersaoBanco", context);
	}

}
