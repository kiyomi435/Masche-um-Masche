package com.example.masche_um_masche.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.masche_um_masche.data.dao.CrochetHookDao;
import com.example.masche_um_masche.data.dao.KnittingNeedleDao;
import com.example.masche_um_masche.data.dao.OtherUtensilDao;
import com.example.masche_um_masche.data.dao.ProjectDao;
import com.example.masche_um_masche.data.dao.ProjectPartDao;
import com.example.masche_um_masche.data.dao.WoolDao;
import com.example.masche_um_masche.data.entity.CrochetHook;
import com.example.masche_um_masche.data.entity.KnittingNeedle;
import com.example.masche_um_masche.data.entity.OtherUtensil;
import com.example.masche_um_masche.data.entity.Project;
import com.example.masche_um_masche.data.entity.ProjectPart;
import com.example.masche_um_masche.data.entity.Wool;

@Database(entities = {Project.class, ProjectPart.class, Wool.class, KnittingNeedle.class, CrochetHook.class, OtherUtensil.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProjectDao projectDao();
    public abstract ProjectPartDao projectPartDao();
    public abstract WoolDao woolDao();
    public abstract KnittingNeedleDao knittingNeedleDao();
    public abstract CrochetHookDao crochetHookDao();
    public abstract OtherUtensilDao otherUtensilDao();

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
