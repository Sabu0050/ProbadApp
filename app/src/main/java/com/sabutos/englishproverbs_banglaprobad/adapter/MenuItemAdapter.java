package com.sabutos.englishproverbs_banglaprobad.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sabutos.englishproverbs_banglaprobad.R;
import com.sabutos.englishproverbs_banglaprobad.activities.MainActivity;

/**
 * Created by devil on 31-Oct-16.
 */

public class MenuItemAdapter extends BaseAdapter {

    Context context;
    int[] imageId;
    String[] title;
    private int[] colors ;//= new int[] {  };
    private static LayoutInflater inflater=null;

    public MenuItemAdapter(MainActivity mainActivity, int[] imageId, String[] title, int[] colors) {
        context = mainActivity;
        this.imageId = imageId;
        this.title = title;
        this.colors = colors;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.menu_list, null);
        holder.tv=(TextView) rowView.findViewById(R.id.tv_menu);
        holder.img=(ImageView) rowView.findViewById(R.id.imageIcon);
        holder.tv.setText(title[position]);
        holder.img.setImageResource(imageId[position]);

        if(position==0){
            rowView.setBackgroundColor(colors[position]);
        }
        else if (position==1){
            rowView.setBackgroundColor(colors[position]);
        }
        else if (position==2){
            rowView.setBackgroundColor(colors[position]);
        }
        return rowView;
    }
}

