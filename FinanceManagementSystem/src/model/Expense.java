package model;

public class Expense{
     private int expenseId;
     private int userId;
     private double amount;
     private int categoryId;
     private String date;
     private String description;
     
     public Expense() {
    	 
     }
     
     public Expense(int expenseId, int userId, double amount, int categoryId, String date, String description) {
    	 super();
    	 this.expenseId = expenseId;
    	 this.setUserId(userId);
    	 this.amount = amount;
    	 this.setCategoryId(categoryId);
    	 this.setDate(date);
    	 this.setDescription(description);
     }
     
     public int getExpenseId() {
    	 return expenseId;
     }
     
     public void setExpenseId(int expenseId) {
    		 this.expenseId = expenseId;
     }
     
     public double getAmount() {
    	 return amount;
     }
     
     public void setAmount(double amount) {
    	 this.amount = amount;
     }

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Expense [expenseId=" + expenseId + ", userId=" + userId + ", amount=" + amount + ", categoryId="
				+ categoryId + ", date=" + date + ", description=" + description + "]";
	}
     
}
