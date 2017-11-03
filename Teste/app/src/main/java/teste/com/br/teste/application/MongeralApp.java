package teste.com.br.teste.application;

import android.app.Application;

import teste.com.br.teste.component.RepositoryComponent;
import teste.com.br.teste.module.RepositoryModule;

public class MongeralApp extends Application {

    private RepositoryComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerRepositoryComponent.builder()
                                            .repositoryModule(new RepositoryModule(this))
                                            .build();
    }

    public RepositoryComponent getComponent() {
        return this.component;
    }

}
