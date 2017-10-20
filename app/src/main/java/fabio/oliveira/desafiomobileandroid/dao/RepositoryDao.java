package fabio.oliveira.desafiomobileandroid.dao;

import fabio.oliveira.desafiomobileandroid.model.Repository;
import io.realm.Realm;
import io.realm.RealmModel;

/**
 * Created by fabio on 19/10/17.
 */

public class RepositoryDao implements RealmModel {

    private Repository repository = null;

    public void insertOrUpdate(final Repository repository) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insertOrUpdate(repository);
                }
            });
        } catch (Exception e) {

        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    public void deleteAll() {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.delete(Repository.class);
                }
            });
        } catch (Exception e) {

        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    public Repository findByPage(final int page){
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    if (realm.where(Repository.class).equalTo("page", page).findFirst() != null) {
                        repository = realm.copyFromRealm(realm.where(Repository.class).equalTo("page", page).findFirst());
                    }
                }
            });
        } catch (Exception e) {
            this.repository = null;
        } finally {
            if (realm != null) {
                realm.close();
            }
        }

        return repository;
    }
}
