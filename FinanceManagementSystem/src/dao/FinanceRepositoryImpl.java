package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.PropertyUtil;
import model.Expense;
import model.User;
import myexceptions.ExpenseNotFoundException;
import myexceptions.UserNotFoundException;

public class FinanceRepositoryImpl implements IFinanceRepository {
	
	PreparedStatement statement;
	Connection connection = PropertyUtil.getConnection();

	public boolean createUser(User users) {
	    String query = "INSERT INTO users VALUES (?, ?, ?, ?)";

	    try (PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setInt(1, users.getUserId());
	        statement.setString(2, users.getUserName());
	        statement.setString(3, users.getPassword());
	        statement.setString(4, users.getEmail());

	        int rowsInserted = statement.executeUpdate();
	        if(rowsInserted > 0)
	        	return true;

	    } catch (Exception e) {
	        System.out.println("Error while creating user: " + e.getMessage());
	        
	    }
		return false;
	}

	public boolean createExpense(Expense expense) throws UserNotFoundException,Exception {
        String userCheckQuery = "SELECT 1 FROM users WHERE user_id = ?";
        String isCatExistQuery = "SELECT 1 FROM expenscategories WHERE category_id = ?";
        String insertQuery = "INSERT INTO expenses (expense_id, user_id, amount, category_id, date, description) VALUES (?, ?, ?, ?, ?, ?)";

        try (
            PreparedStatement userCheckStmt = connection.prepareStatement(userCheckQuery);
        	PreparedStatement catExistStmt = connection.prepareStatement(isCatExistQuery);
            PreparedStatement insertStmt = connection.prepareStatement(insertQuery)
        ) {
            userCheckStmt.setInt(1, expense.getUserId());
            try (ResultSet rs = userCheckStmt.executeQuery()) {
                if (!rs.next()) {
                    throw new UserNotFoundException("User with ID " + expense.getUserId() + " not found.");
                }
            }
            catExistStmt.setInt(1, expense.getCategoryId());
            try (ResultSet rs = catExistStmt.executeQuery()) {
                if (!rs.next()) {
                    throw new Exception("Category id  " + expense.getCategoryId() + " not found.");
                }
            }

            insertStmt.setInt(1, expense.getExpenseId());
            insertStmt.setInt(2, expense.getUserId());
            insertStmt.setDouble(3, expense.getAmount());
            insertStmt.setInt(4, expense.getCategoryId());
            insertStmt.setDate(5, Date.valueOf(expense.getDate()));
            insertStmt.setString(6, expense.getDescription());

            return insertStmt.executeUpdate() > 0;

        }  catch (UserNotFoundException e) {
            //System.out.println(e.getMessage());
            throw new UserNotFoundException("User with ID " + expense.getUserId() + " not found.");
            //return false;
        }
    }



	public boolean deleteUser(int userId) throws UserNotFoundException {
		String checkExpenseUserQuery = "SELECT 1 FROM expenses WHERE user_id=?";
	    String expenseQuery = "DELETE FROM expenses WHERE user_id = ?";
	    String userQuery = "DELETE FROM users WHERE user_id = ?";
	    try{
	    	PreparedStatement checkExpenseUserStatement = connection.prepareStatement(checkExpenseUserQuery);
	    	checkExpenseUserStatement.setInt(1, userId);
	    	ResultSet rs = checkExpenseUserStatement.executeQuery();
	    	
	    	if(rs.next()) {
	    		System.out.println("Expense Exists for the user ID");
	    		PreparedStatement expenseStatement = connection.prepareStatement(expenseQuery);
		        expenseStatement.setInt(1, userId);	
		        expenseStatement.executeUpdate();
		        System.out.println("ExpenseID deleted successfully ");
	    	}
	    	PreparedStatement userStatement = connection.prepareStatement(userQuery);
	        userStatement.setInt(1, userId);
	        int userRowsAffected = userStatement.executeUpdate();
	        
	        if (userRowsAffected == 0) {
	            throw new UserNotFoundException("User with ID " + userId + " not found.");
	        }
	        System.out.println("User with ID "+ userId + " deleted successfully ");
            return true;
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	    }
	    return false;
	}


