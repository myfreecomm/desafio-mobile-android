package fabio.oliveira.desafiomobileandroid;

import org.mockito.Mockito;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import static org.powermock.api.mockito.PowerMockito.doCallRealMethod;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by fabio on 19/10/17.
 */

public class MockRealm {

    public static Realm mockRealm() {
        mockStatic(Realm.class);
        mockStatic(RealmResults.class);
        mockStatic(RealmQuery.class);

        Realm mockRealm = mock(Realm.class);
        when(Realm.getDefaultInstance()).thenReturn(mockRealm);
        doCallRealMethod().when(mockRealm).executeTransaction(Mockito.any(Realm.Transaction.class));

        return mockRealm;
    }




}