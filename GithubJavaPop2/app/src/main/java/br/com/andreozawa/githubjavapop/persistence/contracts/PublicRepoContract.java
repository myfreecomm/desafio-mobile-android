package br.com.andreozawa.githubjavapop.persistence.contracts;

import android.provider.BaseColumns;

import com.google.gson.annotations.SerializedName;

/**
 * Created by andre.ozawa on 08/11/2017.
 */

public class PublicRepoContract {
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + PublicRepoEntry.TABLE_NAME + " (" +
                    PublicRepoEntry._ID + " INTEGER PRIMARY KEY," +
                    PublicRepoEntry.ID_REPO + INTEGER_TYPE + COMMA_SEP +
                    PublicRepoEntry.NAME + TEXT_TYPE + COMMA_SEP +
                    PublicRepoEntry.DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    PublicRepoEntry.QT_FORK + INTEGER_TYPE + COMMA_SEP +
                    PublicRepoEntry.QT_STAR + INTEGER_TYPE + " )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PublicRepoEntry.TABLE_NAME;

    private PublicRepoContract() {
    }

    public static class PublicRepoEntry implements BaseColumns {
        public static final String TABLE_NAME = "PublicRepo";

        public static final String ID_REPO = "id_repo";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String QT_FORK = "qt_fork";
        public static final String QT_STAR = "qt_star";
    }
}
