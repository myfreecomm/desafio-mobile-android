package dev.desafioandroid.util;

public interface ResponseListener {
    void onSuccess(Object o);
    void onFailure(String errorMessage);
}