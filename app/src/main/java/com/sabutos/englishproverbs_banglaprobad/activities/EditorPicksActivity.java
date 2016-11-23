package com.sabutos.englishproverbs_banglaprobad.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.sabutos.englishproverbs_banglaprobad.R;
import com.sabutos.englishproverbs_banglaprobad.adapter.ListProverbsAdapter;
import com.sabutos.englishproverbs_banglaprobad.database.DatabaseOpenHelper;
import com.sabutos.englishproverbs_banglaprobad.model.Proverbs;
import com.sabutos.englishproverbs_banglaprobad.model.widget.IndexableListView;

import java.util.ArrayList;
import java.util.Arrays;

public class EditorPicksActivity extends AppCompatActivity {
    private IndexableListView mListView;
    private ListProverbsAdapter adapter;
    private ArrayList<Proverbs> mProverbsList;
    private DatabaseOpenHelper mDBHelper;
    // Map<String, Integer> mapIndex;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_picks);
        setTitle(R.string.nav_editorpicks);
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-8471623365486761~6447681804");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String[] alphabets = getResources().getStringArray(R.array.alphabates);
        Arrays.asList(alphabets);
        mListView = (IndexableListView) findViewById(R.id.listview_english_product);
        editText = (EditText) findViewById(R.id.editText);
        mDBHelper = new DatabaseOpenHelper(this);

        mProverbsList = (ArrayList<Proverbs>) mDBHelper.getListProverbsEP();
        //Init adapter
        adapter = new ListProverbsAdapter(getApplicationContext(),R.layout.item_listview ,mProverbsList);
        //Set adapter for listview

        mListView.setAdapter(adapter);
        mListView.setFastScrollEnabled(true);


        editText.setFocusableInTouchMode(false);
        editText.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                // TODO Auto-generated method stub
                editText.setFocusableInTouchMode(true);
                editText.requestFocus() ;
                return false;
            }});


        try{

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    adapter.getFilter().filter(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });}catch (Exception e){

        }


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
