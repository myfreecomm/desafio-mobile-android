package br.com.desafioandroid.popularrepoapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dennys on 15/11/2017.
 */

public class UserEntity implements Parcelable {

    @SerializedName("id")
    public long id;

    @SerializedName("login")
    public String login;

    @SerializedName("avatar_url")
    public String avatarUrl;


    public UserEntity(long id, String login, String avatarUrl) {
        this.id = id;
        this.login = login;
        this.avatarUrl = avatarUrl;
    }

    private UserEntity(Parcel in) {
        id = in.readLong();
        login = in.readString();
        avatarUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeLong(id);
        parcel.writeString(login);
        parcel.writeString(avatarUrl);
    }

    public static final Creator<UserEntity> CREATOR = new Creator<UserEntity>() {
        public UserEntity createFromParcel(Parcel source) {
            return new UserEntity(source);
        }

        public UserEntity[] newArray(int size) {
            return new UserEntity[size];
        }
    };
}
