package com.app.githubclient.services;

/**
 * Created by thaynan on 18/05/2016.
 */
public interface CustomCallback<T> {
    void succefull(T response);

    void failure(Throwable t);
}