package com.example.user.loanbook;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText et_userName, et_passWord;
    Button btn_register;
    CheckBox chk_showPassWord;
    String name, password;
    Context context;

    MyDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;

        btn_register = (Button) findViewById(R.id.btn_register);
        et_userName = (EditText) findViewById(R.id.et_user_name);
        et_passWord = (EditText) findViewById(R.id.et_user_password);
        chk_showPassWord = (CheckBox) findViewById(R.id.chk_checkbox);

        chk_showPassWord.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    et_passWord.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    et_passWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

            }
        });

        db = new MyDb(this);
        Cursor cursor = db.getUser();
        if (cursor.moveToFirst()) {
            //Toast.makeText(LoginActivity.this, "GET DATA FROM DB" + cursor.getString(0), Toast.LENGTH_LONG).show();


            //startActivity(new Intent(LoginActivity.this, LocationActivity.class));

            Intent intent = new Intent(context, MainActivity.class);
           // intent.putExtra("emp_id", cursor.getString(1));
            startActivity(intent);
        }

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = et_userName.getText().toString().trim();
                password = et_passWord.getText().toString().trim();
                if(password.length()>=8){
                db.insetUser(name, password);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginActivity.this,"Password must be 8 characters or more",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}




