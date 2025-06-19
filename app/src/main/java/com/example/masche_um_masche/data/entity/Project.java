package com.example.masche_um_masche.data.entity;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Project {
    @PrimaryKey(autoGenerate = true)
    public int id;
    private String name;
    @Nullable
    public String fileUri; // Hier wird der Pfad (URI) gespeichert
    @Ignore
    private List<ProjectPart> parts = new ArrayList<>();    private int currentRows;
    private int allRows;

    public Project() {
        // Leerer Konstruktor für Room
    }

    @Ignore
    public Project(String name) {
        this.name = name;
        this.allRows = 0;
        this.currentRows = 0;
    }

    @Ignore
    public Project(String name, String fileUri) {
        this.name = name;
        this.fileUri = fileUri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProjectPart> getParts() {
        return parts;
    }

    public void setParts(List<ProjectPart> parts) {
        this.parts = parts;
        if (parts.isEmpty()) {
            this.allRows = 1;
            this.currentRows = 0;
        } else if (!parts.isEmpty()) {
            this.allRows = parts.stream().mapToInt(ProjectPart::getAllRows).sum();
            this.currentRows = parts.stream().mapToInt(ProjectPart::getCurrentRows).sum();
        }
    }

    public int getCurrentRows() {
        int total = 0;
        for (ProjectPart part : parts) {
            total += part.getCurrentRows();
        }
        return total;
    }

    public void setCurrentRows(int currentRows) {
        this.currentRows = currentRows;
    }

    public int getAllRows() {
        int total = 0;
        for (ProjectPart part : parts) {
            total += part.getAllRows();
        }
        return total;
    }

    public void setAllRows(int allRows) {
        this.allRows = allRows;
    }

    public int getProgress() {
        if (allRows == 0) return 0;
        return (int) ((currentRows / (float) allRows) * 100);
    }
}
