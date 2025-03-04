package com.example.scan_wifi_kion;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="CAN_MSG_TABLE")
public class CAN_Msg {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String PGN;
    private String SPN;
    private String message;

    public CAN_Msg(String PGN, String SPN, String message) {
        this.PGN = PGN;
        this.SPN = SPN;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPGN() {
        return PGN;
    }

    public void setPGN(String PGN) {
        this.PGN = PGN;
    }

    public String getSPN() {
        return SPN;
    }

    public void setSPN(String SPN) {
        this.SPN = SPN;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
