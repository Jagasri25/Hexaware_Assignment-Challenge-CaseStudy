package model;

public class Products {
    private int productId;
    private String productName;
    private String description;
    private double price;
    
    public Products() {}
    
    public Products(int productId, String productName, String description, double price ) {
    	this.productId = productId;
    	this.productName = productName;
    	this.description = description;
    	this.price = price;
    }

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

 }
