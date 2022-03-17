package com.example.loginsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText usernameSignup, passwordSignup, repasswordSignup;
    Button signUp, signIn;
    DBHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        usernameSignup = findViewById(R.id.usernameSignup);
        passwordSignup = findViewById(R.id.passwordSignup);
        repasswordSignup = findViewById(R.id.repasswordSignup);
        signUp = findViewById(R.id.signUp);
        signIn = findViewById(R.id.signIn);

        myDB = new DBHelper(this);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = usernameSignup.getText().toString();
                String pass = passwordSignup.getText().toString();
                String repass = repasswordSignup.getText().toString();

                if(user.equals("") || pass.equals("") || repass.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Điền tất cả các input", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(pass.equals(repass))
                    {
                        Boolean isValidUser = myDB.checkUsername(user);
                        if(isValidUser == false)
                        {
                            boolean result = myDB.insertData(user,pass);
                            if(result == true)
                            {
                                Toast.makeText(MainActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                                startActivity(intent);

                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

       signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignInActivity.class);
                startActivity(intent);
            }
       });


    }
}