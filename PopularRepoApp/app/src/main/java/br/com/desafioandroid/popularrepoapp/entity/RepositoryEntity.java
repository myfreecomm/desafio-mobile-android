package br.com.desafioandroid.popularrepoapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dennys on 15/11/2017.
 */

public class RepositoryEntity implements Parcelable {

    @SerializedName("items")
    public List<ItemRepositoryEntity> listRepositoryEntities;

    public RepositoryEntity(List<ItemRepositoryEntity> listRepositoryEntities) {
        this.listRepositoryEntities = listRepositoryEntities;
    }

    private RepositoryEntity(Parcel in) {

      listRepositoryEntities = in.createTypedArrayList(ItemRepositoryEntity.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(listRepositoryEntities);
    }

    public static final Creator<RepositoryEntity> CREATOR = new Creator<RepositoryEntity>() {
        public RepositoryEntity createFromParcel(Parcel source) {
            return new RepositoryEntity(source);
        }

        public RepositoryEntity[] newArray(int size) {
            return new RepositoryEntity[size];
        }
    };
}
