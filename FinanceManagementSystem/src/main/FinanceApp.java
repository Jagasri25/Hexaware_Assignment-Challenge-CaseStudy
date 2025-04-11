package main;
import Service.FinanceAppSer;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;
import Service.FinanceAppSer;
//import java.util.List;
import dao.FinanceRepositoryImpl;
import model.User;
import myexceptions.ExpenseNotFoundException;
import myexceptions.UserNotFoundException;
import util.PropertyUtil;

public class FinanceApp {
	
	static {
		try {
			Connection connection = PropertyUtil.getConnection();
			if(connection!=null)
				System.out.println("Database connected successfully...");
		}
		catch(Exception e) {
			System.out.println("Error during DB operation "+ e.getMessage());
		}
	}

    public static void main(String[] args) throws ExpenseNotFoundException {
        Scanner scanner = new Scanner(System.in);
        FinanceAppSer financeAppService = new FinanceAppSer();
        boolean credentialsMatch = false;
        boolean terminatingProgram = true;
        System.out.println("WELCOME TO THE FINANCE APP");
        System.out.println("----------------------------");
        
        do{
        System.out.println("SignIn or SignUp ?");
        
        String signingoption = scanner.next().toLowerCase().replaceAll("\\s+", "");
         
        if (signingoption.equals("signup")) {
        	
            financeAppService.createUser();
        } 
        else if (signingoption.equals("signin")) {
        	
            System.out.println("Enter your userid:");
            int userId = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter your username:");
            String username = scanner.nextLine();
            System.out.println("Enter your password:");
            String password = scanner.nextLine();

            try {
            	
                List<User> userList = financeAppService.getAllUsers();
                for (User u : userList) {
                    if (u.getUserId() == userId &&
                            u.getUserName().equals(username) &&
                            u.getPassword().equals(password)) {
                        credentialsMatch = true;
                        break;
                    }
                }

                if (credentialsMatch)
                    System.out.println("Authentication Successful!");
                
                boolean executeLoop = true;
                
                while (executeLoop) {
                	System.out.println("----------------------------");
                    System.out.println("SELECT YOUR OPTION");
                    System.out.println("1. Create User");
                    System.out.println("2. Create Expense");
                    System.out.println("3. Delete User");
                    System.out.println("4. Delete Expense");
                    System.out.println("5. Get All Expenses");
                    System.out.println("6. Update Expense");
                    System.out.println("7. Exit");
                    System.out.println("Your Option(1-7) : ");
                    System.out.println("----------------------------");
                    int menuoption = scanner.nextInt();

                    switch (menuoption) {
                        
                    case 1: {
                        financeAppService.createUser();
                    }
                    break;

                    case 2: {
                        financeAppService.createExpense();    
                    }
                    break;

                    case 3: {
                         financeAppService.deleteUser();  
                    }
                    break;

                    case 4: {
                         financeAppService.deleteExpense();
                    }
                    break;

                    case 5: {
                         financeAppService.getAllExpense();
                    }
                    break;

                    case 6: {
                        financeAppService.updateExpense();    
                    }
                    break;

                    case 7: {
                         System.out.println("Exiting.....");
                         System.out.println("Thank you for using Financial App!");
                         executeLoop = false;
                         terminatingProgram = false;
                    }
                    break;

                    default: {
                         System.out.println("Sorry! Invalid Option :(");
                    }
                    }
                }
            } 
            
            catch (UserNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        else {
        	System.out.println("Option Invalid Try Again :(");
        	terminatingProgram = false;
        }
        }while(terminatingProgram);
    }
}
