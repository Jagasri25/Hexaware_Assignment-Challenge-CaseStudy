package Service;

import java.util.List;
import java.util.Scanner;
import model.Expense;
import model.User;
import myexceptions.ExpenseNotFoundException;
import myexceptions.UserNotFoundException;
import dao.FinanceRepositoryImpl;

public class FinanceAppSer {
    Scanner scanner;
    FinanceRepositoryImpl financeRepoImp;
    
    public FinanceAppSer(){
    	scanner = new Scanner(System.in);
    	financeRepoImp = new FinanceRepositoryImpl();
    }
    
    public void createUser() {
    	
    	User user = new User();
    	
    	System.out.println("Enter User ID :");
        user.setUserId(scanner.nextInt());
        scanner.nextLine();
        
        System.out.println("Enter User Name: ");
        user.setUserName(scanner.nextLine());
        
        System.out.println("Enter password: ");
        user.setPassword(scanner.nextLine());
        
        System.out.println("Enter Email: ");
        user.setEmail(scanner.nextLine());
        
        financeRepoImp.createUser(user);
    }
    
    public void createExpense() {
        try {
            System.out.print("Enter User ID: ");
            int userId = scanner.nextInt();
            scanner.nextLine();

            Expense expense = new Expense();
            expense.setUserId(userId);

            System.out.print("Enter Expense ID: ");
            expense.setExpenseId(scanner.nextInt());

            System.out.print("Enter Amount: ");
            expense.setAmount(scanner.nextDouble());

            System.out.print("Enter Category ID: ");
            expense.setCategoryId(scanner.nextInt());
            scanner.nextLine();

            System.out.print("Enter Date (YYYY-MM-DD): ");
            expense.setDate(scanner.nextLine());

            System.out.print("Enter Description: ");
            expense.setDescription(scanner.nextLine());

            boolean result = financeRepoImp.createExpense(expense);
            if (result) {
                System.out.println("Expense added successfully.");
            } else {
                System.out.println("Failed to add expense.");
            }

        } catch (UserNotFoundException e) {
            System.out.println("User not found : Failed to add expense.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
    }
    
	public void deleteUser() {
		
		System.out.println("Enter User ID to delete:");
        int userId = scanner.nextInt();
        try {
          financeRepoImp.deleteUser(userId);
        }catch(UserNotFoundException u) {
        	System.out.println("User Not Found");
        }
	
	}

	public void deleteExpense(){
		System.out.println("Enter Expense ID to delete:");
        int expenseId = scanner.nextInt();
        scanner.nextLine();
        try {
          financeRepoImp.deleteExpense(expenseId);
        }catch(ExpenseNotFoundException en) {
        	System.out.println("Expense Not Found");
        }
	}

	public void getAllExpense() {
		System.out.println("Enter User ID to fetch expenses: ");
		int userId = scanner.nextInt();
	    List<Expense> expLs = financeRepoImp.getAllExpenses(userId);
	    if (expLs.isEmpty()) {
	        System.out.println("No expenses found for User ID: " + userId);
	    } else {
	        System.out.println("Expenses for User ID: " + userId);
	        for (Expense e : expLs) {
	        	System.out.println("---------------------------");
	            System.out.println("Expense ID: " + e.getExpenseId());
	            System.out.println("Amount: " + e.getAmount());
	            System.out.println("Category ID: " + e.getCategoryId());
	            System.out.println("Date: " + e.getDate());
	            System.out.println("Description: " + e.getDescription());
	            System.out.println("---------------------------");
	        }
	    }
	}

	public void updateExpense()throws UserNotFoundException {
		System.out.println("Enter User ID:");
        int userId = scanner.nextInt();
        scanner.nextLine();
		
		System.out.println("Enter Expense ID to update:");
        int expenseId = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("Enter new Amount:");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.println("Enter new Category ID:");
        int categoryId = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("Enter new Date (YYYY-MM-DD):");
        String date = scanner.nextLine();
        
        System.out.println("Enter the Description:");
        String description = scanner.nextLine();
        
        Expense expense = new Expense();
        expense.setExpenseId(expenseId);
        expense.setAmount(amount);
        expense.setCategoryId(categoryId);
        expense.setDate(date);
        expense.setDescription(description);
        
        
        financeRepoImp.updateExpense(userId, expense);
	}

	public List<User> getAllUsers() {
		return financeRepoImp.getAllUsers();
	}
	
	public void searchExpenseById() {
		System.out.println("Enter Expense ID: ");
		int expenseId = scanner.nextInt();
		List<Expense> expLs= financeRepoImp.searchExpenseById(expenseId);
	    if (expLs.isEmpty()) {
	        System.out.println("No expenses found ");
	    } else {
	        System.out.println("Expenses for User ID: " + expenseId);
	        for (Expense e : expLs) {
	            System.out.println("Expense ID: " + e.getExpenseId());
	            System.out.println("Amount: " + e.getAmount());
	            System.out.println("Category ID: " + e.getCategoryId());
	            System.out.println("Date: " + e.getDate());
	            System.out.println("Description: " + e.getDescription());
	        }
	    }
	}
}
