package com.example.masche_um_masche;

public class CrochetHook extends IMaterial {
    private String size;
    private String gripType;
    private String material;

    // Optional
    private String brand;
    private String length;
    private String condition;

    public CrochetHook(String size, String gripType, String material,
                       String storageLocation, String notes) {

        super(size + " mm – " + gripType, "Crochet Hook", storageLocation, notes);
        this.size = size;
        this.gripType = gripType;
        this.material = material;
    }

    @Override
    public String getSummaryInfo() {
        return "Size: " + size + "\n" +
                "Grip: " + gripType + "\n" +
                "Material: " + material;
    }

    public void setCondition(String condition) { this.condition = condition; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setLength(String length) { this.length = length; }
}

