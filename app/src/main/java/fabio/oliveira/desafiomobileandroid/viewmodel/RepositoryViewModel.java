package fabio.oliveira.desafiomobileandroid.viewmodel;


import fabio.oliveira.desafiomobileandroid.model.Repository;
import fabio.oliveira.desafiomobileandroid.network.RepositoryRouter;
import fabio.oliveira.desafiomobileandroid.service.RepositoryService;

/**
 * Created by fabio on 17/10/17.
 */

public class RepositoryViewModel {

    public Repository getResult(int page) {
        RepositoryService repositoryService = new RepositoryService();

        Repository repository = repositoryService.findRepositoryByPage(page);

        if(repository == null) {
            repository = new RepositoryRouter().getResult(page);
            repository.setPage(page);
            repositoryService.saveRepository(repository);
        }

        return repository;
    }

}
