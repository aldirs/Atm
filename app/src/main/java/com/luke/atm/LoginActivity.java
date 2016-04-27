package com.luke.atm;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText et_id;
    private EditText et_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();
        String userId = getSharedPreferences("atm", MODE_PRIVATE).getString("USERID", "");
        et_id.setText(userId);
    }

    private void findViews() {
        et_id = (EditText) findViewById(R.id.et_Id);
        et_pwd = (EditText) findViewById(R.id.et_Pwd);
    }

    public void login(View view){
        String userId = et_id.getText().toString();
        String userPrd = et_pwd.getText().toString();
        if (userId.equals("jack") && userPrd.equals("1234")){
            Toast.makeText(this, "登入成功", Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            intent.putExtra("USERID",userId);
            intent.putExtra("USERPWD", userPrd);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
