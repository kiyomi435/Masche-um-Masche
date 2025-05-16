package com.example.masche_um_masche.data.entity;

public class Wool extends Material {
    private String color;
    private String needleSize;
    private String lengthPerUnit;
    private int stock;
    private String composition;

    // Optional fields

    private String brand;
    private String gauge;
    private String usageRecommendation;
    private String careInstructions;
    private String linkedProjects;
    private String consumption;
    private String purchaseDate;
    private double price;

    public Wool(String name, String color, String needleSize, String lengthPerUnit,
                int stock, String composition, String storageLocation, String notes) {

        super(name + " – " + color, "Wool", storageLocation, notes);
        this.color = color;
        this.needleSize = needleSize;
        this.lengthPerUnit = lengthPerUnit;
        this.stock = stock;
        this.composition = composition;
    }

    @Override
    public String getSummaryInfo() {
        return brand + "\n" +
                lengthPerUnit + "\n" +
                stock + " skeins available\n" +
                composition;
    }

    // Setters for optional fields
    public void setNeedleSize(String needleSize) {
        this.needleSize = needleSize;
    }

    public void setGauge(String gauge) {
        this.gauge = gauge;
    }

    public void setUsageRecommendation(String usageRecommendation) {
        this.usageRecommendation = usageRecommendation;
    }
}
