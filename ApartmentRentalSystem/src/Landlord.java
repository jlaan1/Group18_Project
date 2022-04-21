
/**
 * Class that represents a landlord with the relevant attributes
 * 
 * @author Jialei
 */

public class Landlord {

	private int landlordID;
	private int age;
	private String username;
	private String firstName;
	private String lastName;
	private String contactNum;
	private String email;

	/**
	 * Constructor for a landlord object
	 * 
	 * @param landlordID unique landlord ID
	 * @param username   unique client username
	 * @param firstName  first name
	 * @param lastName   last name
	 * @param contactNum contact number
	 * @param email      email address
	 * @param age        client's age
	 */
	public Landlord(int landlordID, String username, String firstName, String lastName, String contactNum, String email,
			int age) {

		setID(landlordID);
		setAge(age);
		setUsername(username);
		setName(firstName, lastName);
		setContact(contactNum);
		setEmail(email);

		// Add landlord to SQLite database
		LandlordDatabase.insert(landlordID, username, firstName, lastName, contactNum, email, age);
	}

	/**
	 * Method to initialize or change landlord ID
	 * 
	 * @param id unique landlord ID
	 */
	public void setID(int id) {
		landlordID = id;
	}

	/**
	 * Method to obtain landlord's ID
	 * 
	 * @return landlord's ID
	 */
	public int getID() {
		return landlordID;
	}

	/**
	 * Method to initialize or change landlord's age
	 * 
	 * @param landlordAge landlord's age
	 */
	public void setAge(int landlordAge) {
		age = landlordAge;
	}

	/**
	 * Method to obtain landlord's age
	 * 
	 * @return landlord's age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Method to initialize or change landlord's age
	 * 
	 * @param uName landlord's username
	 */
	public void setUsername(String uName) {
		username = uName;
	}

	/**
	 * Method to obtain landlord's username
	 * 
	 * @return landlord's username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Method to initialize or change landlord's name
	 * 
	 * @param firstN landlord's first name
	 * @param lastN  landlord's last name
	 */
	public void setName(String firstN, String lastN) {
		firstName = firstN;
		lastName = lastN;
	}

	/**
	 * Method to return name of landlord
	 * 
	 * @return landlord's full name
	 */
	public String getName() {
		return firstName + lastName;
	}

	/**
	 * Method to initialize or change landlord's contact number
	 * 
	 * @param number landlord's contact number
	 */
	public void setContact(String number) {
		contactNum = number;
	}

	/**
	 * Method to obtain landlord's contact number
	 * 
	 * @return landlord's contact number
	 */
	public String getContact() {
		return contactNum;
	}

	/**
	 * Method to initialize or change landlord's email
	 * 
	 * @param emailAdd landlord's email address
	 */
	public void setEmail(String emailAdd) {
		email = emailAdd;
	}

	/**
	 * Method to obtain landlord's email
	 * 
	 * @return landlord's email address
	 */
	public String getEmail() {
		return email;
	}

}
