package desafio.com.br.github.ui.pull_request;


import java.util.*;

import desafio.com.br.github.data.network.model.pull_request.*;

/**
 * Created by rafael on 24/01/18.
 */

public interface IpresenterPullRequest {

    interface PresenterView
    {
        void fetchData(String creator, String repository);
    }

    interface PresenterInteracor
    {
        void success(ArrayList<PullRequest> pullRequests);

        void failure(String msg);
    }
}
