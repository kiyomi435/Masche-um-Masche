package com.example.masche_um_masche.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.masche_um_masche.data.entity.CrochetHook;

import java.util.List;

@Dao
public interface CrochetHookDao {
    @Insert
    void insert(CrochetHook crochetHook);

    @Update
    void update(CrochetHook crochetHook);

    @Delete
    void delete(CrochetHook crochetHook);

    @Query("SELECT * FROM crochethook ORDER BY name ASC")
    List<CrochetHook> getAll();
}

