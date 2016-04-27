package com.luke.atm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FUNC_LOGIN){
            if (resultCode == RESULT_OK){
                Toast.makeText(this, R.string.Result_OK, Toast.LENGTH_LONG).show();
            }else {
                finish();
            }
        }
    }
}
