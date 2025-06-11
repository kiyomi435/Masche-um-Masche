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
    private String lengthPerUnit;
    private String stock;
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

    public Wool() {} // Für Room
    @Ignore
    public Wool(String name, String color, String needleSize, String lengthPerUnit,
                String stock, String composition, String storageLocation, String notes) {

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

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getNeedleSize() { return needleSize; }
    public void setNeedleSize(String needleSize) { this.needleSize = needleSize; }

    public String getLengthPerUnit() { return lengthPerUnit; }
    public void setLengthPerUnit(String lengthPerUnit) { this.lengthPerUnit = lengthPerUnit; }

    public String getStock() { return stock; }
    public void setStock(String stock) { this.stock = stock; }

    public String getComposition() { return composition; }
    public void setComposition(String composition) { this.composition = composition; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getGauge() { return gauge; }
    public void setGauge(String gauge) { this.gauge = gauge; }

    public String getUsageRecommendation() { return usageRecommendation; }
    public void setUsageRecommendation(String usageRecommendation) { this.usageRecommendation = usageRecommendation; }

    public String getCareInstructions() { return careInstructions; }
    public void setCareInstructions(String careInstructions) { this.careInstructions = careInstructions; }

    public String getLinkedProjects() { return linkedProjects; }
    public void setLinkedProjects(String linkedProjects) { this.linkedProjects = linkedProjects; }

    public String getConsumption() { return consumption; }
    public void setConsumption(String consumption) { this.consumption = consumption; }

    public String getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(String purchaseDate) { this.purchaseDate = purchaseDate; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

}
