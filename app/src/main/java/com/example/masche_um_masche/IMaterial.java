package com.example.masche_um_masche;

public abstract class IMaterial {
    private String name;
    private String materialArt;
    private String lagerort;
    private String notizen;

    public IMaterial(String name, String materialArt, String lagerort, String notizen) {
        this.name = name;
        this.materialArt = materialArt;
        this.lagerort = lagerort;
        this.notizen = notizen;
    }

    // Getter und Setter
    public String getName() { return name; }
    public String getMaterialArt() { return materialArt; }
    public String getLagerort() { return lagerort; }
    public String getNotizen() { return notizen; }

    public void setName(String name) { this.name = name; }
    public void setMaterialArt(String materialArt) { this.materialArt = materialArt; }
    public void setLagerort(String lagerort) { this.lagerort = lagerort; }
    public void setNotizen(String notizen) { this.notizen = notizen; }

    public abstract String getSummaryInfo();
}

