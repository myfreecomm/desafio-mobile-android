package testemongeral.com.br.testemongeral.component;

import dagger.Component;
import testemongeral.com.br.testemongeral.activity.MainActivity;
import testemongeral.com.br.testemongeral.fragment.PullRequestsFragment;
import testemongeral.com.br.testemongeral.fragment.RepositoryFragment;
import testemongeral.com.br.testemongeral.module.RepositoryModule;

@Component(modules=RepositoryModule.class)
public interface RepositoryComponent {

    public void inject(MainActivity activity);
    public void inject(RepositoryFragment fragment);
    public void inject(PullRequestsFragment fragment);

}
