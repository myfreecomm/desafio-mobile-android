package fabio.oliveira.desafiomobileandroid.viewmodel;

import fabio.oliveira.desafiomobileandroid.model.Item;
import fabio.oliveira.desafiomobileandroid.model.Pull;
import fabio.oliveira.desafiomobileandroid.network.PullRouter;

/**
 * Created by fabio on 17/10/17.
 */

public class PullViewModel {

    public Pull[] getResult(Item item) {
        Pull[] pulls = new PullRouter().getResult(item.getPullsUrl());
        return pulls;
    }

}
