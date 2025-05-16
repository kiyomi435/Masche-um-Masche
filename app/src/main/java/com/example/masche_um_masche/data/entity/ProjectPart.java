package com.example.masche_um_masche.data.entity;

public class ProjectPart {
    private String name;
    private int rows;
    private int progress;

    public ProjectPart(String name, int rows, int progress) {
        this.name = name;
        this.rows = rows;
        this.progress = progress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
