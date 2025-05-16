package com.example.masche_um_masche.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.masche_um_masche.data.dao.ProjectDao;
import com.example.masche_um_masche.data.entity.Project;

@Database(entities = {Project.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProjectDao projectDao();
}
