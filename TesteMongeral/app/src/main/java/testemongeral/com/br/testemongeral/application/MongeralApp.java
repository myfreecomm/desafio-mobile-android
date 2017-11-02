package testemongeral.com.br.testemongeral.application;

import android.app.Application;

import testemongeral.com.br.testemongeral.component.DaggerRepositoryComponent;
import testemongeral.com.br.testemongeral.component.RepositoryComponent;
import testemongeral.com.br.testemongeral.module.RepositoryModule;

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
