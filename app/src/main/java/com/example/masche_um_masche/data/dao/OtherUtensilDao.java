package com.example.masche_um_masche.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.masche_um_masche.data.entity.OtherUtensil;

import java.util.List;

@Dao
public interface OtherUtensilDao {
    @Insert
    void insert(OtherUtensil otherUtensil);

    @Update
    void update(OtherUtensil otherUtensil);

    @Delete
    void delete(OtherUtensil otherUtensil);

    @Query("SELECT * FROM OtherUtensil ORDER BY name ASC")
    List<OtherUtensil> getAll();
}
