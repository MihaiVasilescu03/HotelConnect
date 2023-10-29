package com.example.hotelconnect;

import android.os.Bundle;

public class ManagerManageActivity extends MainActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_manage_menu);
        activityChangeIntent(R.id.buttonOrar, ManagerOrarActivity.class);
        activityChangeIntent(R.id.buttonAnunturi, ManagerOrarActivity.class);
        activityChangeIntent(R.id.buttonCamere, ManagerOrarActivity.class);
    }

}
