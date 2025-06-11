package com.example.masche_um_masche.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.masche_um_masche.data.entity.Wool;

import java.util.List;

@Dao
public interface WoolDao {
    @Insert
    void insert(Wool wool);

    @Update
    void update(Wool wool);

    @Delete
    void delete(Wool wool);

    @Query("SELECT * FROM wool ORDER BY name ASC")
    List<Wool> getAll();
}

