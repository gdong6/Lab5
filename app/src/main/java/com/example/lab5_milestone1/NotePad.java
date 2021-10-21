package com.example.lab5_milestone1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NotePad extends AppCompatActivity {

    int noteid = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_pad);

        EditText textField = findViewById(R.id.writeSpace);
        Intent intent = getIntent();
        noteid = intent.getIntExtra("noteid", -1);

        if(noteid != -1){
            Note note = UserPage.notes.get(noteid);
            String noteContent = note.getContent();
            textField.setText(noteContent);
        }


        String log = "" + noteid;
        Log.i("noteid",log);


    }

    public void saveFunction(View view) {
        EditText textField = findViewById(R.id.writeSpace);
        String content = textField.getText().toString();

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);

        DBHelper dbhelper = new DBHelper(sqLiteDatabase);

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5_milestone1", Context.MODE_PRIVATE);
        String username  = sharedPreferences.getString("username","");


        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if (noteid == -1){ //add new note
            title = "NOTE_" + (UserPage.notes.size() + 1);
            dbhelper.saveNotes(username, title, content, date);

        }else{
            title = "NOTE_" + (noteid + 1);
            dbhelper.updateNotes(title, date, content, username);
        }


        Intent intent = new Intent(this, UserPage.class);
        startActivity(intent);
    }
}