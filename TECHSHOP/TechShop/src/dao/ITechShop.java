package dao;

import java.sql.SQLException;
import java.util.List;

import model.Customers;
import model.OrderDetails;
import model.Orders;
import model.Products;

public interface ITechShop {
	 public int addCustomer(Customers customer) throws SQLException;
	 public int addProduct(Products product) throws SQLException;
	 public Products getProductById(int productId) throws SQLException;
	 public void updateProduct(Products product) throws SQLException;
	 public Customers getCustomerById(int customerId) throws SQLException;
	 public List<Products> getAllProducts() throws SQLException;
	 public int addOrder(Orders order) throws SQLException;
	 public void addOrderDetail(OrderDetails detail) throws SQLException;
	 public void updateInventory(int productId, int quantityChange) throws SQLException;
	 public void updateOrder(Orders order) throws SQLException;
	 public Orders getOrderById(int orderId) throws SQLException;
	 public void updateCustomer(Customers customer) throws SQLException;
	 public void updateOrderStatus(int orderId, String status) throws SQLException;
	 public List<Products> searchProducts(String searchTerm) throws SQLException;

}
