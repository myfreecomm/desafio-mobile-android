package desafio.com.br.github.ui.pull_request;

import java.util.*;
import desafio.com.br.github.data.network.model.pull_request.*;

/**
 * Created by rafael on 24/01/18.
 */

public interface IviewPullRequest {

    public void Success(ArrayList<PullRequest> pullRequests);

    public void Failure(String error);
}
