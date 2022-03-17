package com.example.loginsqlite;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {
    ImageView logo;
    EditText username, password;
    Button login;
    DBHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        logo = findViewById(R.id.logo);
        logo.setImageResource(R.drawable.logo2);

        myDB = new DBHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if(user.equals("") || pass.equals(""))
                {
                    Toast.makeText(SignInActivity.this, "Nhập thiếu tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean result = myDB.checkUsernamePassword(user,pass);
                    if(result)
                    {
                        Intent intent = new Intent(getApplicationContext(),Home.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(SignInActivity.this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }
}
