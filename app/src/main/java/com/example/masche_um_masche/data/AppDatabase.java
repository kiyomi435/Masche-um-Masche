package com.example.masche_um_masche.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.masche_um_masche.data.dao.ProjectDao;
import com.example.masche_um_masche.data.dao.ProjectPartDao;
import com.example.masche_um_masche.data.entity.Project;
import com.example.masche_um_masche.data.entity.ProjectPart;

@Database(entities = {Project.class, ProjectPart.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProjectDao projectDao();
    public abstract ProjectPartDao projectPartDao();

    private static volatile AppDatabase INSTANCE;


    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "masche_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
