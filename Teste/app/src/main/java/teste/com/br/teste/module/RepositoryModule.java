package teste.com.br.teste.module;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import teste.com.br.teste.BuildConfig;
import teste.com.br.teste.service.RepositoryService;

@Module
public class RepositoryModule {

    private Application app;

    public RepositoryModule(Application app) {
        this.app = app;
    }

    @Provides
    public RepositoryService getRepositoryService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL_API_GITHUB)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RepositoryService service = retrofit.create(RepositoryService.class);
        return service;
    }

}
