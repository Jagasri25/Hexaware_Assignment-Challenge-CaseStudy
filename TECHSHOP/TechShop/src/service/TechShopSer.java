package service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import dao.TechShopImplementation;
import exceptions.*;
import model.*;

public class TechShopSer {
    private Scanner scanner;
    private TechShopImplementation techShopImpl;
    
    public TechShopSer() {
        scanner = new Scanner(System.in);
        techShopImpl = new TechShopImplementation();
    }

    public void registerCustomer() throws InvalidDataException {
        try {
            System.out.println("=== Customer Registration ===");
            System.out.print("First Name: ");
            String firstName = scanner.nextLine();
            
            System.out.print("Last Name: ");
            String lastName = scanner.nextLine();
            
            System.out.print("Email: ");
            String email = scanner.nextLine();
            
            System.out.print("Phone: ");
            String phone = scanner.nextLine();
            
            System.out.print("Address: ");
            String address = scanner.nextLine();
            
            int customerId = techShopImpl.addCustomer(new Customers(0, firstName, lastName, email, phone, address));
            System.out.println("Registration successful! Your Customer ID: " + customerId);
        } catch (Exception e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }


    public void manageProduct() {
        System.out.println("1. Add New Product");
        System.out.println("2. Update Existing Product");
        System.out.print("Choose option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        try {
            if (choice == 1) {
                System.out.print("Product Name: ");
                String name = scanner.nextLine();
                
                System.out.print("Description: ");
                String desc = scanner.nextLine();
                
                System.out.print("Price: ");
                double price = scanner.nextDouble();
                scanner.nextLine();
                
                int productId = techShopImpl.addProduct(new Products(0, name, desc, price));
                System.out.println("Product added! Product ID: " + productId);
            } else if (choice == 2) {
                System.out.print("Enter Product ID to update: ");
                int productId = scanner.nextInt();
                scanner.nextLine();
                
                Products product = techShopImpl.getProductById(productId);
                if (product == null) {
                    System.out.println("Product not found!");
                    return;
                }
                
                System.out.print("New Product Name (" + product.getProductName() + "): ");
                product.setProductName(scanner.nextLine());
                
                System.out.print("New Description (" + product.getDescription() + "): ");
                product.setDescription(scanner.nextLine());
                
                System.out.print("New Price (" + product.getPrice() + "): ");
                product.setPrice(scanner.nextDouble());
                scanner.nextLine();
                
                techShopImpl.updateProduct(product);
                System.out.println("Product updated successfully!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void placeOrder() throws InsufficientStockException {
        try {
            System.out.print("Customer ID: ");
            int customerId = scanner.nextInt();
            scanner.nextLine();
            
            Customers customer = techShopImpl.getCustomerById(customerId);
            if (customer == null) {
                System.out.println("Customer not found!");
                return;
            }
            
            List<Products> products = techShopImpl.getAllProducts();
            System.out.println("Available Products:");
            for (Products p : products) {
                System.out.println(p.getProductId() + ". " + p.getProductName() + " - $." + p.getPrice());
            }
            
            System.out.println("Enter product IDs and quantities (comma separated, e.g., '1,2 3,1'): ");
            String[] items = scanner.nextLine().split(" ");
            
   
            Orders order = new Orders(0, customerId, LocalDate.now(), 0.0);           
            int orderId = techShopImpl.addOrder(order);
            
            double total = 0;
            for (String item : items) {
                String[] parts = item.split(",");
                int productId = Integer.parseInt(parts[0]);
                int quantity = Integer.parseInt(parts[1]);
                
                Products product = techShopImpl.getProductById(productId);
                OrderDetails detail = new OrderDetails(0, order, product, quantity);
                techShopImpl.addOrderDetail(detail);
                total += detail.calculateSubtotal();
                
                techShopImpl.updateInventory(productId, -quantity);
            }
            
            order.setTotalAmount(total);
            techShopImpl.updateOrder(order);
            
            System.out.println("Order placed successfully! Order ID: " + orderId);
            System.out.println("Total Amount: $." + total);
        } catch (Exception e) {
            System.out.println("Order failed: " + e.getMessage());
        }
    }


    public void checkOrderStatus() {
        System.out.print("Enter Order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();
        
        try {
            Orders order = techShopImpl.getOrderById(orderId);
            if (order != null) {
                System.out.println("Order Status:");
                System.out.println("ID: " + order.getOrderId());
                System.out.println("Date: " + order.getOrderDate());
                System.out.println("Total: $" + order.getTotalAmount());
                System.out.println("Customer: " + order.getCustomerId());
            } else {
                System.out.println("Order not found!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void viewAllProducts() {
        try {
            List<Products> products = techShopImpl.getAllProducts();
            System.out.println("=== Product Catalog ===");
            for (Products p : products) {
                System.out.println(p.getProductId() + ". " + p.getProductName() + 
                                 " - $" + p.getPrice() + " | " + p.getDescription());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public void updateInventory() {
        try {
            System.out.print("Product ID: ");
            int productId = scanner.nextInt();
            
            System.out.print("Quantity to add (use negative to remove): ");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            
            techShopImpl.updateInventory(productId, quantity);
            System.out.println("Inventory updated successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateCustomerInfo() throws InvalidDataException {
        try {
            System.out.print("Customer ID: ");
            int customerId = scanner.nextInt();
            scanner.nextLine();
            
            Customers customer = techShopImpl.getCustomerById(customerId);
            if (customer == null) {
                System.out.println("Customer not found!");
                return;
            }
            
            System.out.println("Current Info:");
            System.out.println("Name: " + customer.getFirstName() + " " + customer.getLastName());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("Phone: " + customer.getPhone());
            System.out.println("Address: " + customer.getAddress());
            
            System.out.println("\nEnter new details:");
            
            System.out.print("New First Name: ");
            String firstName = scanner.nextLine();
            if (!firstName.isEmpty()) customer.setFirstName(firstName);
            
            System.out.print("New Last Name: ");
            String lastName = scanner.nextLine();
            if (!lastName.isEmpty()) customer.setLastName(lastName);
            
            System.out.print("New Email: ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) customer.setEmail(email);
            
            System.out.print("New Phone: ");
            String phone = scanner.nextLine();
            if (!phone.isEmpty()) customer.setPhone(phone);
            
            System.out.print("New Address: ");
            String address = scanner.nextLine();
            if (!address.isEmpty()) customer.setAddress(address);
            
            techShopImpl.updateCustomer(customer);
            System.out.println("Customer information updated!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void processPayment() {
        try {
            System.out.print("Order ID: ");
            int orderId = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Payment Amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            
            Orders order = techShopImpl.getOrderById(orderId);
            if (order == null) {
                System.out.println("Order not found!");
                return;
            }
            
            if (amount < order.getTotalAmount()) {
                throw new PaymentFailedException("Payment amount is less than order total");
            }
            
            System.out.println("Processing payment...");
            System.out.println("Payment of $" + amount + " successful for Order #" + orderId);
            techShopImpl.updateOrderStatus(orderId, "Paid");
        } catch (PaymentFailedException e) {
            System.out.println("Payment Failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void searchProducts() {
        System.out.print("Search term (name/description): ");
        String searchTerm = scanner.nextLine();
        
        try {
            List<Products> results = techShopImpl.searchProducts(searchTerm);
            if (results.isEmpty()) {
                System.out.println("No products found matching '" + searchTerm + "'");
            } else {
                System.out.println("Search Results:");
                for (Products p : results) {
                    System.out.println(p.getProductId() + ". " + p.getProductName() + 
                                     " - $" + p.getPrice() + " | " + p.getDescription());
                }
            }
        } catch (Exception e) {
            System.out.println("Search error: " + e.getMessage());
        }
    }
}