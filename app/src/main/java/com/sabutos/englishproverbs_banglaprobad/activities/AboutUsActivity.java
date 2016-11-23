package com.sabutos.englishproverbs_banglaprobad.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.sabutos.englishproverbs_banglaprobad.R;

public class AboutUsActivity extends AppCompatActivity {

    TextView textView1,textView2,textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        setTitle(R.string.nav_about_us);

        textView1 = (TextView) findViewById(R.id.textViewLink1);
        textView1.setClickable(true);
        String text1 = "Probad bakko is developed by English-bangla.com";
        textView1.setText(text1);


        textView2 = (TextView) findViewById(R.id.textViewLink2);
        textView2.setClickable(true);
        String text2 = "E-mail: info@english-bangla.com";
        textView2.setText(text2);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        textView3 = (TextView) findViewById(R.id.textViewLink3);
        textView3.setText("© All right reserved by English-bangla.com\n");

        /*\n\n
        \n
        n*/
    }
}
