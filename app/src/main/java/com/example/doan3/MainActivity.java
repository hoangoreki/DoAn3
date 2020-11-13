package com.example.doan3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.doan3.adapter.BoTruyenAdapter;
import com.example.doan3.api.ApiLayTruyen;
import com.example.doan3.interfaces.LayTruyenVe;
import com.example.doan3.object.BoTruyen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LayTruyenVe {
GridView gdvDSTruyen;
BoTruyenAdapter adapter;
ArrayList<BoTruyen>boTruyenArrayList;
EditText edtTimKiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        anhXa();
        setClik();
        setup();
        new ApiLayTruyen(this).execute();
    }
    private void init(){
        boTruyenArrayList = new ArrayList<>();
        boTruyenArrayList.add(new BoTruyen("SOLO LEVELING - THĂNG CẤP MỘT MÌNH"," Chapter 260","https://a.wattpad.com/cover/191015220-288-k676384.jpg"));
        boTruyenArrayList.add(new BoTruyen("Atack On Titan","Chapter 133","https://a.wattpad.com/cover/184443856-352-k170068.jpg"));
        boTruyenArrayList.add(new BoTruyen("Vua Sinh Tồn - PUBG","Chapter 102","https://a.wattpad.com/cover/179573800-352-k899077.jpg"));
        boTruyenArrayList.add(new BoTruyen("TIÊU DIỆT ĐẤNG CỨU THẾ","Chapter 37","https://media.hamtruyen.vn/Pictures/Truyen/Large/ke-giet-anh-hung.jpg"));


        adapter= new BoTruyenAdapter(this,0, boTruyenArrayList);
    };
    private void anhXa(){
        gdvDSTruyen = findViewById(R.id.gdvDSTruyen);
        edtTimKiem = findViewById(R.id.edtTimKiem);

    };
    private void setup(){
        gdvDSTruyen.setAdapter(adapter);
    };
    private void setClik(){
        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = edtTimKiem.getText().toString();
                adapter.sortTruyen(s);
            }
        });
    };

    @Override
    public void batDau() {
        Toast.makeText(this,"Dang Lay Ve",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThuc(String data) {
        try {
            boTruyenArrayList.clear();
            JSONArray arr = new JSONArray(data);
            for(int i=0;i<arr.length();i++){
                JSONObject o = arr.getJSONObject(i);
                boTruyenArrayList.add(new BoTruyen(o));
            }
            adapter= new BoTruyenAdapter(this,0, boTruyenArrayList);
            gdvDSTruyen.setAdapter(adapter);
        }catch (JSONException e ){

        }
    }

    @Override
    public void biLoi() {
        Toast.makeText(this,"Loi ket noi",Toast.LENGTH_LONG).show();
    }

    public void update(View view) {
        new ApiLayTruyen(this).execute();
    }
}