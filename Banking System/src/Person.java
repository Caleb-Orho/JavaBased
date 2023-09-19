/*
 * ===========================================
 * Program: CET
 * course: CST8132 Object-Oriented Programming
 * Task: Lab 5
 * Prof: James Mwangi
 * Section: 311
 * Student: Caleb Orhomre
 * ==========================================
 */
/**
 * The class that sets persons attribute
 * @author Caleb Orhomre
 *
 */
public class Person {

	protected String firstName; 	//instance variable to hold person first name
	protected String lastName;  	//instance variable to hold person last Name
	protected String email;     	//instance variable to hold person email
	protected Long phoneNumber; 	//instance variable to hold person phoneNumber

	/**
	 * Person parameterized constructor 
	 * @param firstName holds the first name of student 
	 * @param lastName holds the last name of student 
	 * @param email holds the email of student
	 * @param phoneNumber holds the phone number of student 
	 */
	public Person(String firstName, String lastName,  String email, Long phoneNumber) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * Method to return person full name
	 * @return the account holder full name
	 */
	public String getFullName() {
		String fullName = firstName + " " + lastName;

		return fullName;
	}

	/**
	 * Method to return person email address
	 * @return the account holder email address
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Method to return person phone number
	 * @return the account holder phoneNumber
	 */
	public Long getPhoneNumber() {
		return phoneNumber;
	}
}
