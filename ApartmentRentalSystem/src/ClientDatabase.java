import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientDatabase {

	/**
	 * Method to establish connection with SQLite database
	 * 
	 * @return Connection conn with SQLite database
	 */
	private static Connection connect() {
		// SQLite connection string
		// absolute path
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
	public static void insert(int clientID, String username, String password, String fName, String lName,
			String contactNum, String email, int age) {

		String sql = "INSERT INTO clients VALUES(?,?,?,?,?,?,?,?)";

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, clientID);
			pstmt.setString(2, username);
			pstmt.setString(3, fName);
			pstmt.setString(4, lName);
			pstmt.setString(5, contactNum);
			pstmt.setString(6, email);
			pstmt.setInt(7, age);
			pstmt.setString(8, password);
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
	public static void delete(int clientID) {

		String sql = "DELETE FROM clients WHERE cID = ?";

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			// Set the corresponding param
			pstmt.setInt(1, clientID);

			// Execute the delete statement
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Method to query password of desired client
	 * 
	 * @param clientID unique ID of the client
	 * @return client's account password
	 */
	public static String queryPW(int clientID) {

		String password = "";

		try {

			Connection connection = connect();
			String sql = "SELECT cPASSWORD FROM clients WHERE cID = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, clientID);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				password = result.getString("cPASSWORD");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error connecting to SQLite database");
			e.printStackTrace();
		}

		return password;

	}

	/**
	 * Method to query the budget of desired client
	 * 
	 * @param clientID unique ID of client
	 * @return integer array {lower bound, upper bound}
	 */
	public static int[] queryBounds(int clientID) {

		int lower = 0;
		int upper = 0;

		try {

			Connection connection = connect();
			String sql = "SELECT cLBOUND FROM clients WHERE cID = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, clientID);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				lower = result.getInt("cLBOUND");
			}

			String sql2 = "SELECT cUBOUND FROM clients WHERE cID = ?";

			statement = connection.prepareStatement(sql2);
			statement.setInt(1, clientID);
			result = statement.executeQuery();

			while (result.next()) {
				upper = result.getInt("cUBOUND");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error connecting to SQLite database");
			e.printStackTrace();
		}

		int[] bounds = { lower, upper };
		return bounds;

	}

	/**
	 * Method to update budget of client in client database
	 * 
	 * @param clientID unique client ID
	 * @param lower    lower bound of budget
	 * @param upper    upper bound of budget
	 */
	public static void updateBounds(int clientID, int lower, int upper) {

		try {

			Connection connection = connect();
			String sql = "UPDATE clients SET cLBOUND = ? where cID = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, lower);
			statement.setInt(2, clientID);
			statement.executeUpdate();

			String sql2 = "UPDATE clients SET cUBOUND = ? where cID = ?";

			statement = connection.prepareStatement(sql2);
			statement.setInt(1, upper);
			statement.setInt(2, clientID);
			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error connecting to SQLite database");
			e.printStackTrace();
		}

	}

	/**
	 * Method to update bedroom requirements in client database
	 * 
	 * @param clientID unique client ID
	 * @param numBR    number of bedrooms
	 */
	public static void updateBR(int clientID, int numBR) {

		try {

			Connection connection = connect();
			String sql = "UPDATE clients SET cNUMBEDRM = ? where cID = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, numBR);
			statement.setInt(2, clientID);
			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error connecting to SQLite database");
			e.printStackTrace();
		}

	}

	/**
	 * Method to update contact in client database
	 * 
	 * @param clientID unique client ID
	 * @param contact  contact number
	 */
	public static void updateContact(int clientID, String contact) {

		try {

			Connection connection = connect();
			String sql = "UPDATE clients SET cCONTACTNUM = ? where cID = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, contact);
			statement.setInt(2, clientID);
			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error connecting to SQLite database");
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param clientID
	 * @param email
	 */
	public static void updateEmail(int clientID, String email) {

		try {

			Connection connection = connect();
			String sql = "UPDATE clients SET cEMAIL = ? where cID = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			statement.setInt(2, clientID);
			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error connecting to SQLite database");
			e.printStackTrace();
		}

	}

	/**
	 * Method to display existing clients currently in clients database
	 */
	public static void display() {

		try {

			Connection connection = connect();
			String sql = "SELECT * FROM clients";

			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {

				int clientID = result.getInt("cID");
				String username = result.getString("cUSERNAME");
				String fName = result.getString("cFIRSTNAME");
				String lName = result.getString("cLASTNAME");
				String contactNum = result.getString("cCONTACTNUM");
				String email = result.getString("cEMAIL");
				int age = result.getInt("cAGE");

				System.out.println(clientID + "|" + username + "|" + fName + "|" + lName + "|" + contactNum + "|"
						+ email + "|" + age);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error connecting to SQLite database");
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

	}

}
