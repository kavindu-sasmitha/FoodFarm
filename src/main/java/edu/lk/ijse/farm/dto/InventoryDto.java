package edu.lk.ijse.farm.dto;

public class InventoryDto {
    private String inventoryId;
    private String ItemName;
    private int quantity;
    private String harvestId;
    private String lastStockDate;
    private double harvestWaste;
    private String itemId;
    private String supplierId;

    public InventoryDto() {
    }
    public InventoryDto(String inventoryId, String ItemName, int quantity, String harvestId, String lastStockDate, double harvestWaste, String itemId, String supplierId) {
        this.inventoryId = inventoryId;
        this.ItemName = ItemName;
        this.quantity = quantity;
        this.harvestId = harvestId;
        this.lastStockDate = lastStockDate;
        this.harvestWaste = harvestWaste;
        this.itemId = itemId;
        this.supplierId = supplierId;

    }
    public String getInventoryId() {
        return inventoryId;
    }
    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }
    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String ItemName) {
        this.ItemName = ItemName;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;

    }
    public String getHarvestId() {
        return harvestId;
    }
    public void setHarvestId(String harvestId) {
        this.harvestId = harvestId;
    }
    public String getLastStockDate() {
        return lastStockDate;
    }
    public void setLastStockDate(String lastStockDate) {
        this.lastStockDate = lastStockDate;
    }
    public double getHarvestWaste() {
        return harvestWaste;
    }
    public void setHarvestWaste(double harvestWaste) {
        this.harvestWaste = harvestWaste;
    }

    @Override
    public String toString() {
        return "InventoryDto{" +
                "inventoryId='" + inventoryId + '\'' +
                ", ItemName='" + ItemName + '\'' +
                ", quantity=" + quantity +
                ", harvestId='" + harvestId + '\'' +
                ", lastStockDate='" + lastStockDate + '\'' +
                ", harvestWaste=" + harvestWaste +
                ", itemId='" + itemId + '\'' +
                ", supplierId='" + supplierId + '\'' +
                '}';
    }
}
