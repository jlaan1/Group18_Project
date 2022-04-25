import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PropertyDatabase {

	/**
	 * Method to establish connection with SQLite database
	 * 
	 * @return Connection conn with SQLite database
	 */
	private static Connection connect() {
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
			System.out.println();
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

	}

}
