import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class that represents a client with the relevant attributes
 * 
 * @author ljialei
 */

public class Client {

	private int clientID;
	private int age;
	private String username;
	private String firstName;
	private String lastName;
	private String contactNum;
	private String email;

	/**
	 * Method to initialize or change clientID
	 * 
	 * @param id unique client ID
	 */
	public void setID(int id) {
		clientID = id;
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
	 * Method to initialize or change client's age
	 * 
	 * @param uName client's username
	 */
	public void setUsername(String uName) {
		username = uName;
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
	 * Method to initialize or change client's contact number
	 * 
	 * @param number client's contact number
	 */
	public void setContact(String number) {
		contactNum = number;
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
	 * Method to establish connection with SQLite database
	 * 
	 * @return Connection conn with SQLite database
	 */
	private Connection connect() {
		// SQLite connection string
		String url = "jdbc:sqlite://Users/ljialei/Documents/Boston University/Junior/Spring 2022/"
				+ "CAS CS411/Group18_Project/ApartmentRentalSystem/Data.db";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	/**
	 * Method to insert new clients into clients database
	 * 
	 * @param clientID   unique ID of the client
	 * @param username   unique username of the client
	 * @param fName      first name
	 * @param lName      last name
	 * @param contactNum contact number
	 * @param email      email address
	 * @param age        client age
	 */
	public void insert(int clientID, String username, String fName, String lName, String contactNum, String email,
			int age) {

		String sql = "INSERT INTO clients(id, username, firstName, lastName, contactNumber, email, age) VALUES(?,?,?,?,?,?,?)";

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, clientID);
			pstmt.setString(2, username);
			pstmt.setString(3, fName);
			pstmt.setString(4, lName);
			pstmt.setString(5, contactNum);
			pstmt.setString(6, email);
			pstmt.setInt(7, age);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Method to delete a client from the existing clients database
	 * 
	 * @param clientID unique ID of the client
	 */
	public void delete(int clientID) {

		String sql = "DELETE FROM clients WHERE id = ?";

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			// Set the corresponding param
			pstmt.setInt(1, clientID);

			// Execute the delete statement
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Method to display existing clients currently in clients database
	 */
	public void display() {

		try {

			Connection connection = connect();
			String sql = "SELECT * FROM clients";

			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {

				int clientID = result.getInt("id");
				String username = result.getString("username");
				String fName = result.getString("firstName");
				String lName = result.getString("lastName");
				String contactNum = result.getString("contactNumber");
				String email = result.getString("email");
				int age = result.getInt("age");

				System.out.println(clientID + "|" + username + "|" + fName + "|" + lName + "|" + contactNum + "|"
						+ email + "|" + age);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error connecting to SQLite database");
			e.printStackTrace();
		}

	}

}
