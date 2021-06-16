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
    private static final String SHARED_PREF_NAME = "my_pref";
    private static final String KEY_USERNAME = "username";

    EditText user, pass;
    Button login, register;
    AkunDao akunDao;

    String dbUsername = "firstInitial", dbEmail = "firstInitial", dbPassword = "firstInitial";
    String sUsername = "", sPassword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.btn_register);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        String sharedUsername = sharedPreferences.getString(KEY_USERNAME,null);

        if (sharedUsername != null){
            Intent intent = new Intent(getApplicationContext(), NavbarActivity.class);
            startActivity(intent);
            finish();
        }

        akunDao = AkunDatabase.getInstance(this).akunDao();

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
                if(username.trim().length() > 0 && password.trim().length() > 0){
                    checkUser();
                    if(username.equals(sUsername) && password.equals(sPassword)) {
                        //Diatur bahwa passwordnya admin
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(KEY_USERNAME, user.getText().toString());
                        editor.apply();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        Toast.makeText(getApplicationContext(),"Selamat Datang " + username, Toast.LENGTH_LONG).show();
                        startActivity(intent);
                        finish();

                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Tidak Berhasil Login", Toast.LENGTH_LONG).show();
                    }
                }else if(username.trim().length() == 0 ){
                    //JIka Username Kosong
                    Toast.makeText(getApplicationContext(),"Tidak Berhasil Login", Toast.LENGTH_LONG).show();
                }
                else if(password.trim().length() == 0 ){
                    //Jika Password Kosong
                    Toast.makeText(getApplicationContext(),"Tidak Berhasil Login", Toast.LENGTH_LONG).show();
                }
            }

            private void checkUser() {
                dbUsername = "";
                dbEmail = "";
                dbPassword = "";

                sUsername = user.getText().toString();
                sPassword = pass.getText().toString();

                List<Akun> akuns = akunDao.getAllData();
                for (Akun data : akuns ){
                    System.out.println("Output");
                    if((data.getUsername().equals(sUsername) || dbPassword.equals(sPassword))){
                        System.out.println("Masuk");
                        dbUsername = data.getUsername();
                        dbPassword = data.getPassword();
                        break;
                    }
                }
            }
        });

    }
}