package desafio.com.br.github.ui.repository;


import desafio.com.br.github.data.network.model.repository.*;

/**
 * Created by rafael on 24/01/18.
 */

public interface IviewRepository {

    void Success(Repository repository);

    void Failure(String error);
}
