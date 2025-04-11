package test;
import dao.FinanceRepositoryImpl;
import model.*;
import myexceptions.ExpenseNotFoundException;
import myexceptions.UserNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import Service.FinanceAppSer;

class FinanceAppTest {
    
	FinanceRepositoryImpl finRepoImpl= new FinanceRepositoryImpl();
	
	@Test
	void testUserCreation() {
		User user = new User(555, "yyy", "yyy500", "yyy500@gmail.com");
		try {
			boolean statusOfUserCreation = finRepoImpl.createUser(user);
			assertTrue(statusOfUserCreation);
			if (statusOfUserCreation)
				System.out.println("Test Case Passed: User Created Successfully");
			else
				System.out.println("Test Case Failed: User is not Created");
		} catch (Exception e) {
			System.out.println("Test Case Failed: " + e.getMessage());
		} finally {
			try {
				finRepoImpl.deleteUser(555);
			} catch (UserNotFoundException e) {
				System.out.println("Cleanup Failed: " + e.getMessage());
			}
		}
	}
    
	@Test
	void testExpenseDeletion() {
		try {
			User tempUser = new User(556, "testUser", "pass123", "test556@gmail.com");
			finRepoImpl.createUser(tempUser);
			
			Expense expense = new Expense(9998, 556, 300.00, 201, "2025-04-08", "To delete");
			boolean expenseCreated = finRepoImpl.createExpense(expense);
			assertTrue(expenseCreated);
			
			boolean statusOfDeletion = finRepoImpl.deleteExpense(9998);
			assertTrue(statusOfDeletion);
			if (statusOfDeletion)
				System.out.println("Test Case Passed: Expense Deleted Successfully");
			else
				System.out.println("Test Case Failed: Expense Deletion Failed");

		} catch (Exception e) {
			System.out.println("Test Case Failed: " + e.getMessage());
		} finally {
			try {
				finRepoImpl.deleteUser(556);
				//System.out.println("Cleanup Passed: User with ID 556 deleted successfully");
			} catch (UserNotFoundException e) {
				System.out.println("Cleanup Failed: " + e.getMessage());
			}
		}
	}

	@Test
	void testSearchOfExpense() {
		try {
			User user = new User(557, "searchUser", "passSearch", "search557@gmail.com");
			finRepoImpl.createUser(user);

			Expense expense = new Expense(9997, 557, 150.00, 201, "2025-04-09", "Search Test");
			finRepoImpl.createExpense(expense);

			List<Expense> foundList = finRepoImpl.searchExpenseById(9997); 
			assertNotNull(foundList);
			assertFalse(foundList.isEmpty(), "Expense not found");
			
			Expense found = foundList.get(0); 
			assertEquals("Search Test", found.getDescription());

			System.out.println("Test Case Passed: Expense found successfully");

		} catch (Exception e) {
			System.out.println("Test Case Failed: " + e.getMessage());
		} finally {
			try {
				finRepoImpl.deleteExpense(9997);
				finRepoImpl.deleteUser(557);
			} catch (Exception e) {
				System.out.println("Cleanup Failed: " + e.getMessage());
			}
		}
	}

	@Test
	void testUserNotFoundException() {
		
	    Expense invalidUserExpense = new Expense(8888, 1, 150.00, 201, "2025-04-10", "Invalid user expense");
	    boolean userExceptionThrown = false;
        try {
          finRepoImpl.createExpense(invalidUserExpense);
	    } catch (UserNotFoundException e) {
	        userExceptionThrown = true;
	        System.out.println("Test Case Passed: UserNotFoundException thrown for invalid user");
	    } catch (Exception e) {
	        fail(e.getMessage());
	    }

	    assertTrue(userExceptionThrown);
	}
	
	@Test
	void testExpenseNotFoundException() {
		  Expense invalidUserExpense = new Expense(8888, 1, 150.00, 201, "2025-04-10", "Invalid user expense");
		  boolean expenseExceptionThrown = false;

		    try {
		        finRepoImpl.deleteExpense(7777); 
		    } catch (ExpenseNotFoundException e) {
		        expenseExceptionThrown = true;
		        System.out.println("Test Case Passed: ExpenseNotFoundException thrown for non-existent expense");
		    }

		    assertTrue(expenseExceptionThrown);
	
		
	}
	
	


}
