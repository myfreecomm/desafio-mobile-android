package br.com.desafioandroid.popularrepoapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dennys on 15/11/2017.
 */

public class ItemRepositoryEntity implements Parcelable {

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    @SerializedName("owner")
    public UserEntity owner;

    @SerializedName("forks_count")
    public int forkCount;

    @SerializedName("stargazers_count")
    public int startCount;


    private ItemRepositoryEntity(Parcel in) {

        name = in.readString();
        description = in.readString();
        owner = in.readParcelable(UserEntity.class.getClassLoader());
        forkCount = in.readInt();
        startCount = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeParcelable(owner, i);
        parcel.writeInt(forkCount);
        parcel.writeInt(startCount);
    }

    public static final Creator<ItemRepositoryEntity> CREATOR = new Creator<ItemRepositoryEntity>() {
        public ItemRepositoryEntity createFromParcel(Parcel source) {
            return new ItemRepositoryEntity(source);
        }

        public ItemRepositoryEntity[] newArray(int size) {
            return new ItemRepositoryEntity[size];
        }
    };
}
