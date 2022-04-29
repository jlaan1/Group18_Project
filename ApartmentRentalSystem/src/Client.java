
/**
 * Class that represents a client with the relevant attributes
 * 
 * @author Jialei
 */

public class Client {

	private int clientID;
	private int age;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String contactNum;
	private String email;

	/**
	 * Constructor for a customer object
	 * 
	 * @param clientID   unique client ID
	 * @param username   unique client username
	 * @param firstName  first name
	 * @param lastName   last name
	 * @param contactNum contact number
	 * @param email      email address
	 * @param age        client's age
	 */
	public Client(int clientID, String username, String password, String firstName, String lastName, String contactNum,
			String email, int age) {

		setID(clientID);
		setAge(age);
		setUsername(username);
		setPassword(password);
		setName(firstName, lastName);
		setContact(contactNum);
		setEmail(email);

		// Add client information to SQLite database
		ClientDB_Messenger.insert(clientID, username, password, firstName, lastName, contactNum, email, age);

	}

	/**
	 * Method to initialize or change clientID
	 * 
	 * @param id unique client ID
	 */
	public void setID(int id) {
		clientID = id;
	}

	/**
	 * Method to obtain client's ID
	 * 
	 * @return client's ID
	 */
	public int getID() {
		return clientID;
	}

	/**
	 * Method to initialize or change client's age
	 * 
	 * @param clientAge client's age
	 */
	public void setAge(int clientAge) {
		age = clientAge;
	}

	/**
	 * Method to obtain client's age
	 * 
	 * @return client's age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Method to initialize or change client's age
	 * 
	 * @param uName client's username
	 */
	public void setUsername(String uName) {
		username = uName;
	}

	/**
	 * Method to obtain client's username
	 * 
	 * @return client's username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Method to set client's password
	 * 
	 * @param pw client's password
	 */
	public void setPassword(String pw) {
		password = pw;
	}

	/**
	 * Method to initialize or change client's name
	 * 
	 * @param firstN client's first name
	 * @param lastN  client's last name
	 */
	public void setName(String firstN, String lastN) {
		firstName = firstN;
		lastName = lastN;
	}

	/**
	 * Method to return name of client
	 * 
	 * @return client's full name
	 */
	public String getName() {
		return firstName + lastName;
	}

	/**
	 * Method to initialize or change client's contact number
	 * 
	 * @param number client's contact number
	 */
	public void setContact(String number) {
		contactNum = number;
	}

	/**
	 * Method to obtain client's contact number
	 * 
	 * @return client's contact number
	 */
	public String getContact() {
		return contactNum;
	}

	/**
	 * Method to initialize or change client's email
	 * 
	 * @param emailAdd client's email address
	 */
	public void setEmail(String emailAdd) {
		email = emailAdd;
	}

	/**
	 * Method to obtain client's email
	 * 
	 * @return client's email address
	 */
	public String getEmail() {
		return email;
	}

}
