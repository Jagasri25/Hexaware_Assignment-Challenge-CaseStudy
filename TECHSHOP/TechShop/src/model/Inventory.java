package model;

public class Inventory {
    private int InventoryId;
    private int productId;
    private int quantityInStock;
    private int lastStockUpdate;

    public Inventory() {}

    public Inventory(int InventoryId, int productId, int quantityInStock, int lastStockUpdate) {
        this.InventoryId = InventoryId;
        this.productId = productId;
        this.quantityInStock = quantityInStock;
        this.lastStockUpdate = lastStockUpdate;
    }

	public int getInventoryId() {
		return InventoryId;
	}

	public void setInventoryId(int inventoryId) {
		InventoryId = inventoryId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public int getLastStockUpdate() {
		return lastStockUpdate;
	}

	public void setLastStockUpdate(int lastStockUpdate) {
		this.lastStockUpdate = lastStockUpdate;
	}
    
}