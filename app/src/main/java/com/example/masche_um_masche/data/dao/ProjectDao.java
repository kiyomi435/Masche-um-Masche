package com.example.masche_um_masche.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.masche_um_masche.data.entity.Project;

import java.util.List;

@Dao
public interface ProjectDao {
    @Insert
    long insert(Project project);

    @Query("SELECT * FROM Project")
    List<Project> getAll();

    @Delete
    void delete(Project project);
}
