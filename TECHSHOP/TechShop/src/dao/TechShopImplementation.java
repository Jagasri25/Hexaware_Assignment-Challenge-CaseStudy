package dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.*;
import util.DBUtil;

public class TechShopImplementation implements ITechShop {
    private Connection connection;

    public TechShopImplementation() {
        this.connection = DBUtil.getConnection();
    }

    @Override
    public int addCustomer(Customers customer) throws SQLException {
        String sql = "INSERT INTO Customers (FirstName, LastName, Email, Phone, Address) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, customer.getFirstName());
            pstmt.setString(2, customer.getLastName());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getPhone());
            pstmt.setString(5, customer.getAddress());
            
            pstmt.executeUpdate();
            
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    @Override
    public int addProduct(Products product) throws SQLException {
        String sql = "INSERT INTO Products (ProductName, Description, Price) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, product.getProductName());
            pstmt.setString(2, product.getDescription());
            pstmt.setDouble(3, product.getPrice());
            
            pstmt.executeUpdate();
            
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    @Override
    public Products getProductById(int productId) throws SQLException {
        String sql = "SELECT * FROM Products WHERE ProductID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Products(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getString("Description"),
                        rs.getDouble("Price")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public void updateProduct(Products product) throws SQLException {
        String sql = "UPDATE Products SET ProductName = ?, Description = ?, Price = ? WHERE ProductID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, product.getProductName());
            pstmt.setString(2, product.getDescription());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getProductId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public Customers getCustomerById(int customerId) throws SQLException {
        String sql = "SELECT * FROM Customers WHERE CustomerID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Customers(
                        rs.getInt("CustomerID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getString("Phone"),
                        rs.getString("Address")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Products> getAllProducts() throws SQLException {
        List<Products> products = new ArrayList<>();
        String sql = "SELECT * FROM Products";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                products.add(new Products(
                    rs.getInt("ProductID"),
                    rs.getString("ProductName"),
                    rs.getString("Description"),
                    rs.getDouble("Price")
                ));
            }
        }
        return products;
    }

    @Override
    public int addOrder(Orders order) throws SQLException {
        String sql = "INSERT INTO Orders (CustomerID, OrderDate, TotalAmount) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, order.getCustomerId());
            pstmt.setDate(2, Date.valueOf(order.getOrderDate()));
            pstmt.setDouble(3, order.getTotalAmount());
            
            pstmt.executeUpdate();
            
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    @Override
    public void addOrderDetail(OrderDetails detail) throws SQLException {
        String sql = "INSERT INTO OrderDetails (OrderID, ProductID, Quantity) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, detail.getOrder().getOrderId());
            pstmt.setInt(2, detail.getProduct().getProductId());
            pstmt.setInt(3, detail.getQuantity());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void updateInventory(int productId, int quantityChange) throws SQLException {
        String sql = "UPDATE Inventory SET QuantityInStock = QuantityInStock + ?, LastStockUpdate = CURRENT_TIMESTAMP WHERE ProductID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, quantityChange);
            pstmt.setInt(2, productId);
            pstmt.executeUpdate();
        }
    }

    @Override
    public void updateOrder(Orders order) throws SQLException {
        String sql = "UPDATE Orders SET TotalAmount = ? WHERE OrderID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, order.getTotalAmount());
            pstmt.setInt(2, order.getOrderId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public Orders getOrderById(int orderId) throws SQLException {
        String sql = "SELECT o.OrderID, o.OrderDate, o.TotalAmount, o.CustomerID, " +
                    "c.FirstName, c.LastName, c.Email, c.Phone, c.Address " +
                    "FROM Orders o JOIN Customers c ON o.CustomerID = c.CustomerID " +
                    "WHERE o.OrderID = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    LocalDate orderDate = rs.getDate("OrderDate") != null 
                        ? rs.getDate("OrderDate").toLocalDate() 
                        : LocalDate.now();
                    
                    return new Orders(
                        rs.getInt("OrderID"),
                        rs.getInt("CustomerID"),
                        orderDate,
                        rs.getDouble("TotalAmount")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public void updateCustomer(Customers customer) throws SQLException {
        String sql = "UPDATE Customers SET FirstName = ?, LastName = ?, Email = ?, Phone = ?, Address = ? WHERE CustomerID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, customer.getFirstName());
            pstmt.setString(2, customer.getLastName());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getPhone());
            pstmt.setString(5, customer.getAddress());
            pstmt.setInt(6, customer.getCustomerId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void updateOrderStatus(int orderId, String status) throws SQLException {
        String sql = "UPDATE Orders SET Status = ? WHERE OrderID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, orderId);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Products> searchProducts(String searchTerm) throws SQLException {
        List<Products> products = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE ProductName LIKE ? OR Description LIKE ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            String likeTerm = "%" + searchTerm + "%";
            pstmt.setString(1, likeTerm);
            pstmt.setString(2, likeTerm);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    products.add(new Products(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getString("Description"),
                        rs.getDouble("Price")
                    ));
                }
            }
        }
        return products;
    }
}