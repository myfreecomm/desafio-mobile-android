package fabio.oliveira.desafiomobileandroid.network;

import io.realm.RealmObject;

/**
 * Created by fabio on 17/10/17.
 */

public interface GitHubApiRouter<T extends RealmObject> {
    T getResult(int page);
    T[] getResult(String url);
}
