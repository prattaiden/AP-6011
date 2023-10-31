package com.example.hw12androidtwo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

public class ChatRoomActivity extends AppCompatActivity {

    String userInfo = "nothing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        TextView RoomView =findViewById(R.id.roomNameField);
        TextView UserView =findViewById(R.id.UserNameField);

        String roomInfo = "nothing";

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            roomInfo = extras.getString(MainActivity.roomNameKey);
            userInfo = extras.getString(MainActivity.userNameKey);

        }
        RoomView.setText("Room: " + roomInfo);
        UserView.setText("User: " + userInfo);

    }
    public void handleSendClick(View view) {
        EditText messageET = findViewById(R.id.ChatEdit);
        TextView tv = findViewById(R.id.messageField);
        ScrollView scrollView = findViewById(R.id.scrollView2);

        String message = String.valueOf(messageET.getText());
        tv.append(userInfo + ": " + message + "\n");

        // Scroll to the bottom of the ScrollView
        //making it in the runnable class is necessary to execute scrolling operation
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    }

