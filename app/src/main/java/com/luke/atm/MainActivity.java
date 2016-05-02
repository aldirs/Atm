package com.luke.atm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    private static final int FUNC_LOGIN = 6;
    boolean logon = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!logon){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, FUNC_LOGIN);
        }
        //startActivity(new Intent(this, TestActivity.class));
        findviews();
        ListView lv = (ListView) findViewById(R.id.listView);
        String[] data = {"AAAA", "BBBB", "CCCC","DDDD"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        lv.setAdapter(arrayAdapter);

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
        switch (itemId){
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
        if (requestCode == FUNC_LOGIN){

            if (resultCode == RESULT_OK){
                Toast.makeText(this, R.string.Result_OK, Toast.LENGTH_LONG).show();
                String userid = data.getStringExtra("USERID");
                String userpwd = data.getStringExtra("USERPWD");
                getSharedPreferences("atm", MODE_PRIVATE)
                        .edit()
                        .putString("USERID", userid)
                        .commit();

                Log.d("LOGIN:", userid+","+userpwd);
            }else {
                finish();
            }
        }
    }
}
