package com.example.mydiary;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//Query syntax
@Dao
public interface DiaryDao {

    @Query("SELECT * FROM Diary")
    List<Diary> getAll();

    @Insert
    void insertAll(DiaryStore diaries);

    @Delete
    void delete(Diary... diaries);

    @Update
    void update(DiaryStore diary);

}

