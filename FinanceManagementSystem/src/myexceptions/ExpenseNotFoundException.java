package myexceptions;

public class ExpenseNotFoundException extends Exception {
      
	public ExpenseNotFoundException(String expenseNotFoundMessage){
    	   super(expenseNotFoundMessage);
       }
}
