package com.example.masche_um_masche.data.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
@Entity
public class KnittingNeedle extends Material {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String needleType;
    private String size;
    private String length;
    private String material;

    // Optional
    private String storageLocation;
    private String notes;

    public KnittingNeedle() {} // Für Room
    @Ignore
    public KnittingNeedle(String needleType, String size, String length, String material,
                          String storageLocation, String notes) {

        super(size + " – " + needleType, "Knitting Needle", storageLocation, notes);
        this.needleType = needleType;
        this.size = size;
        this.length = length;
        this.material = material;
        this.storageLocation = storageLocation;
        this.notes = notes;
    }

    @Override
    public String getSummaryInfo() {
        return "Type: " + needleType + "\n" +
                "Size: " + size + "\n" +
                "Length: " + length + "\n" +
                "Material: " + material;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNeedleType() {
        return needleType;
    }

    public void setNeedleType(String needleType) {
        this.needleType = needleType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
