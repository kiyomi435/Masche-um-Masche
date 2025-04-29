package com.example.masche_um_masche.objects;

import java.util.List;

public class Project {
    private String name;
    private List<ProjectPart> parts;
    private int currentRows;
    private int allRows;

    public Project(String name, List<ProjectPart> parts, int allRows) {
        this.name = name;
        this.parts = parts;
        this.allRows = allRows;
        this.currentRows = 0;
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
    }

    public int getCurrentRows() {
        return currentRows;
    }

    public void setCurrentRows(int currentRows) {
        this.currentRows = currentRows;
    }

    public int getAllRows() {
        return allRows;
    }

    public void setAllRows(int allRows) {
        this.allRows = allRows;
    }

    public int getProgress() {
        if (allRows == 0) return 0;
        return (int) ((currentRows / (float) allRows) * 100);
    }
}
