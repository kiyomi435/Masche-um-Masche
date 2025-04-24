package com.example.masche_um_masche.objects;

import java.util.List;

public class Project {
    public String name;
    public List<ProjectPart> parts;
    public int progress;

    public Project(String name, List<ProjectPart> parts, int progress) {
        this.name = name;
        this.parts = parts;
        this.progress = progress;
    }
}
