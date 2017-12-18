package com.example.meill.dijoncenter.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.meill.dijoncenter.Models.Parcours;
import com.example.meill.dijoncenter.R;

import java.util.ArrayList;

/**
 * Created by meill on 18/12/2017.
 */

public class DataParcoursAdapter {


        ArrayList<Parcours> dataParcours;
        LayoutInflater mInflater;
        Adapter adapter;
        public DataParcoursAdapter(ArrayList<Parcours> Parcours,LayoutInflater Inflater)
        {
            this.dataParcours=Parcours;
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

        public ArrayList<Parcours> getDataLieu() {
            return dataParcours;
        }
        public void setDataLieu(ArrayList<Parcours> dataParcours) {
            this.dataParcours = dataParcours;
        }


        public class Adapter extends BaseAdapter {

            @Override
            public int getCount() {
                return dataParcours.size();
            }

            @Override
            public Parcours getItem(int position) {
                return dataParcours.get(position);
            }

            @Override
            public long getItemId(int position) {

                return dataParcours.get(position).getIdParcours() & Long.MAX_VALUE;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                com.example.meill.dijoncenter.Adapter.DataLieuAdapter.ViewHolder holder = null;
                //mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                if (convertView == null) {
                    convertView = mInflater.inflate(R.layout.layout, null);
                    holder = new com.example.meill.dijoncenter.Adapter.DataLieuAdapter.ViewHolder();
                    holder.textView = (TextView)convertView.findViewById(R.id.textView);
                    holder.textView2 = (TextView)convertView.findViewById(R.id.textView2);
                    holder.textView3 = (TextView)convertView.findViewById(R.id.textView3);
                    holder.textView4 = (TextView)convertView.findViewById(R.id.textView4);
                    convertView.setTag(holder);
                }
                else {
                    holder = (com.example.meill.dijoncenter.Adapter.DataLieuAdapter.ViewHolder)convertView.getTag();
                }

                holder.textView.setText(dataParcours.get(position).getNom().toString());
                holder.textView2.setText("");
                holder.textView3.setText(dataParcours.get(position).getDATETIME().toString());
                holder.textView4.setText("");
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
