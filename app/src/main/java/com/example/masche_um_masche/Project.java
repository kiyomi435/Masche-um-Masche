package com.example.masche_um_masche;

import java.util.List;

public class Project {
    String name;
    List<ProjectPart> parts;
    int progress;

    public Project(String name, List<ProjectPart> parts, int progress) {
        this.name = name;
        this.parts = parts;
        this.progress = progress;
    }
}
