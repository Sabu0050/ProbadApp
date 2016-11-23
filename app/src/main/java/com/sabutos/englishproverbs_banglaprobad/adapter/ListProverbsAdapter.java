package com.sabutos.englishproverbs_banglaprobad.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.sabutos.englishproverbs_banglaprobad.R;
import com.sabutos.englishproverbs_banglaprobad.model.Proverbs;
import com.sabutos.englishproverbs_banglaprobad.model.util.StringMatcher;

import java.util.ArrayList;

/**
 * Created by devil on 15-Oct-16.
 */

public class ListProverbsAdapter extends BaseAdapter implements Filterable,SectionIndexer {

    private Context mContext;
    private int textViewResourceId;
    private ArrayList<Proverbs> mProverbsList;
    private ArrayList<Proverbs> filterProverbsList;
    ValueFilter valueFilter;
    private String mSections = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public ListProverbsAdapter(Context mContext,int textViewResourceId, ArrayList<Proverbs> mProverbsList) {
        this.mContext = mContext;
        this.textViewResourceId = textViewResourceId;
        this.mProverbsList = mProverbsList;
        filterProverbsList = mProverbsList;
    }

    @Override
    public int getCount() {
        return mProverbsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProverbsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mProverbsList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater mInflater = (LayoutInflater) mContext
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        convertView = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_listview, null);

            TextView tv_proverbs = (TextView) convertView.findViewById(R.id.tv_proverbs);
            TextView tv_probad = (TextView) convertView.findViewById(R.id.tv_probad);


            Proverbs proverbs = mProverbsList.get(position);

            tv_proverbs.setText(proverbs.getProverbs()+".");
            tv_probad.setText(proverbs.getProbad()+"।");

        }
        if (position % 2 == 0) {

            convertView.setBackgroundColor(Color.parseColor("#ffffff"));

        } else {

            convertView.setBackgroundColor(Color.parseColor("#f1f1f1"));

        }
        return convertView;
    }

    @Override
    public Filter getFilter() {
        if(valueFilter==null){
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    @Override
    public Object[] getSections() {

        String[] sections = new String[mSections.length()];
        for (int i = 0; i < mSections.length(); i++)
            sections[i] = String.valueOf(mSections.charAt(i));
        return sections;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {

        for (int i = sectionIndex; i >= 0; i--) {
            for (int j = 0; j < getCount(); j++) {
                if (i == 0) {
                    // For numeric section
                    for (int k = 0; k <= 9; k++) {
                        if (StringMatcher.match(String.valueOf(mProverbsList.get(j).getProverbs().charAt(0)), String.valueOf(k)))
                            return j;
                    }
                } else {
                    if (StringMatcher.match(String.valueOf(mProverbsList.get(j).getProverbs().charAt(0)), String.valueOf(mSections.charAt(i))))
                        return j;
                }
            }
        }
        return 0;
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }

    class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if(constraint!=null && constraint.length()>0){
                ArrayList<Proverbs> filterList = new ArrayList<Proverbs>();

                for (int i = 0; i<filterProverbsList.size();i++){
                    if((filterProverbsList.get(i).getProverbs().toUpperCase())
                            .contains(constraint.toString().toUpperCase())){
                        Proverbs proverbs = new Proverbs(filterProverbsList.get(i).getId(),filterProverbsList.get(i)
                        .getProverbs(),filterProverbsList.get(i).getProbad(),filterProverbsList.get(i).getFlag());

                        filterList.add(proverbs);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            }
            else{
                results.count = filterProverbsList.size();
                results.values = filterProverbsList;
            }

            return results;


        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            mProverbsList = (ArrayList<Proverbs>) results.values;
            notifyDataSetChanged();
        }
    }
}
