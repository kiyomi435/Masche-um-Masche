package com.example.masche_um_masche.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.masche_um_masche.data.entity.KnittingNeedle;

import java.util.List;

@Dao
public interface KnittingNeedleDao {
    @Insert
    void insert(KnittingNeedle knittingNeedle);

    @Update
    void update(KnittingNeedle knittingNeedle);

    @Delete
    void delete(KnittingNeedle knittingNeedle);

    @Query("SELECT * FROM knittingneedle ORDER BY name ASC")
    List<KnittingNeedle> getAll();
}