	public boolean deleteExpense(int expenseId) throws ExpenseNotFoundException {
		String query = "DELETE FROM expenses WHERE expense_id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, expenseId);
			int rowsAffected = statement.executeUpdate();
			if (rowsAffected == 0) {
	            throw new ExpenseNotFoundException("Expense ID " + expenseId + " not found.");
	        }
            return true;
		}catch(ExpenseNotFoundException ex) {
			throw new ExpenseNotFoundException("Expense ID " + expenseId + " not found.");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	
	}

	public List<Expense> getAllExpenses(int userId) {
		List<Expense> expLs = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement("select * from expenses where user_id = ?"); 
			statement.setInt(1, userId);
			ResultSet resultSet = statement.executeQuery();
			
			
			while(resultSet.next()) {
				
				Expense exp = new Expense();
				exp.setExpenseId(resultSet.getInt(1));
				exp.setUserId(resultSet.getInt(2));
				exp.setAmount(resultSet.getDouble(3));
				exp.setCategoryId(resultSet.getInt(4));
				exp.setDate(resultSet.getString(5));
				exp.setDescription(resultSet.getString(6));
				expLs.add(exp);
			}
		}
		catch(Exception e) {
			System.out.println("Something Went Wrong :( ");
		}
		return expLs;
	}

	public boolean updateExpense(int userId, Expense expense) throws UserNotFoundException {
	    String updateExpenseQuery = "UPDATE expenses SET amount = ?, category_id = ?, date = ?, description = ? WHERE user_id = ? AND expense_id = ?";

	    try {
	        PreparedStatement expenseStatement = connection.prepareStatement(updateExpenseQuery);

	        expenseStatement.setDouble(1, expense.getAmount());
	        expenseStatement.setInt(2, expense.getCategoryId());
	        expenseStatement.setString(3, expense.getDate());
	        expenseStatement.setString(4, expense.getDescription());
	        expenseStatement.setInt(5, userId);
	        expenseStatement.setInt(6, expense.getExpenseId());

	        int rowsUpdatedExpense = expenseStatement.executeUpdate();
	        if (rowsUpdatedExpense == 0) {
	            throw new UserNotFoundException("Expense with ID " + expense.getExpenseId() + " for User ID " + userId + " not found.");
	        }
            System.out.println("Expense updated successfully");
	        return true;

	    } catch (Exception e) {
	        System.out.println("Error while updating expense: " + e.getMessage());
	    }
	    return false;
	}


	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement("select * from users"); 
			ResultSet resultSet = statement.executeQuery();
			
			
			while(resultSet.next()) {
				
				User user = new User();
				user.setUserId(resultSet.getInt(1));
				user.setUserName(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setEmail(resultSet.getString(4));
				userList.add(user);
			}
		}
		catch(Exception e) {
			System.out.println("Something Went Wrong :( ");
		}
		return userList;
	}

	public List<Expense> searchExpenseById(int expenseId) {
	    List<Expense> expLs = new ArrayList<>();
	    try {
	        String query = "SELECT * FROM expenses WHERE expense_id = ?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setInt(1, expenseId);
	        ResultSet rs = statement.executeQuery();

	        while (rs.next()) {
	            Expense expense = new Expense();
	            expense.setExpenseId(rs.getInt("expense_id"));
	            expense.setUserId(rs.getInt("user_id"));
	            expense.setAmount(rs.getDouble("amount"));
	            expense.setCategoryId(rs.getInt("category_id"));
	            expense.setDate(rs.getString("date"));
	            expense.setDescription(rs.getString("description"));
	            expLs.add(expense);
	        }
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	    return expLs;
	}

}
