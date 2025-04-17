package model;

public class Customers {
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;

    public Customers() {
    	
    }
    public Customers(int customerId, String firstName, String lastName, String email, String phone, String address) {
    	super();
        this.setCustomerId(customerId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerID) {
		this.customerId = customerID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


}