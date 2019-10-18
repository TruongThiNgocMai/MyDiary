package com.example.mydiary;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class UpdateActivity extends AppCompatActivity {

    AppDatabase db;
    EditText editDiary;
    Button btnUpdate;
    int diaryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        editDiary = findViewById(R.id.edit_text_Title);
        btnUpdate = findViewById(R.id.btn_Update);

        int id = getIntent().getIntExtra("id", 0);
        diaryId = id;
        String task = getIntent().getStringExtra("task");

        editDiary.setText(task);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTodoToDatabase();

            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private void updateTodoToDatabase() {
        final String diaryTitle = editDiary.getText().toString();
        final String diaryContent = editDiary.getText().toString();
        final String diaryDate = editDiary.getText().toString();

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                DiaryStore newDiary = new DiaryStore();
                newDiary.setTitle(diaryTitle);
                newDiary.setId(diaryId); // thinking about why we need to set id here
                newDiary.setDatetime(diaryContent); // thinking about why we need to set id here
                newDiary.setDatetime(diaryDate); // thinking about why we need to set id here
                db.diaryDao().update(newDiary);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                showSuccessDialog();
            }
        }.execute();
    }

    private void showSuccessDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Message")
                .setMessage("Update Success")
                .setPositiveButton("Got it", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .show();
    }
}
