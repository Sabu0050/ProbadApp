package com.sabutos.englishproverbs_banglaprobad.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.sabutos.englishproverbs_banglaprobad.R;
import com.sabutos.englishproverbs_banglaprobad.adapter.EnglishSearchAdapter;
import com.sabutos.englishproverbs_banglaprobad.database.DatabaseOpenHelper;
import com.sabutos.englishproverbs_banglaprobad.model.Proverbs;
import com.sabutos.englishproverbs_banglaprobad.model.widget.IndexableListView;

import java.util.ArrayList;
import java.util.Arrays;

public class BanglaActivity extends AppCompatActivity {

    private IndexableListView mListView;
    private EnglishSearchAdapter adapter;
    private ArrayList<Proverbs> mProverbsList;
    private DatabaseOpenHelper mDBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bangla);
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-8471623365486761~6447681804");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        setTitle(R.string.nav_bangla);
        String[] alphabets = getResources().getStringArray(R.array.alphabatesBn);
        Arrays.asList(alphabets);
        mListView = (IndexableListView) findViewById(R.id.listview_bangla_product);
        mDBHelper = new DatabaseOpenHelper(this);
        mProverbsList = (ArrayList<Proverbs>) mDBHelper.getListProbadB();
        //Init adapter
        adapter = new EnglishSearchAdapter(getApplicationContext(),R.layout.item_listview ,mProverbsList);
        //Set adapter for listview

        mListView.setAdapter(adapter);
        mListView.setFastScrollEnabled(true);


    }
}
