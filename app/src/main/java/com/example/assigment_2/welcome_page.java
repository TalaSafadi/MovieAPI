package com.example.assigment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

public class welcome_page extends AppCompatActivity {
    private Button signUp;
    private Button Login;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomepage2);
        Login=findViewById(R.id.login_buttonFirstPage2);
        signUp=findViewById(R.id.RegisterFirstPage2);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(welcome_page.this,RegisterPage.class);
                startActivity(intent);

            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(welcome_page.this,Login_page.class);
                startActivity(intent);

            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        loadFlag();

        if(flag){
            Intent intent=new Intent(welcome_page.this,display_page.class);
            startActivity(intent);
        }

    }
    private void loadFlag(){
        SharedPreferences sharedPreferences= getSharedPreferences("sharedPreferences",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        String json=sharedPreferences.getString("flag",null);

        if(json!=null){
            Gson gson = new Gson();
            // Type type = new TypeToken<ArrayList<User>>(){}.getType();

            flag=gson.fromJson(json,Boolean.class);
           // Toast.makeText(this, "flag "+flag, Toast.LENGTH_SHORT).show();

            // Toast.makeText(this, "email"+users.get(position).UserName+" "+users.get(position).UserName, Toast.LENGTH_SHORT).show();

        }else{
            //Toast.makeText(this, "no user found no data ", Toast.LENGTH_SHORT).show();

        }



    }

}