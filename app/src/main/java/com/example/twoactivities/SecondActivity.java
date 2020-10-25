package com.example.twoactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private EditText mReply; // EditText member to handle text from EditText view
    public static final String EXTRA_REPLY = "com.example.android.twoactivities.extra.REPLY"; // Key of Intent Extras as extras are key value pair

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mReply = findViewById(R.id.editText_second); // Getting the EditText message by id
        Intent intent = getIntent(); // Taking the message coming from the Parent Activity
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE); // Converting the Parent Message to the String
        // Printing the message to the TextView in the Child Activity
        TextView textView = findViewById(R.id.text_message);
        textView.setText(message);
    }

    public void returnReply(View view) {
        String reply = mReply.getText().toString(); // Taking message from EditText as String
        // New Intent to reply to Parent Activity
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY,reply); // (EXTRA_REPLY is key , reply is value)
        setResult(RESULT_OK,replyIntent); // TO indicate that the response was successful
        finish(); // To close the Child Activity and return to the Parent Activity
    }
}