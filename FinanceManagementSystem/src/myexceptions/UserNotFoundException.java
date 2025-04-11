package myexceptions;

public class UserNotFoundException extends Exception {
      public UserNotFoundException(String userNotFoundExceptionMessage){
    	  super(userNotFoundExceptionMessage);
      }
}
