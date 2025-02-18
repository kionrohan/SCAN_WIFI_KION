package com.example.scan_wifi_kion;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities={CAN_Msg.class},version=1)
public abstract class Database extends RoomDatabase {
    public abstract CAN_Msg_Dao canMsgDao();
    public static Database instance;
    public static synchronized Database getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            Database.class, "app_database")

                    .build();
        }
        return instance;
    }
}
