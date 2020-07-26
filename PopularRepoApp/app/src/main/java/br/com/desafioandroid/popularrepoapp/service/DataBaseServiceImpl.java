package br.com.desafioandroid.popularrepoapp.service;

import br.com.desafioandroid.popularrepoapp.constantes.BancoDados;
import br.com.desafioandroid.popularrepoapp.dao.VersaoBancoDAO;
import br.com.desafioandroid.popularrepoapp.dao.impl.VersaoBancoDAOImpl;
import br.com.desafioandroid.popularrepoapp.database.Database;
import br.com.desafioandroid.popularrepoapp.entity.VersaoBanco;
import br.com.desafioandroid.popularrepoapp.interfaces.database.DataBaseService;

public class DataBaseServiceImpl implements DataBaseService {

	VersaoBancoDAO versaoBancoDAO;
	
	public DataBaseServiceImpl() {

		versaoBancoDAO = new VersaoBancoDAOImpl();
	}
	
	@Override
	public void verificarVersaoBanco() throws Exception {
		
		VersaoBanco versaoBanco = null;
		try {
			versaoBanco = versaoBancoDAO.findFirst();
		} catch (Exception e1) {}
		
		if (versaoBanco == null || versaoBanco.getVersao() < BancoDados.UPDATE_BANCO) {

			createOrDropDb();
		}
	}
	
	public void createOrDropDb() throws Exception {
		Database db = new Database();
		try {
			db.createDatabases(BancoDados.CLASSES_DOMAIN);
			versaoBancoDAO.insert(new VersaoBanco(BancoDados.UPDATE_BANCO));
		} catch (Exception e) {
			throw new Exception("falha ao criar banco de dados", e);
		}
	}
}
