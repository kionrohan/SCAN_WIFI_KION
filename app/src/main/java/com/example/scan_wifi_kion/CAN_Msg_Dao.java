package com.example.scan_wifi_kion;

import androidx.room.Dao;
import androidx.room.Insert;


@Dao
public interface CAN_Msg_Dao {

    @Insert
    void insert(CAN_Msg canMsg);
}
