package com.example.masche_um_masche.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.masche_um_masche.data.entity.Project;

import java.util.List;

@Dao
public interface ProjectDao {
    @Insert
    void insert(Project project);

    @Query("SELECT * FROM Project")
    List<Project> getAll();

    @Update
    void update(Project project);

    @Query("SELECT * FROM project WHERE id = :id LIMIT 1")
    Project getById(int id);

    @Delete
    void delete(Project project);
}
