package com.example.masche_um_masche.data.entity;
public class Pattern {
    private String name;
    private String category; // z. B. Kleidung, Haushalt, Accessoires ...
    private String needleType;
    private String pdfPath;  // Speicherort der PDF-Datei
    private String needleSize;
    private String notes;

    public Pattern(String name, String category, String pdfPath,
                   String needleType, String needleSize) {
        this.name = name;
        this.category = category;
        this.pdfPath = pdfPath;
        this.needleType = needleType;
        this.needleSize = needleSize;
    }

    // Getter
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public String getNeedleType() {
        return needleType;
    }

    public void setNeedleType(String needleType) {
        this.needleType = needleType;
    }

    public String getNeedleSize() {
        return needleSize;
    }

    public void setNeedleSize(String needleSize) {
        this.needleSize = needleSize;
    }

    public String getNotes() {
        return notes;
    }

    // Für Kurzansicht z. B. in der CardView
    public String getSummaryInfo() {
        return category + "\n" + needleType + " " + needleSize;
    }
}

