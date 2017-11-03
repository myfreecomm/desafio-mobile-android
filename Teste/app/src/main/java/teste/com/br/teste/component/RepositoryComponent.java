package teste.com.br.teste.component;

import dagger.Component;
import teste.com.br.teste.activity.MainActivity;
import teste.com.br.teste.fragment.PullRequestsFragment;
import teste.com.br.teste.fragment.RepositoryFragment;
import teste.com.br.teste.module.RepositoryModule;

@Component(modules=RepositoryModule.class)
public interface RepositoryComponent {

    public void inject(MainActivity activity);
    public void inject(RepositoryFragment fragment);
    public void inject(PullRequestsFragment fragment);

}
