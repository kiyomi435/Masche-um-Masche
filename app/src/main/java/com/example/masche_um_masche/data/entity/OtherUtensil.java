package com.example.masche_um_masche.data.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class OtherUtensil extends Material {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String itemType;
    private String description;
    private String material;
    private String quantity;

    // Optional
    private String color;
    private String storageLocation;
    private String notes;

    public OtherUtensil() {} // Für Room
    @Ignore
    public OtherUtensil(String itemType, String description, String material, String quantity, String color,
                       String storageLocation, String notes) {

        super(itemType + " – " + description, "Other", storageLocation, notes);
        this.itemType = itemType;
        this.description = description;
        this.material = material;
        this.quantity = quantity;
        this.color = color;
        this.storageLocation = storageLocation;
        this.notes = notes;
    }

    @Override
    public String getSummaryInfo() {
        return "Type: " + itemType + "\n" +
                "Material: " + material + "\n" +
                "Quantity: " + quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

