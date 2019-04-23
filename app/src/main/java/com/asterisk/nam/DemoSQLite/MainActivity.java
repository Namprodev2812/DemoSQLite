package com.asterisk.nam.DemoSQLite;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ItemRecyclerView{

    private EditText mEditTextName;
    private EditText mEditTextPrice;
    private EditText mEditTextIndex;
    private Button mButtonSave, mButtonUpdate;
    private RecyclerView mRecyclerView;
    private List<Giohang> mListGiohang;
    private DBManagerGiohang mDBManager;
    private GiohangAdapter mGiohangAdapter;
    private Integer mAbc, mStt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        showValue();
        registerListener();
    }

    public void registerListener(){
        mButtonSave.setOnClickListener(this);
        mButtonUpdate.setOnClickListener(this);
    }

    public void saveValue(){
        Giohang giohang = createStudent();
        if (giohang != null) {
            mDBManager.addProduct(giohang);
        }
        mListGiohang.clear();
        mListGiohang.addAll(mDBManager.getAllProduct());
        mGiohangAdapter.notifyDataSetChanged();
        mRecyclerView.scrollToPosition(mListGiohang.size() - 1);
    }

    public void updateValue(){
        Giohang giohang = createStudentwithid(mStt);
        mDBManager.Update(giohang);
        if (mDBManager.Update(giohang) > 0) {
            mListGiohang.clear();
            mListGiohang.addAll(mDBManager.getAllProduct());
            mGiohangAdapter.notifyDataSetChanged();
            mButtonSave.setEnabled(true);
            mButtonUpdate.setEnabled(false);
        }
    }

    public void editValue(int i){
        mStt = i;
        Giohang giohang = mListGiohang.get(i);
        mEditTextName.setText(giohang.getmName());
        mEditTextPrice.setText(giohang.getMprice() + "");
        mEditTextIndex.setText(giohang.getmIndex() + "");
        mButtonSave.setEnabled(false);
        mButtonUpdate.setEnabled(true);
    }

    private void deleteValue(int i){
        mAbc = i;
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);
        dialog.show();
        Button button00, button11;
        button00 = (Button) dialog.findViewById(R.id.btn_ok);
        button11 = (Button) dialog.findViewById(R.id.btn_close);
        button00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                mDBManager.delete(mListGiohang.get(mAbc));
                Log.e("aa", mListGiohang.get(mAbc).getmId() + "");
                Log.e("222", "" + mDBManager.delete(mListGiohang.get(mAbc)));
                mListGiohang.clear();
                mListGiohang.addAll(mDBManager.getAllProduct());
                mGiohangAdapter.notifyDataSetChanged();
            }
        });

        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void showValue(){
        mDBManager = new DBManagerGiohang(this);
        mListGiohang = mDBManager.getAllProduct();
        getlistview(mListGiohang);
    }

    private Giohang createStudent() {
        String name = mEditTextName.getText().toString();
        double price = Double.parseDouble(mEditTextPrice.getText().toString());
        int index = Integer.parseInt(mEditTextIndex.getText().toString());
        Giohang giohang = new Giohang(name, price, index);
        return giohang;
    }

    private Giohang createStudentwithid(int stt) {
        String name = mEditTextName.getText().toString();
        double price = Double.parseDouble(mEditTextPrice.getText().toString());
        int index = Integer.parseInt(mEditTextIndex.getText().toString());
        Giohang giohang = new Giohang(mListGiohang.get(stt).getmId(), name, price, index);
        return giohang;
    }

    public void initViews() {
        mEditTextName = findViewById(R.id.edit_name);
        mEditTextPrice = findViewById(R.id.edit_price);
        mEditTextIndex = findViewById(R.id.edit_index);
        mButtonSave = findViewById(R.id.btn_Save);
        mButtonUpdate = findViewById(R.id.btn_update);
        mRecyclerView = findViewById(R.id.lv_item);
    }

    public void getlistview(List<Giohang> giohangs) {
        mGiohangAdapter = new GiohangAdapter(this,giohangs);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mGiohangAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Save:
                saveValue();
                break;
            case R.id.btn_update:
                updateValue();
                break;
        }
    }

    @Override
    public void onClick(View view, int position) {
        editValue(position);
    }

    @Override
    public void onLongClick(View view, int position) {
        deleteValue(position);
    }
}
