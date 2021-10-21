package com.example.lab5_milestone1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String usernameKey = "username";

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5_milestone1", Context.MODE_PRIVATE);


        if(!sharedPreferences.getString(usernameKey, "").equals("")){
            userPage();
            return;
        }else{
            setContentView(R.layout.activity_main);
        }

        setContentView(R.layout.activity_main);
    }

    public void loginFunc(View view) {
        EditText userNameInput = (EditText) findViewById(R.id.userNameInput);
        EditText passwordInput = (EditText) findViewById(R.id.passwordInput);

        String userName = userNameInput.getText().toString();
        String password = passwordInput.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5_milestone1", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", userName).apply();
        sharedPreferences.edit().putString("password", password).apply();


        userPage();
    }

    private void userPage() {

        Intent intent = new Intent(this, UserPage.class);
        startActivity(intent);
    }
}