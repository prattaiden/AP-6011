package com.example.hello2023inclassdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String msTag = "MainActivity:Dd";
    boolean firstClick = true;

    static final String RoomNameKey = "RoomNameKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleButton(View view){

        Log.d(msTag, "Button was pressed ...");

        EditText tv = findViewById(R.id.outputinfoID);
        //R variable to grab the ID
        if(firstClick) {

            tv.setText("");
            firstClick = false;
        }
        else{
            String roomName = String.valueOf(tv.getText());
            Intent intent = new Intent(this, MainChat.class);
            intent.putExtra(RoomNameKey , roomName);
            startActivity(intent);
        }


    }
}