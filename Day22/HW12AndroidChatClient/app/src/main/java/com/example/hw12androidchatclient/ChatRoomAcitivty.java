package com.example.hw12androidchatclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

class ChatRoomActivity extends AppCompatActivity {

    @Override
    //takes the place of the constructor
    //intent takes place of activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room_acitivty);

        TextView text = findViewById(R.id.roomNameID);

        String info = "nothing";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            info = extras.getString(MainActivity.RoomNameKey);
        }
        text.setText(info);

    }
}