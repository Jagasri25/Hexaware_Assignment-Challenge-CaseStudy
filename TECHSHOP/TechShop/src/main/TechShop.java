package main;

import java.sql.Connection;
import java.util.Scanner;

import service.TechShopSer;
import util.DBUtil;

public class TechShop {
    static {
        try {
            Connection connection = DBUtil.getConnection();
            if (connection != null) {
                System.out.println("Database connected successfully...");
            }
        } catch (Exception e) {
            System.out.println("Error during DB operation: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        TechShopSer techShopService = new TechShopSer();
        
        System.out.println("WELCOME TO TECHSHOP ELECTRONIC GADGETS STORE");
        System.out.println("----------------------------------------------");
        
        boolean flag = true;
        do {
            System.out.println("\nMAIN MENU");
            System.out.println("1. Register Customer");
            System.out.println("2. Add/Update Product");
            System.out.println("3. Place Order");
            System.out.println("4. Check Order Status");
            System.out.println("5. View All Products");
            System.out.println("6. Update Inventory");
            System.out.println("7. Update Customer Info");
            System.out.println("8. Process Payment");
            System.out.println("9. Search Products");
            System.out.println("10. Exit");
            System.out.println("----------------------------------------------");
            System.out.print("Enter your choice (1-10): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (choice) {
                case 1:
                    techShopService.registerCustomer();
                    break;
                    
                case 2:
                    techShopService.manageProduct();
                    break;
                    
                case 3:
                    techShopService.placeOrder();
                    break;
                    
                case 4:
                    techShopService.checkOrderStatus();
                    break;
                    
                case 5:
                    techShopService.viewAllProducts();
                    break;
                    
                case 6:
                    techShopService.updateInventory();
                    break;
                    
                case 7:
                    techShopService.updateCustomerInfo();
                    break;
                    
                case 8:
                    techShopService.processPayment();
                    break;
                    
                case 9:
                    techShopService.searchProducts();
                    break;
                    
                case 10:
                    flag = false;
                    System.out.println("Thank you for using TechShop. Goodbye!");
                    break;
                    
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (flag);
        
        scanner.close();
    }
}