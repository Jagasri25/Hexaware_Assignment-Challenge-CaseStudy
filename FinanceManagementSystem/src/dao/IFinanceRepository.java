package dao;

import java.util.List;

import model.Expense;
import model.User;
import myexceptions.UserNotFoundException;
import myexceptions.ExpenseNotFoundException;

public interface IFinanceRepository {
    boolean createUser(User user);
    boolean createExpense(Expense expense) throws UserNotFoundException, Exception;
    boolean deleteUser(int userId) throws UserNotFoundException; 
    boolean deleteExpense(int expenseId) throws ExpenseNotFoundException;
    List<Expense> getAllExpenses(int userId);
    boolean updateExpense(int userId, Expense expense) throws UserNotFoundException;
}