package br.com.andreozawa.githubjavapop.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import br.com.andreozawa.githubjavapop.asynctasks.PublicReposAsyncTask;
import br.com.andreozawa.githubjavapop.model.PublicRepos;
import br.com.andreozawa.githubjavapop.model.enums.Method;
import br.com.andreozawa.githubjavapop.persistence.RepositoryBase;
import br.com.andreozawa.githubjavapop.persistence.contracts.PublicRepoContract;

/**
 * Created by andre.ozawa on 30/10/2017.
 */

public class PublicReposRepository extends RepositoryBase<PublicRepos> {

    private Context context;

    public PublicReposRepository(Context context) {
        super(context, PublicRepoContract.PublicRepoEntry.TABLE_NAME);
        this.context = context;
    }

    public void get(int page, PublicReposAsyncTask.OnPublicReposListener onPublicReposListener) {
        PublicReposAsyncTask publicReposAsyncTask = new PublicReposAsyncTask(this.context, onPublicReposListener);

        String url = "https://api.github.com/search/repositories?q=language:Java&sort=stars&page=" + String.valueOf(page);

        publicReposAsyncTask.execute(Method.GET, url);
    }

    public void getPullRequests(PublicRepos publicRepos, PublicReposAsyncTask.OnPublicReposListener onPublicReposListener) {
        PublicReposAsyncTask publicReposAsyncTask = new PublicReposAsyncTask(this.context, onPublicReposListener);

        Uri.Builder uriBuilder = new Uri.Builder()
                .scheme("https")
                .encodedAuthority("api.github.com")
                .appendEncodedPath("repos")
                .appendEncodedPath(publicRepos.getOwner().getLogin())
                .appendEncodedPath(publicRepos.getName())
                .appendEncodedPath("pulls");

        publicReposAsyncTask.execute(Method.GET, uriBuilder.toString());
    }

    @Override
    protected ContentValues getValues(PublicRepos object) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PublicRepoContract.PublicRepoEntry.ID_REPO, object.getIdRepo());
        contentValues.put(PublicRepoContract.PublicRepoEntry.NAME, object.getName());
        contentValues.put(PublicRepoContract.PublicRepoEntry.DESCRIPTION, object.getDescription());
        contentValues.put(PublicRepoContract.PublicRepoEntry.QT_FORK, object.getQtForks());
        contentValues.put(PublicRepoContract.PublicRepoEntry.QT_STAR, object.getQtStars());

        return contentValues;
    }

    @Override
    protected String[] getProjection() {
        return new String[]{
                PublicRepoContract.PublicRepoEntry._ID,
                PublicRepoContract.PublicRepoEntry.ID_REPO,
                PublicRepoContract.PublicRepoEntry.NAME,
                PublicRepoContract.PublicRepoEntry.DESCRIPTION,
                PublicRepoContract.PublicRepoEntry.QT_FORK,
                PublicRepoContract.PublicRepoEntry.QT_STAR
        };
    }

    @Override
    protected PublicRepos getRowMapper(Cursor cursor) {
        return new PublicRepos(
                cursor.getInt(cursor.getColumnIndex(PublicRepoContract.PublicRepoEntry._ID)),
                cursor.getInt(cursor.getColumnIndex(PublicRepoContract.PublicRepoEntry.ID_REPO)),
                cursor.getString(cursor.getColumnIndex(PublicRepoContract.PublicRepoEntry.NAME)),
                cursor.getString(cursor.getColumnIndex(PublicRepoContract.PublicRepoEntry.DESCRIPTION)),
                cursor.getInt(cursor.getColumnIndex(PublicRepoContract.PublicRepoEntry.QT_FORK)),
                cursor.getInt(cursor.getColumnIndex(PublicRepoContract.PublicRepoEntry.QT_STAR))
        );
    }

    @Override
    protected int getObjectId(PublicRepos object) {
        return object.getIdRepo();
    }
}
