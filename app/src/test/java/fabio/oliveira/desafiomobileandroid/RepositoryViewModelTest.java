package fabio.oliveira.desafiomobileandroid;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import fabio.oliveira.desafiomobileandroid.model.Repository;
import fabio.oliveira.desafiomobileandroid.viewmodel.RepositoryViewModel;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by fabio on 19/10/17.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({Realm.class, RealmQuery.class, RealmResults.class})
public class RepositoryViewModelTest {

    private Realm mockRealm;

    @Before
    public void before() throws Exception {
        mockRealm = MockRealm.mockRealm();
        Realm.getDefaultInstance();
    }

    @Test
    public void getResult(){
        final int page = 1;
        final RealmQuery<Repository> realmQuery = mock(RealmQuery.class);
        final RealmQuery<Repository> anotherRealmQuery = mock(RealmQuery.class);
        final Repository repository = mock(Repository.class);
        final Repository anotherRepository = mock(Repository.class);

        when(mockRealm.where(Repository.class)).thenReturn(realmQuery);
        when(realmQuery.equalTo("page", page)).thenReturn(anotherRealmQuery);
        when(anotherRealmQuery.findFirst()).thenReturn(repository);
        when(mockRealm.copyFromRealm(repository)).thenReturn(anotherRepository);


        Repository repositoryResult = new RepositoryViewModel().getResult(page);

        assertNotNull(repositoryResult);
    }

    @After
    public void after(){
        mockRealm.close();
    }
}
