package com.example.turismapp.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RecenzieDao {

    @Query("select * from recenzii")
    List<Recenzie> getAll();

    @Insert
    long insert(Recenzie recenzie);

    @Update
    int update(Recenzie recenzie);

    @Delete
    int delete(Recenzie recenzie);
}