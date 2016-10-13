package dev.desafioandroid.api.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Error {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("documentation_url")
    @Expose
    private String documentationUrl;

    public String getMessage() {
        return message;
    }

    public String getDocumentationUrl() {
        return documentationUrl;
    }
}
