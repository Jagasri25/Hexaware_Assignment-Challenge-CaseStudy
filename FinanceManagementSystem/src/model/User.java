package model;

public class User {
      private int userId;
      private String username;
      private String password;
      private String email;
      
      public User() {   	  
      }
      
      public User(int userId, String username, String password, String email) {
    	  super(); 
    	  this.userId = userId;
    	  this.username = username;
    	  this.password = password;
    	  this.email = email;
      }
      
      public int getUserId() {
    	  return userId;
      }
      
      public void setUserId(int userId) {
    	  this.userId = userId;
      }
      
      public String getUserName() {
    	  return username;
      }
      
      public void setUserName(String username) {
    	  this.username = username;
      }
      
      public String getPassword() {
    	  return password;
      }
      
      public void setPassword(String password) {
    	  this.password = password;
      }
      
      public String getEmail() {
    	  return email;
      }
      
      public void setEmail(String email) {
    	  this.email = email;
      }

}
