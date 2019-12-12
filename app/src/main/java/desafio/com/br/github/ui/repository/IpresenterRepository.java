package desafio.com.br.github.ui.repository;


import desafio.com.br.github.data.network.model.repository.*;

/**
 * Created by rafael on 24/01/18.
 */

public interface IpresenterRepository {

    interface PresenterView
    {
        void fetchData();
    }

    interface PresenterInteracor
    {
        void success(Repository repository);

        void failure(String msg);
    }
}
