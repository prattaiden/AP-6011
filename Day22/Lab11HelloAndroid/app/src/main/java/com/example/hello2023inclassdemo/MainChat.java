package com.example.hello2023inclassdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainChat extends AppCompatActivity {

    @Override
    //takes the place of the constructor
    //intent takes place of activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);

        Button button = findViewById(R.id.MainChatButton);

        String info = "nothing";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            info = extras.getString(MainActivity.RoomNameKey);
        }
        button.setText(info);

    }
}