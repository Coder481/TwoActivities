package com.example.twoactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mMessageEditText; // EditText member to handle text from EditText view
    public static final String EXTRA_MESSAGE = "com.example.android.twoactivities.extra.MESSAGE"; // Key of Intent Extras as extras are key value pair
    public static final int TEXT_REQUEST = 1; // Key for a particular type of response
    private TextView mReplyHeadTextView;
    private TextView mReplyTextView;
    private static final String LOG_TAG = MainActivity.class.getSimpleName() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMessageEditText = findViewById(R.id.editText_main); // Getting the EditText message by id
        mReplyHeadTextView = findViewById(R.id.text_header_reply);
        mReplyTextView = findViewById(R.id.text_message_reply);
    }

    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, "Button clicked!"); // Give a message in logcat when the Send Button is clicked
        Intent intent = new Intent(this , SecondActivity.class); // Creating an Intent (from where , to where)
        String message = mMessageEditText.getText().toString(); // Taking message from EditText as String
        intent.putExtra(EXTRA_MESSAGE , message); // Putting the Extra message to the intent to carry the data to the Child Activity (EXTRA_MESSAGE is key , message is value)
        startActivityForResult(intent , TEXT_REQUEST); // Use startActivity(intent); when target is to only start the Child Activity not to expecting any response

    }

    // requestCode when launched the Activity with startActivityForResult()
    // resultCode set in the launched Activity (usually RESULT_OK or RESULT_CANCELLED)
    // Intent data contained the data returned from the launched Activity (SecondActivity here)
    @Override
    public void onActivityResult(int requestCode , int resultCode , Intent data){
        super.onActivityResult(requestCode , resultCode , data);
        if (requestCode == TEXT_REQUEST){ // Testing for TEXT_REQUEST to make sure you process the right Intent result
            if (resultCode == RESULT_OK){ // Testing for RESULT_OK  to make sure  that the result was successful (as RESULT may cancelled by the user(then code become RESULT_CANCELLED))
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(reply);
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    }
}