package model;

public class ExpenseCategories {
     private int categoryId;
     private String categoryName;
     
     public ExpenseCategories(){
    	 
     }
     
     public ExpenseCategories(int categoryId, String categoryName) {
    	super();
    	this.setCategoryId(categoryId);
    	this.setCategoryName(categoryName);
     }

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		    this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
     
}
