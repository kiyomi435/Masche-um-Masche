package com.example.masche_um_masche.objects;

import java.util.List;

public class Project {
    private String name;
    private List<ProjectPart> parts;
    private int progress;

    public Project(String name, List<ProjectPart> parts, int progress) {
        this.name = name;
        this.parts = parts;
        this.progress = progress;
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

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
