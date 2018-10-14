package com.iothomeautomation.iothomeautomation.Model;

/**
 * Created by hakiki95 on 11/30/2016.
 */

public class ModelData {
    String Uid, Name, Tagg, Time;

    public ModelData(){}

    public ModelData(String Uid, String Name, String Tagg, String Time) {
        this.Time = Time;
        this.Name = Name;
        this.Tagg = Tagg;
        this.Uid = Uid;

    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String Uid) {
        this.Uid = Uid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getTagg() {
        return Tagg;
    }

    public void setTagg(String Tagg) {
        this.Tagg = Tagg;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }
}
