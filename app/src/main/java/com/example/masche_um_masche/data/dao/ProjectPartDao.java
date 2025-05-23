package com.example.masche_um_masche.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.masche_um_masche.data.entity.ProjectPart;

import java.util.List;

@Dao
public interface ProjectPartDao {
    @Insert
    void insert(ProjectPart part);

    @Query("SELECT * FROM ProjectPart WHERE projectId = :projectId")
    List<ProjectPart> getAllByProjectId(int projectId);
}
