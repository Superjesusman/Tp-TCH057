package com.gctcymd.mastermind.modele;

import android.provider.BaseColumns;

public class MyDBContract {

    public static final String DATABASE_NAME = "my_database.db";

    public static final int DB_VERSION = 2;

    public static class MyTable implements BaseColumns {
        public static final String TABLE_NAME = "partie";
        public static final String COLUMN_NAME1 = "id_code_secret";

        public static final String COLUMN_NAME2 = "courriel";

        public static final String COLUMN_NAME3 = "code_secret";

        public static final String COLUMN_NAME4 = "nb_couleurs";

        public static final String COLUMN_NAME5 = "resultat";

        public static final String COLUMN_NAME6 = "nb_tentatives";
    }
}
