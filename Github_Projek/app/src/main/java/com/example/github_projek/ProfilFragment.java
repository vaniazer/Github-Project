package com.example.github_projek;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.room.Delete;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.github_projek.database.AkunDao;
import com.example.github_projek.database.AkunDatabase;
import com.example.github_projek.model.db.Akun;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;



public class ProfilFragment extends Fragment{


    SharedPreferences sharedPreferences;
    private static final String KEY_USERNAME = "USERNAME";


    private TextView Name,Username, Telepon;
    private Button Ubah, Logout, Hapus;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences getPreferences = this.getActivity().getSharedPreferences("MY_PREF", Context.MODE_PRIVATE);
        String username = getPreferences.getString(KEY_USERNAME, "DEFAULT");

        AkunDatabase akunDatabase = AkunDatabase.getInstance(this.getContext());
        AkunDao akunDAO = akunDatabase.akunDao();
        Akun akun = akunDAO.checkUser(username);

        Username = view.findViewById(R.id.lblUsername);
        Username.setText(akun.getUsername());
        Name = view.findViewById(R.id.lblNama);
        Name.setText(akun.getNama());
        Telepon = view.findViewById(R.id.lblPhone);
        Telepon.setText(akun.getTelepon());

        Logout = view.findViewById(R.id.buttonLogOut);
        Ubah = view.findViewById(R.id.btn_edit);
        Hapus = view.findViewById(R.id.delete);






        Ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(),EditActivity.class);
                requireContext().startActivity(intent);
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Selamat Tinggal " + sharedPreferences.getString(KEY_USERNAME,"None"), Toast.LENGTH_LONG).show();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("LOGGED",false);
                editor.putString(KEY_USERNAME,"DEFAULT");
                editor.apply();

                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        Hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                akunDAO.deleteSingleData(username);
                SharedPreferences.Editor editor = getPreferences.edit();
                editor.putBoolean("LOGGED",false);
                editor.putString(KEY_USERNAME,"DEFAULT");
                editor.apply();

                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

    }





}