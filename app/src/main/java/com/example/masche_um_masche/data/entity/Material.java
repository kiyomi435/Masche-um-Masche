package com.example.masche_um_masche.data.entity;

import androidx.room.Ignore;

public abstract class Material {
    private String name;
    private String materialType;
    private String lagerort;
    private String notizen;

    @Ignore
    public Material(String name, String materialType, String lagerort, String notizen) {
        this.name = name;
        this.materialType = materialType;
        this.lagerort = lagerort;
        this.notizen = notizen;
    }

    public Material() {} // Default-Konstruktor für Room

    // Getter und Setter
    public String getName() { return name; }
    public String getMaterialType() { return materialType; }
    public String getLagerort() { return lagerort; }
    public String getNotizen() { return notizen; }

    public void setName(String name) { this.name = name; }
    public void setMaterialType(String materialType) { this.materialType = materialType; }
    public void setLagerort(String lagerort) { this.lagerort = lagerort; }
    public void setNotizen(String notizen) { this.notizen = notizen; }

    @Ignore
    public abstract String getSummaryInfo();
}

