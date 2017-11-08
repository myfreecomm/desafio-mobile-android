package br.com.andreozawa.githubjavapop.services;

import android.content.Context;

import java.util.List;

import br.com.andreozawa.githubjavapop.asynctasks.PublicReposAsyncTask;
import br.com.andreozawa.githubjavapop.model.PublicRepos;
import br.com.andreozawa.githubjavapop.repositories.PublicReposRepository;

/**
 * Created by andre.ozawa on 30/10/2017.
 */

public class PublicReposService {

    private PublicReposRepository publicReposRepository;

    public PublicReposService(Context context) {
        this.publicReposRepository = new PublicReposRepository(context);
    }

    public void get(int page, PublicReposAsyncTask.OnPublicReposListener onPublicReposListener) {
        this.publicReposRepository.get(page, onPublicReposListener);
    }

    public void getPullRequests(PublicRepos publicRepos, PublicReposAsyncTask.OnPublicReposListener onPublicReposListener) {
        this.publicReposRepository.getPullRequests(publicRepos, onPublicReposListener);
    }

    public long saveAll(List<PublicRepos> publicReposes) {
        return this.publicReposRepository.saveAll(publicReposes);
    }

    public long save (PublicRepos publicRepos) {
        return this.publicReposRepository.save(publicRepos);
    }
}
