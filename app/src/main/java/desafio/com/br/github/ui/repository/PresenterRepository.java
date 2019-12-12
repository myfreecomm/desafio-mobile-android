package desafio.com.br.github.ui.repository;


import desafio.com.br.github.data.network.model.repository.*;
import desafio.com.br.github.interactor.repository.*;

/**
 * Created by rafael on 24/01/18.
 */

public class PresenterRepository implements IpresenterRepository.PresenterView, IpresenterRepository.PresenterInteracor{

    private IviewRepository view;
    private IiteractorRepository iteractorRepository;

    public PresenterRepository(final IviewRepository view) {

        this.view = view;
    }

    @Override
    public void fetchData() {
        iteractorRepository = new IteractorRepository(this);
        iteractorRepository.fechtData();
    }

    @Override
    public void success(Repository repository) {
        view.Success(repository);
    }

    @Override
    public void failure(String msg) {
        view.Failure(msg);
    }
}
