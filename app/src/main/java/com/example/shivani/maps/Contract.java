package com.example.harish.maps;

import android.provider.BaseColumns;

/**
 * Created by Shreyas on 06-01-2018.
 */

public class Contract {
    private Contract() {
    }

    public static final class ContractEntry implements BaseColumns {
        public static final String COLUMN_LATITIUDE = "Latitude";
        public static final String COLUMN_LONGITUDE = "Longitude";
        public static final String TABLE_NAME = "location";


    }
}
