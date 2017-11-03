package teste.com.br.teste.callback;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import teste.com.br.teste.delegate.Delegate;
import teste.com.br.teste.model.Items;
import teste.com.br.teste.model.Repository;

public class RepositoryCallback implements Callback<Items> {

    private Delegate delegate;
    private List<Repository> repositories;
    private String error;

    public RepositoryCallback(Delegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void onResponse(Call<Items> call, Response<Items> response) {
        if(response.isSuccessful()) {
            this.repositories = response.body().getItems();
            this.delegate.success();
        } else {
            try {
                response.errorBody().string();
            } catch (IOException e) {
                e.printStackTrace();
                this.delegate.error();
            }
            this.delegate.error();
        }
    }

    @Override
    public void onFailure(Call<Items> call, Throwable t) {
        this.error = t.getMessage();
        this.delegate.error();
    }

    public List<Repository> getRepositories() {
        return this.repositories;
    }

    public String getError() {
        return this.error;
    }

}
