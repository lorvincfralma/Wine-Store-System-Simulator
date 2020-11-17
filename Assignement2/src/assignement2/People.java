package assignement2;

/**
 * The class {@code People} is used to represents user/employee entity. 
 * It contains property and methods used to manage users and employees.
 */

public class People {
	
	/**
	 * Class fields.
	 * name - It is the name of the user or employee.
	 * surname - It is the surname of the user or employee.
	 * email - It is the email of the user or employee.
	 * password - It is the password of the user or employee.
	 */

	private String name;
	private String surname;
	private String email;
	private String password;
	private String address;
	
	
	/**
	 * Class constructor. It is used by unregistered user.
	 */
	
	public People() {}
	
	/**
	 * Class constructor. It is used by Users.
	 * @param name It is the name of the user
	 * @param surname It is the surname of the user
	 * @param email It is the email of the user
	 * @param password It is the password of the user
	 * @param address It is the user's address
	 */
	
	public People(final String name, final String surname, final String email, final String password, final String address) {
		this.setName(name);
		this.setSurname(surname);
		this.setEmail(email);
		this.setPassword(password);
		this.setAddress(address);
	}
	
	/**
	 * Class constructor. It is used by Employees.
	 * @param name It is the name of the employee.
	 * @param surname It is the surname of the employee.
	 * @param email It is the email of the employee.
	 * @param password It is the password of the employee.
	 */
	
	public People(final String name, final String surname, final String email, final String password) {
		this.setName(name);
		this.setSurname(surname);
		this.setEmail(email);
		this.setPassword(password);
	}
			
	/**
	 * Method used to return name.
	 * @return Name of the instance of the class.
	 */
		
	public String getName() { return this.name; }
	
	/**
	 * Method used to return surname
	 * @return Surname of the instance of the class.
	 */
	
	public String getSurname() { return this.surname; }
	
	/**
	 * Method used to return email
	 * @return Email of the instance of the class.
	 */
	
	public String getEmail() { return this.email; }
	
	/**
	 * Method used to return password
	 * @return Password of the instance of the class.
	 */
	
	public String getPassword() { return this.password;	}
	
	/**
	 * Method used to return address
	 * @return Address of the instance of the class.
	 */
	
	public String getAddress() { return address; }
	
	/**
	 * Method used to set name of the instance of the class.
	 * @param name New name.
	 */
	
	public void setName(final String name) { this.name = name; }
	
	/**
	 * Method used to set surname of the instance of the class.	
	 * @param surname New surname.
	 */
	
	public void setSurname(final String surname) { this.surname = surname; }
	
	/**
	 * Method used to set email of the instance of the class.
	 * @param  email New email.
	 */
	
	public void setEmail(final String email) { this.email = email; }
	
	/**
	 * Method used to set password of the instance of the class.
	 * @param password New password.
	 */
	
	public void setPassword(final String password) { this.password = password; }
	
	/**
	 * Method used to set address of the instance of the class.
	 * @param address New address.
	 */
	
	public void setAddress(String address) { this.address = address; }	
}