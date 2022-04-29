import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class that represents the landlords database and will handle any
 * communications with the SQLite database
 * 
 * @author Jialei
 */
public class LandlordDB_Messenger {

	/**
	 * Method to establish connection with SQLite database
	 * 
	 * @return Connection conn with SQLite database
	 */
	private static Connection connect() {
		// SQLite connection string
		String url = "jdbc:sqlite:Data.db";
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
	public static void insert(int landlordID, String username, String password, String fName, String lName,
			String contactNum, String email, int age) {

		String sql = "INSERT INTO landlords VALUES(?,?,?,?,?,?,?,?)";

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, landlordID);
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
	 * Method to delete a landlord from the existing landlords database
	 * 
	 * @param landlordID unique ID of the landlord
	 */
	public static void delete(int landlordID) {

		String sql = "DELETE FROM landlords WHERE lID = ?";

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			// Set the corresponding param
			pstmt.setInt(1, landlordID);

			// Execute the delete statement
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Method to query password of desired landlord
	 * 
	 * @param landlordID unique ID of the landlord
	 * @return landlord's account password
	 */
	public static String queryPW(int landlordID) {

		String password = "";

		try {

			Connection connection = connect();
			String sql = "SELECT lPASSWORD FROM landlords WHERE lID = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, landlordID);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				password = result.getString("lPASSWORD");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error connecting to SQLite database");
			e.printStackTrace();
		}

		return password;
	}

	/**
	 * Method to update contact number of the landlord
	 * 
	 * @param landlordID unique landlord ID
	 * @param contact    contact number
	 */
	public static void updateContact(int landlordID, String contact) {

		try {

			Connection connection = connect();
			String sql = "UPDATE landlords SET lCONTACTNUM = ? where lID = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, contact);
			statement.setInt(2, landlordID);
			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error connecting to SQLite database");
			e.printStackTrace();
		}

	}

	/**
	 * Method to update email address of the landlord
	 * 
	 * @param landlordID unique landlord ID
	 * @param email      email address
	 */
	public static void updateEmail(int landlordID, String email) {

		try {

			Connection connection = connect();
			String sql = "UPDATE landlords SET lEMAIL = ? where lID = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			statement.setInt(2, landlordID);
			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error connecting to SQLite database");
			e.printStackTrace();
		}

	}

	/**
	 * Method to display existing landlords currently in landlords database
	 */
	public static void display() {

		System.out.println("LANDLORDID | USERNAME | FIRSTNAME | LASTNAME | NUMBER | EMAIL | AGE | PASSWORD");

		try {

			Connection connection = connect();
			String sql = "SELECT * FROM landlords";

			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {

				int landlordID = result.getInt("lID");
				String username = result.getString("lUSERNAME");
				String fName = result.getString("lFIRSTNAME");
				String lName = result.getString("lLASTNAME");
				String contactNum = result.getString("lCONTACTNUM");
				String email = result.getString("lEMAIL");
				int age = result.getInt("lAGE");
				String password = result.getString("lPASSWORD");

				System.out.println(landlordID + "|" + username + "|" + fName + "|" + lName + "|" + contactNum + "|"
						+ email + "|" + age + "|" + password);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error connecting to SQLite database");
			e.printStackTrace();
		}

	}

}
