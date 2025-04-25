package com.example.masche_um_masche.objects;

public class OtherUtensil extends Material {
    private String itemType;
    private String description;
    private String material;
    private int quantity;

    // Optional
    private String color;
    private String usage;

    public OtherUtensil(String itemType, String description, String material, int quantity,
                       String storageLocation, String notes) {

        super(itemType + " – " + description, "Other", storageLocation, notes);
        this.itemType = itemType;
        this.description = description;
        this.material = material;
        this.quantity = quantity;
    }

    @Override
    public String getSummaryInfo() {
        return "Type: " + itemType + "\n" +
                "Material: " + material + "\n" +
                "Quantity: " + quantity;
    }

    public void setColor(String color) { this.color = color; }
    public void setUsage(String usage) { this.usage = usage; }
}

