package com.example.doan3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.doan3.R;
import com.example.doan3.object.BoTruyen;

import java.util.ArrayList;
import java.util.List;

public class BoTruyenAdapter extends ArrayAdapter<BoTruyen> {
    private  Context ct;
    private ArrayList<BoTruyen> arr;
    public BoTruyenAdapter(@NonNull Context context, int resource, @NonNull List<BoTruyen> objects) {
        super(context, resource, objects);
        this.ct= context;
        this.arr= new ArrayList<>(objects);
    }

    public void sortTruyen(String s){
        s=s.toUpperCase();
        int k =0;
        for (int i=0;i<arr.size();i++){
            BoTruyen t =arr.get(i);
            String ten =t.getTenTruyen().toUpperCase();
            if(ten.indexOf(s)>=0){
                arr.set(i,arr.get(k));
                arr.set(k,t);
                k++;
            }
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inflater = (LayoutInflater)ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_truyen, null);
        }
        if (arr.size()>0){
            BoTruyen boTruyen= this.arr.get(position);

            TextView tenTenTruyen = convertView.findViewById(R.id.txvTenTruyen);
            TextView tenTenChap = convertView.findViewById(R.id.txvTenChap);
            ImageView imgAnhtruyen= convertView.findViewById(R.id.imgAnhTruyen);
            tenTenTruyen.setText(boTruyen.getTenTruyen());
            tenTenChap.setText(boTruyen.getTenChap());
            Glide.with(this.ct).load(boTruyen.getLinkAnh()).into(imgAnhtruyen);
        }
        return convertView;
    }
}
