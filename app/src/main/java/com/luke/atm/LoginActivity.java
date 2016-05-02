package com.luke.atm;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText et_id;
    private EditText et_pwd;
    private CheckBox cb_uid;
    private boolean checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();
        String userId = getSharedPreferences("atm", MODE_PRIVATE).getString("USERID", "");
        et_id.setText(userId);

        cb_uid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                getSharedPreferences("atm", MODE_PRIVATE)
                        .edit()
                        .putBoolean("pref_Check", isChecked)
                        .commit();

            }
        });

        checked = getSharedPreferences("atm", MODE_PRIVATE)
                .getBoolean("pref_Check",false);
        cb_uid.setChecked(checked);

    }

    private void findViews() {
        et_id = (EditText) findViewById(R.id.et_Id);
        et_pwd = (EditText) findViewById(R.id.et_Pwd);
        cb_uid = (CheckBox) findViewById(R.id.cb_Uid);
    }

    public void login(View view) {
        String userId = et_id.getText().toString();
        String userPrd = et_pwd.getText().toString();
        if (userId.equals("jack") && userPrd.equals("1234")) {
            if (checked){
                getSharedPreferences("atm", MODE_PRIVATE)
                        .edit()
                        .putString("USERID", userId)
                        .commit();
            }

            Toast.makeText(this, "登入成功", Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            intent.putExtra("USERID", userId);
            intent.putExtra("USERPWD", userPrd);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
