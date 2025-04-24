package com.example.masche_um_masche.objects;

public class KnittingNeedle extends IMaterial {
    private String needleType;
    private String size;
    private String length;
    private String material;

    // Optional
    private String condition;
    private String brand;

    public KnittingNeedle(String needleType, String size, String length, String material,
                          String storageLocation, String notes) {

        super(size + " – " + needleType, "Knitting Needle", storageLocation, notes);
        this.needleType = needleType;
        this.size = size;
        this.length = length;
        this.material = material;
    }

    @Override
    public String getSummaryInfo() {
        return "Type: " + needleType + "\n" +
                "Size: " + size + "\n" +
                "Length: " + length + "\n" +
                "Material: " + material;
    }

    public void setCondition(String condition) { this.condition = condition; }
    public void setBrand(String brand) { this.brand = brand; }
}
