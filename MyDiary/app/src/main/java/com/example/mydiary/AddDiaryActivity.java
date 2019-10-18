package com.example.mydiary;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class AddDiaryActivity extends AppCompatActivity {
    AppDatabase db;
    EditText edtTitle;
    EditText edtDate;
    EditText editContent;
    Button btnSave;
    Button btnBack;

    @SuppressLint({"StaticFieldLeak", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary);

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDiaryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        edtTitle = findViewById(R.id.txtTitle);
        edtDate = findViewById(R.id.txtDate);
        editContent = findViewById(R.id.txtContent);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDiaryToDatabase();
                finish();
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private void addDiaryToDatabase() {
        final String title = edtTitle.getText().toString();
        final String date = edtDate.getText().toString();
        final String content = editContent.getText().toString();

        if (title.isEmpty()) {
            Toast.makeText(this, "Title must not null", Toast.LENGTH_SHORT).show();
            return;
        }

        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                DiaryStore newDiary = new DiaryStore();
                db.diaryDao().insertAll(newDiary);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(AddDiaryActivity.this, "New todo added", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }
}

