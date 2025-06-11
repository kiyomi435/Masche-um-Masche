package com.example.masche_um_masche.data.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class CrochetHook extends Material {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String size;
    private String gripType;
    private String material;

    // Optional
    private String brand;
    private String length;
    private String condition;

    public CrochetHook() {} // Für Room
    @Ignore
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getGripType() {
        return gripType;
    }

    public void setGripType(String gripType) {
        this.gripType = gripType;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}

