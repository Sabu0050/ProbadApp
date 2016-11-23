package com.sabutos.englishproverbs_banglaprobad.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class EnglishActivity extends AppCompatActivity {

    private IndexableListView mListView;
    private ListProverbsAdapter adapter;
    private ArrayList<Proverbs> mProverbsList;
    private DatabaseOpenHelper mDBHelper;
   // Map<String, Integer> mapIndex;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english);
        setTitle(R.string.nav_english);
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-8471623365486761~6447681804");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        String[] alphabets = getResources().getStringArray(R.array.alphabates);
        Arrays.asList(alphabets);
        mListView = (IndexableListView) findViewById(R.id.listview_english_product);
        editText = (EditText) findViewById(R.id.editText);
        mDBHelper = new DatabaseOpenHelper(this);

       /* File database = getApplicationContext().getDatabasePath(DatabaseOpenHelper.DBNAME);
        if(false == database.exists()) {
            mDBHelper.getReadableDatabase();
            //Copy db
            if(copyDatabase(this)) {
                Toast.makeText(this, "Copy database success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Copy data error", Toast.LENGTH_SHORT).show();
                return;
            }
        }*/
        mProverbsList = (ArrayList<Proverbs>) mDBHelper.getListProverbs();
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

        /*getIndexList(alphabets);
        displayIndex();*/


    }



    /*private void displayIndex() {
        LinearLayout indexLayout = (LinearLayout) findViewById(R.id.side_index);

        TextView textView;
        List<String> indexList = new ArrayList<String>(mapIndex.keySet());
        for (String index : indexList) {
            textView = (TextView) getLayoutInflater().inflate(
                    R.layout.side_index_item, null);
            textView.setText(index);
            textView.setOnClickListener(this);
            indexLayout.addView(textView);
        }
    }

    private void getIndexList(String[] alphabets) {
        mapIndex = new LinkedHashMap<String, Integer>();
        for(int i = 0; i<alphabets.length;i++){
            String alphabate = alphabets[i];
            String index = alphabate.substring(0,1);
            if (mapIndex.get(index) == null)
                mapIndex.put(index, i);
        }
    }

    @Override
    public void onClick(View view) {
        TextView selectedIndex = (TextView) view;
        lvProduct.setSelection(mapIndex.get(selectedIndex.getText()));

    }




   /* private boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(DatabaseOpenHelper.DBNAME);
            String outFileName = DatabaseOpenHelper.DBLOCATION + DatabaseOpenHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();

            outputStream.close();
            Log.w("MainActivity","DB copied");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }*/

}
