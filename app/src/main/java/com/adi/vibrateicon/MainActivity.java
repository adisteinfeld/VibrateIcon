package com.adi.vibrateicon;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.appDescriptionText);
        String description =
                "<p>The Vibrate Icon app shows a notification whenever your phone is on vibrate.</p>\n" +
                "<p><br/></p>\n" +
                "<p>Android requires another, persistent notification for the app to be able to know when the\n" +
                "phone is changed to vibrate. You can hide this notification by long pressing, clicking\n" +
                "\"Turn off notifications\", and deselecting the \"Persistent Notification\" channel.</p>\n" +
                "<p><br/></p>\n" +
                "<p>Icons are Google Material Design vibrate.</p>";
        textView.setText(Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT));

        Intent intent = new Intent(this, VibrateIconService.class);
        getApplicationContext().startForegroundService(intent);
    }


}
