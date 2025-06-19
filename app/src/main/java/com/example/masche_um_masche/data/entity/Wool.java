package com.example.masche_um_masche.data.entity;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Wool extends Material {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String color;
    private String needleSize;
    private String stock;
    private String composition;
    private String brand;
    private String storageLocation;
    private String notes;

    public Wool() {} // Für Room
    @Ignore
    public Wool(String name, String color, String needleSize,
                String stock, String composition, String storageLocation, String notes) {

        super(name + " – " + color, "Wool", storageLocation, notes);
        this.color = color;
        this.needleSize = needleSize;
        this.stock = stock;
        this.composition = composition;
        this.storageLocation = storageLocation;
        this.notes = notes;
    }

    @Override
    public String getSummaryInfo() {
        return needleSize + "\n" +
                stock + " noch da\n" +
                composition;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getNeedleSize() { return needleSize; }
    public void setNeedleSize(String needleSize) { this.needleSize = needleSize; }

    public String getStock() { return stock; }
    public void setStock(String stock) { this.stock = stock; }

    public String getComposition() { return composition; }
    public void setComposition(String composition) { this.composition = composition; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

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
