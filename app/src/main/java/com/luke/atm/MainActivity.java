package com.luke.atm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    private static final int FUNC_LOGIN = 6;
    boolean logon = false;
    String[] data = {"餘額查詢", "交易明細", "最新消息", "投資理財", "離開"};
    int[] icons = {R.drawable.icon_search,
    R.drawable.icon_list,
    R.drawable.icon_news,
    R.drawable.icon_money,
    R.drawable.icon_quit};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!logon) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, FUNC_LOGIN);
        }
        //startActivity(new Intent(this, TestActivity.class));
        findviews();

        ListView lv = (ListView) findViewById(R.id.listView);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        lv.setAdapter(arrayAdapter);
        //lv.setOnItemClickListener(this);
//Spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        //標準用法 下兩行
//        String[] spinArray = getResources().getStringArray(R.array.spinner_array);
//        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, spinArray);

        //改用類別層級 建立一個Resource 在Value的Array.xml中, 再抓出來
        ArrayAdapter arrayAdapter2 = ArrayAdapter.createFromResource(this, R.array.spinner_array,
                android.R.layout.simple_spinner_item);
        //選下拉時的Layout
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(arrayAdapter2);

        spinner.setOnItemSelectedListener(this);
//Spinner End
//GridView
        GridView gridView = (GridView) findViewById(R.id.gridView);

        IconAdapter iconAdapter = new IconAdapter();
        gridView.setAdapter(iconAdapter);
        gridView.setOnItemClickListener(this);
//GridView End

    }

    private void findviews() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.action_settings:
                Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_exit:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FUNC_LOGIN) {

            if (resultCode == RESULT_OK) {
                Toast.makeText(this, R.string.Result_OK, Toast.LENGTH_LONG).show();
                String userid = data.getStringExtra("USERID");
                String userpwd = data.getStringExtra("USERPWD");
                getSharedPreferences("atm", MODE_PRIVATE)
                        .edit()
                        .putString("USERID", userid)
                        .commit();

                Log.d("LOGIN:", userid + "," + userpwd);
            } else {
                finish();
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String[] spinArray = getResources().getStringArray(R.array.spinner_array);
        Log.d("Select Spinner", position+","+spinArray[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long itemId) { //position 抓位置
//實作多個同方法時所採用的方式                                                               //Long 抓itemId 要轉型int
//        int parentId = parent.getId();
//        switch (parentId){
//            case R.id.gridView:
//                break;
//            case R.id.listView:
//                break;
//        }
        switch ((int)itemId){        //GridView
            case R.drawable.icon_list:
                break;
            case R.drawable.icon_money:
                startActivity(new Intent(MainActivity.this, FinanceActivity.class));
                break;
            case R.drawable.icon_news:
                break;
            case R.drawable.icon_quit:
                break;
            case R.drawable.icon_search:
                finish();
                break;
        }
    }
    class IconAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return icons.length; //長度
        }

        @Override
        public Object getItem(int position) {
            return data[position];  //傳String 或 Int都可以, 目前傳String 給他
        }

        @Override
        public long getItemId(int position) {

            return icons[position]; //給position
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (convertView ==null){
                view = getLayoutInflater().inflate(R.layout.icon, null);
                TextView tv = (TextView) view.findViewById(R.id.icon_text);
                ImageView iv = (ImageView) view.findViewById(R.id.icon_image);
                tv.setText(data[position]);
                iv.setImageResource(icons[position]);

                convertView = view;
            }
            return convertView;
        }
    }
}
