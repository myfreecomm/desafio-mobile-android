package fabio.oliveira.desafiomobileandroid.service;

import fabio.oliveira.desafiomobileandroid.dao.RepositoryDao;
import fabio.oliveira.desafiomobileandroid.model.Repository;

/**
 * Created by fabio on 19/10/17.
 */

public class RepositoryService {

    public void saveRepository(Repository repository){
        new RepositoryDao().insertOrUpdate(repository);
    }

    public Repository findRepositoryByPage(int page){
        return new RepositoryDao().findByPage(page);
    }

}
