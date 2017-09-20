package com.example.meill.dijoncenter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.meill.dijoncenter.Models.Lieu;
import com.example.meill.dijoncenter.R;
import java.util.ArrayList;


public class DataLieuAdapter  {
    ArrayList<Lieu> dataLieu;
    LayoutInflater mInflater;
    Adapter adapter;
    public DataLieuAdapter(ArrayList<Lieu> lieus,LayoutInflater Inflater)
    {
        this.dataLieu=lieus;
        this.mInflater =Inflater;
        adapter = new Adapter();
    }

    public Adapter getAdapter() {
        return adapter;
    }

    public LayoutInflater getmInflater() {
        return mInflater;
    }
    public void setmInflater(LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }

    public ArrayList<Lieu> getDataLieu() {
        return dataLieu;
    }
    public void setDataLieu(ArrayList<Lieu> dataLieu) {
        this.dataLieu = dataLieu;
    }


    public class Adapter extends BaseAdapter{

    @Override
    public int getCount() {
        return dataLieu.size();
    }

    @Override
    public Lieu getItem(int position) {
        return dataLieu.get(position);
    }

    @Override
    public long getItemId(int position) {

        return dataLieu.get(position).getId().getMostSignificantBits() & Long.MAX_VALUE;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        //mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.layout, null);
            holder = new ViewHolder();
            holder.textView = (TextView)convertView.findViewById(R.id.textView);
            holder.textView2 = (TextView)convertView.findViewById(R.id.textView2);
            holder.textView3 = (TextView)convertView.findViewById(R.id.textView3);
            holder.textView4 = (TextView)convertView.findViewById(R.id.textView4);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.textView.setText(dataLieu.get(position).getName().toString());
        holder.textView2.setText(dataLieu.get(position).getType().toString());
        holder.textView3.setText(dataLieu.get(position).getLocation().getCity().toString());
        holder.textView4.setText(dataLieu.get(position).getLocation().getAdress().toString());
        return convertView;
    }
}

    public static class ViewHolder {
        public TextView textView;
        public TextView textView2;
        public TextView textView3;
        public TextView textView4;
    }
}