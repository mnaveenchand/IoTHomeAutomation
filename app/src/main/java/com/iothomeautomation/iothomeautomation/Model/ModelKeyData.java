package com.iothomeautomation.iothomeautomation.Model;

import android.util.Log;

/**
 * Created by arunt on 16/03/2018.
 */

public class ModelKeyData {

    String Date,Login;

    public ModelKeyData(){}
    public ModelKeyData( String Date,String Login){
        this.Date = Date;
        this.Login = Login;

    }
    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }
}
