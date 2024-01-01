package com.example.assigment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.gson.Gson;

public class display_page extends AppCompatActivity {
    private Button Movie;
    private Button show;
    private Button LogOUT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_page);
        Movie=findViewById(R.id.MovieDisplayPage);
        show=findViewById(R.id.showsDisplayPage);
        LogOUT=findViewById(R.id.LogOutDisplayPage);

        LogOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences= getSharedPreferences("sharedPreferences",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                Gson gson = new Gson();
                String json =gson.toJson(false);
                editor.putString("flag",json);
                editor.apply();
                Intent intent=new Intent(display_page.this,welcome_page.class);
                startActivity(intent);
            }
        });
        Movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(display_page.this,MainActivity.class);
                startActivity(intent);
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(display_page.this,shows_list.class);
                startActivity(intent);
            }
        });



    }

}