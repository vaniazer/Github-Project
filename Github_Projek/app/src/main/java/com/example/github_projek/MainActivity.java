package com.example.github_projek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.github_projek.database.AkunDao;
import com.example.github_projek.database.AkunDatabase;
import com.example.github_projek.model.db.Akun;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private static final String KEY_USERNAME = "USERNAME";

    EditText user, pass;
    Button login, register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.btn_register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,DaftarActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = user.getText().toString();
                String password = pass.getText().toString();


                // Check if username, password is filled

                if(username.trim().length() == 0 || password.trim().length() == 0 ){
                    //JIka Username Kosong
                    Toast.makeText(getApplicationContext(),"Tidak Berhasil Login", Toast.LENGTH_LONG).show();
                }else{
                    AkunDatabase akunDatabase = AkunDatabase.getInstance(getApplicationContext());
                    AkunDao akunDao = akunDatabase.akunDao();

                    Akun akun = akunDao.login(username,password);
                    if (akun == null){
                        Toast.makeText(getApplicationContext(), "Akun Tidak Ada", Toast.LENGTH_SHORT).show();
                    }else{
                        sharedPreferences = getSharedPreferences("MY_PREF", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("LOGGED",true);
                        editor.putString(KEY_USERNAME,username);
                        editor.apply();

                        Intent intent = new Intent(getApplicationContext(), NavbarActivity.class);
                        Toast.makeText(getApplicationContext(),"Selamat Datang " + username, Toast.LENGTH_LONG).show();
                        MainActivity.this.startActivity(intent);
                        finish();
                    }
                }

            }
        });

    }
}