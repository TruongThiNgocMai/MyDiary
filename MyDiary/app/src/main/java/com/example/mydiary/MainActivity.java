package com.example.mydiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity{

    AppDatabase db;
    Button btnAdd;
    RecyclerView rvDiary;
    DiaryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        btnAdd = findViewById(R.id.btnAdd);
        rvDiary = findViewById(R.id.recyclerDiary);
        rvDiary.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvDiary.setLayoutManager(new LinearLayoutManager(this));

        adapter = new DiaryAdapter();
        adapter.listener = new TodoAdapter.OnItemClickListener(){

            @Override
            public void onUpdateClick(int position) {
                openUpdateDiaryScreen(adapter.diaries.get(position));
            }

            @Override
            public void onDeleteClick(int position) {

            }
        };
        rvDiary.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddDiaryScreen();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        getDiaryFromDatabase();
    }

//    @SuppressLint("StaticFieldLeak")
//    private void getDiaryFromDatabase(){
//        new AsyncTask<Void, Void, List<DiaryStore>>(){
//
//            @Override
//            protected List<DiaryStore> doInBackground(Void... voids) {
//                return db.diaryDao().getAll();
//            }
//
//            @Override
//            protected void onPostExecute(List<DiaryStore> todos) {
//                super.onPostExecute(todos);
//                adapter.diaries = todos;
//                adapter.notifyDataSetChanged();
//            }
//        }.execute();
//    }

    private void openAddDiaryScreen() {
        startActivity(new Intent(MainActivity.this, AddDiaryActivity.class));
    }

    private void openUpdateDiaryScreen(DiaryStore todo){
        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
        intent.putExtra("id", todo.getId());
        intent.putExtra("title", todo.getTitle());
        intent.putExtra("content", todo.getContent());
        intent.putExtra("date", todo.getDatetime());
        startActivity(intent);
    }
}




