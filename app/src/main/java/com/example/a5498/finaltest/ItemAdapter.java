package com.example.a5498.finaltest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemAdapter extends BaseAdapter {

    LayoutInflater lay;
    Map<String, Integer> ma;
    List<String> id;
    List<Integer> pwd;

    public ItemAdapter(Context c, Map m){
        lay  = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ma = m;
        id = new ArrayList<String>(m.keySet());
        pwd = new ArrayList<Integer>(m.values());
    }


    @Override
    public int getCount() {
        return ma.size();
    }

    @Override
    public Object getItem(int position) {
        return id.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = lay.inflate(R.layout.listlayout,null);

         TextView t1 = v.findViewById(R.id.LeftTextview);
         TextView t2 = v.findViewById(R.id.RightTextview);

         t1.setText(id.get(position));
         t2.setText(Integer.toString(pwd.get(position)));

        return v;
    }
}
