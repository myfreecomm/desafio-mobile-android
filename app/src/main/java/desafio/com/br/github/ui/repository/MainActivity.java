package desafio.com.br.github.ui.repository;


import android.os.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;

import butterknife.*;
import desafio.com.br.github.R;
import desafio.com.br.github.data.network.model.repository.*;

public class MainActivity extends AppCompatActivity implements IviewRepository {

    @BindView(R.id.repositories_list)
    RecyclerView repositoriesRecyclerView;
    IpresenterRepository.PresenterView presenterRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenterRepository = new PresenterRepository(this);
        presenterRepository.fetchData();

        ButterKnife.bind(this);
    }

    @Override
    public void Success(Repository repository) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        repositoriesRecyclerView.setLayoutManager(layoutManager);
        repositoryAdapter adapter = new repositoryAdapter(repository,MainActivity.this);
        repositoriesRecyclerView.setAdapter(adapter);
    }

    @Override
    public void Failure(String error) {

    }
}
