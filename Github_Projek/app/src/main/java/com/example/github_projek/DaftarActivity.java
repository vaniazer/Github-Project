package com.example.github_projek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.github_projek.database.AkunDao;
import com.example.github_projek.database.AkunDatabase;
import com.example.github_projek.model.db.Akun;

public class DaftarActivity extends AppCompatActivity {
    public static final int REQUEST_ADD = 100;
    public static final int RESULT_ADD = 110;
    public static final String EXTRA_ADD = "extra_add";

    private EditText daftar_username, daftar_nama, daftar_password, daftar_telepon;
    private Button kembali, daftar;
    private AkunDao akunDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Daftar");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        akunDao = AkunDatabase.getInstance(this).akunDao();

        daftar_username = findViewById(R.id.daftar_username);
        daftar_nama = findViewById(R.id.daftar_nama);
        daftar_password = findViewById(R.id.daftar_password);
        daftar_telepon = findViewById(R.id.daftar_telepon);
        kembali = findViewById(R.id.btn_kembali);
        daftar = findViewById(R.id.btn_simpan);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DaftarActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usname = daftar_username.getText().toString();
                String name = daftar_nama.getText().toString();
                String pass = daftar_password.getText().toString();
                String tel = daftar_telepon.getText().toString();

                Akun akun = new Akun(usname, name, pass, tel);
                akunDao.insertData(akun);
                finish();
            }
        });
    }
}