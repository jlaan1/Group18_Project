import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Landlord {

	private int landlordID;
	private int age;
	private String username;
	private String firstName;
	private String lastName;
	private String contactNum;
	private String email;

	/**
	 * Method to initialize or change landlord ID
	 * 
	 * @param id unique landlord ID
	 */
	public void setID(int id) {
		landlordID = id;
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
	 * Method to initialize or change landlord's age
	 * 
	 * @param uName landlord's username
	 */
	public void setUsername(String uName) {
		username = uName;
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
	 * Method to initialize or change landlord's contact number
	 * 
	 * @param number landlord's contact number
	 */
	public void setContact(String number) {
		contactNum = number;
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
	 * Method to insert new landlord into landlords database
	 * 
	 * @param landlordID unique ID of the landlord
	 * @param username   unique username of the landlord
	 * @param fName      first name
	 * @param lName      last name
	 * @param contactNum contact number
	 * @param email      email address
	 * @param age        landlord age
	 */
	public void insert(int landlordID, String username, String fName, String lName, String contactNum, String email,
			int age) {

		String sql = "INSERT INTO landlords(id, username, firstName, lastName, contactNumber, email, age) VALUES(?,?,?,?,?,?,?)";

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, landlordID);
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
	 * Method to delete a landlord from the existing landlords database
	 * 
	 * @param landlordID unique ID of the landlord
	 */
	public void delete(int landlordID) {

		String sql = "DELETE FROM landlords WHERE id = ?";

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			// Set the corresponding param
			pstmt.setInt(1, landlordID);

			// Execute the delete statement
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Method to display existing landlords currently in landlords database
	 */
	public void display() {

		try {

			Connection connection = connect();
			String sql = "SELECT * FROM landlords";

			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {

				int landlordID = result.getInt("id");
				String username = result.getString("username");
				String fName = result.getString("firstName");
				String lName = result.getString("lastName");
				String contactNum = result.getString("contactNumber");
				String email = result.getString("email");
				int age = result.getInt("age");

				System.out.println(landlordID + "|" + username + "|" + fName + "|" + lName + "|" + contactNum + "|"
						+ email + "|" + age);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error connecting to SQLite database");
			e.printStackTrace();
		}

	}
}
