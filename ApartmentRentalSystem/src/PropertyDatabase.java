import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class that represents the property database and will handle any
 * communications with the SQLite database
 * 
 * @author Jialei
 */
public class PropertyDatabase {

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
	 * Method to insert new property into property database
	 * 
	 * @param address
	 * @param propertyType
	 * @param zipCode
	 * @param numBR
	 * @param status
	 * @param monthlyRate
	 * @param clientID
	 * @param landlordID
	 */
	public static void insert(String address, String propertyType, int zipCode, int numBR, int status, int monthlyRate,
			int clientID, int landlordID) {

		String sql = "INSERT INTO property VALUES(?,?,?,?,?,?,?,?)";

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, address);
			pstmt.setString(2, propertyType);
			pstmt.setInt(3, zipCode);
			pstmt.setInt(4, numBR);
			pstmt.setInt(5, status);
			pstmt.setInt(6, monthlyRate);
			pstmt.setInt(7, clientID);
			pstmt.setInt(8, landlordID);
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
	public static void delete(String address) {

		String sql = "DELETE FROM property WHERE pADDRESS = ?";

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			// Set the corresponding param
			pstmt.setString(1, address);

			// Execute the delete statement
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Method to allow clients to make a reservation
	 * 
	 * @param clientID unique client ID
	 * @param address  address of the to-be-reserved property
	 */
	public static void makeReservation(int clientID, String address) {

		try {

			Connection connection = connect();
			String sql = "UPDATE property SET pCLIENTID = ? where pADDRESS = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, clientID);
			statement.setString(2, address);
			statement.executeUpdate();

			String sql2 = "UPDATE property SET pSTATUS = ? where pADDRESS = ?";

			statement = connection.prepareStatement(sql2);
			statement.setInt(1, 1);
			statement.setString(2, address);
			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error connecting to SQLite database");
			e.printStackTrace();
		}

	}

	/**
	 * Method to allow landlords to evict clients
	 * 
	 * @param address address of the property
	 */
	public static void evictClient(String address) {

		try {

			Connection connection = connect();
			String sql = "UPDATE property SET pCLIENTID = ? where pADDRESS = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, 0);
			statement.setString(2, address);
			statement.executeUpdate();

			String sql2 = "UPDATE property SET pSTATUS = ? where pADDRESS = ?";

			statement = connection.prepareStatement(sql2);
			statement.setInt(1, 0);
			statement.setString(2, address);
			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error connecting to SQLite database");
			e.printStackTrace();
		}

	}

	/**
	 * Method to enable search process for clients to find available apartments
	 * 
	 * @param lowerB lower bound of budget
	 * @param upperB upper bound of budget
	 */
	public static void queryMatch(int lowerB, int upperB) {

		boolean isEmpty = true;

		try {

			boolean dummy = true;

			Connection connection = connect();
			String sql = "SELECT pMONTHLYRATE, pSTATUS, lFIRSTNAME, lLASTNAME, lCONTACTNUM, pADDRESS FROM property, landlords WHERE pLANDLORDID = lID";

			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {

				String landlordContact = result.getString("lCONTACTNUM");
				String landlordFirst = result.getString("lFIRSTNAME");
				String landlordLast = result.getString("lLASTNAME");
				int monthlyRate = result.getInt("pMONTHLYRATE");
				int status = result.getInt("pSTATUS");
				String address = result.getString("pADDRESS");

				if ((monthlyRate >= lowerB && monthlyRate <= upperB) && status == 0) {

					if (dummy) {
						System.out.println("Your search yielded the following matching properties:");
						System.out.println("PROPERTY ADDRESS  |  LANDLORD'S NAME  |  RATE  |  LANDLORD'S CONTACT");
						dummy = false;
					}

					System.out.println(address + " | " + landlordFirst + " " + landlordLast + " | $" + monthlyRate
							+ " | " + landlordContact);

					isEmpty = false;

				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error connecting to SQLite database");
			e.printStackTrace();
		}

		if (isEmpty) {
			System.out.println("Your search yielded no results. Please update budget and try again.");
		}

	}

	public static void updatePrice(String address, int rate) {

		try {

			Connection connection = connect();
			String sql = "UPDATE property SET pMONTHLYRATE = ? where pADDRESS = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, rate);
			statement.setString(2, address);
			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error connecting to SQLite database");
			e.printStackTrace();
		}

	}

	/**
	 * Method to display properties belonging to a specific landlord
	 * 
	 * @param landlordID unique landlord ID
	 */
	public static void displayProperties(int landlordID) {

		boolean isEmpty = true;

		try {

			boolean dummy = true;

			Connection connection = connect();
			String sql = "SELECT * FROM property where pLANDLORDID = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, landlordID);

			// Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery();

			while (result.next()) {

				String address = result.getString("pADDRESS");
				String propertyType = result.getString("pPTYPE");
				int zipCode = result.getInt("pZIPCODE");
				int numBR = result.getInt("pNUMBR");
				int status = result.getInt("pSTATUS");
				int monthlyRate = result.getInt("pMONTHLYRATE");
				int cID = result.getInt("pCLIENTID");
				int lID = result.getInt("pLANDLORDID");

				if (dummy) {
					System.out.println("You have the following properties listed:");
					System.out.println();
					System.out.println(
							"PROPERTY ADDRESS  |  PROPERTY TYPE  |  ZIP  | BEDRMS | STATUS | RATE | CLIENTID | LANDLORDID");
					dummy = false;
				}

				System.out.println(address + "  |  " + propertyType + "  |  " + zipCode + "  |  " + numBR + "  |  "
						+ status + "  |  " + "$" + monthlyRate + "  |  " + cID + "  |  " + lID);

				isEmpty = false;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error connecting to SQLite database");
			e.printStackTrace();
		}

		if (isEmpty) {
			System.out.println("Oops...you have no listed properties.");
		}

	}

	/**
	 * Method to display existing properties currently in property database
	 */
	public static void display() {

		try {

			Connection connection = connect();
			String sql = "SELECT * FROM property";

			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {

				String address = result.getString("pADDRESS");
				String propertyType = result.getString("pPTYPE");
				int zipCode = result.getInt("pZIPCODE");
				int numBR = result.getInt("pNUMBR");
				int status = result.getInt("pSTATUS");
				int monthlyRate = result.getInt("pMONTHLYRATE");
				int clientID = result.getInt("pCLIENTID");
				int landlordID = result.getInt("pLANDLORDID");

				System.out.println(address + "|" + propertyType + "|" + zipCode + "|" + numBR + "|" + status + "|"
						+ monthlyRate + "|" + clientID + "|" + landlordID);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error connecting to SQLite database");
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		display();
	}

}
