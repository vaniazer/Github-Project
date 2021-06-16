package com.example.github_projek;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;


public class ProfilFragment extends Fragment implements View.OnClickListener {


    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "my_pref";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";

    Button Logout;
    TextView Username, Name, Telepon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Username = view.findViewById(R.id.lblUsername);
        Name = view.findViewById(R.id.lblNama);
        Telepon = view.findViewById(R.id.lblPhone);
        Logout = view.findViewById(R.id.buttonLogOut);

        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        ProsesProfil();

        Logout.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ProsesProfil();
    }

    @SuppressLint("SetTextI18n")
    private void ProsesProfil(){
        String Namaku = sharedPreferences.getString(KEY_USERNAME,"Tidak Ada Data");
        String User_name = sharedPreferences.getString(KEY_NAME,"Tidak Ada Data");
        String Phoneku = sharedPreferences.getString(KEY_PHONE,"Tidak Ada Data");

        Username.setText(": " + Namaku);
        Name.setText(User_name);
        Telepon.setText(": " + Phoneku);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
           

            case R.id.buttonLogOut:
                Toast.makeText(getContext(),"Selamat Tinggal " + sharedPreferences.getString(KEY_USERNAME,"None"), Toast.LENGTH_LONG).show();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent i = new Intent(getContext(), MainActivity.class);
                requireActivity().finish();
                requireContext().startActivity(i);
                break;
        }
    }
}