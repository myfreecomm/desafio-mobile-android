package desafio.com.br.github.interactor.repository;


import desafio.com.br.github.data.network.*;
import desafio.com.br.github.data.network.model.repository.*;
import desafio.com.br.github.ui.repository.*;
import retrofit2.*;

/**
 * Created by rafael on 24/01/18.
 */

public class IteractorRepository implements IiteractorRepository {

    IpresenterRepository.PresenterInteracor presenterInteracor;

    public IteractorRepository(IpresenterRepository.PresenterInteracor presenterInteracor) {
        this.presenterInteracor = presenterInteracor;
    }

    @Override
    public void fechtData() {

        GithubApi.create().getRepository().enqueue(new Callback<Repository>() {
            @Override
            public void onResponse(Call<Repository> call, Response<Repository> response) {
                presenterInteracor.success(response.body());
            }

            @Override
            public void onFailure(Call<Repository> call, Throwable t) {
                presenterInteracor.failure(t.getLocalizedMessage());
            }
        });
    }
}
