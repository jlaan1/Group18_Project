import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Client {

	private Connection connect() {
		// SQLite connection string
		String url = "jdbc:sqlite://Users/ljialei/Documents/Boston University/Junior/Spring 2022/"
				+ "CAS CS411/Group18_Project/ApartmentRentalSystem/clientData.db";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

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

	public static void main(String[] args) {

		Client client = new Client();
		client.insert(2, "nanny23", "Nanny", "Mcphee", "8578692812", "nannymcphee@bu.edu", 42);
		client.display();

	}

}
