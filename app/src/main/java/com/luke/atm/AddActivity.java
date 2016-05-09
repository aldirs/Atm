package com.luke.atm;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    private MyDBHelper dbHelper;
    private EditText ed_date;
    private EditText ed_info;
    private EditText ed_amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        findViews();
        dbHelper = new MyDBHelper(this);  //寫在建構子上面的Default Value
    }

    private void findViews() {
        ed_date = (EditText) findViewById(R.id.ed_date);
        ed_info = (EditText) findViewById(R.id.ed_info);
        ed_amount = (EditText) findViewById(R.id.ed_amount);
    }

    public void add(View v){
        String eddate = ed_date.getText().toString();
        String edinfo = ed_info.getText().toString();
        int edamount = Integer.parseInt(ed_amount.getText().toString());
        ContentValues values = new ContentValues();
        values.put("cdate",eddate);
        values.put("info", edinfo);
        values.put("amount", edamount);

        long dbResult = dbHelper.getWritableDatabase().insert("exp", null, values);
        Log.d("db Log:", dbResult+"");

    }
}
