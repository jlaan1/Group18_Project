import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void invalidInputError() {
		System.out.println("Your input is invalid. Please try again.");
	}

	/**
	 * Method to check whether current user is a new or existing user
	 * 
	 * @param scan Scanner to read user input
	 * @return 'n' if user is new; 'e' if user already exists
	 */
	public static String checkUser(Scanner scan) {

		String check = "Are you a NEW or EXISTING user? Enter 'n' if you are a NEW user or 'e' if you are an EXISTING user.";

		System.out.println(check);
		String input = scan.nextLine().toLowerCase();
		while (!(input.contentEquals("n") || input.contentEquals("e"))) {
			invalidInputError();
			System.out.println(check);
			input = scan.nextLine().toLowerCase();
		}

		System.out.println();

		return input;
	}

	/**
	 * Method to check type of account being created
	 * 
	 * @param scan Scanner to read user input
	 * @return 'c' if client account; 'l' if landlord account
	 */
	public static String createType(Scanner scan) {

		String register = "Are you registering to be a CLIENT or LANDLORD? Enter 'c' for CLIENT or 'l' for LANDLORD.";
		System.out.println(register);
		String accountType;
		while (true) {
			accountType = scan.nextLine().toLowerCase();
			if (accountType.contentEquals("c") || accountType.contentEquals("l")) {
				break;
			}
			invalidInputError();
			System.out.println();
			System.out.println(register);
		}

		System.out.println();

		return accountType;

	}

	/**
	 * Method to create ID of account
	 * 
	 * @param scan        Scanner to read user input
	 * @param accountType 'c' if client account; 'l' if landlord account
	 * @return account ID number
	 */
	public static int createIDNum(Scanner scan, String accountType) {

		int clientID = -1;
		int landlordID = -1;

		if (accountType.contentEquals("c")) {
			String createID = "Enter your desired client ID (numbers only): ";
			do {
				try {
					System.out.println(createID);
					clientID = scan.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("WARNING: Enter integer values ONLY. \n");
				}
				scan.nextLine();
			} while (clientID == -1);
		} else {
			String createID = "Enter your desired landlord ID (numbers only): ";
			do {
				try {
					System.out.println(createID);
					landlordID = scan.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("WARNING: Enter integer values ONLY. \n");
				}
				scan.nextLine();
			} while (landlordID == -1);
		}

		System.out.println();

		if (clientID == -1) {
			return landlordID;
		} else {
			return clientID;
		}

	}

	/**
	 * Method to create username of account
	 * 
	 * @param scan Scanner to read user input
	 * @return username
	 */
	public static String createUsername(Scanner scan) {

		String createUsername = "Enter your desired username (NO spaces allowed): ";
		System.out.println(createUsername);
		String username = scan.next().toLowerCase();
		scan.nextLine();
		System.out.println("Your username is: " + username);

		System.out.println();

		return username;

	}

	/**
	 * Method to create password for user
	 * 
	 * @param scan Scanner to read user input
	 * @return password
	 */
	public static String createPassword(Scanner scan) {

		String createPassword = "Enter your desired password: ";
		System.out.println(createPassword);
		String password = scan.nextLine();

		System.out.println();

		return password;

	}

	/**
	 * Method to obtain name of user
	 * 
	 * @param scan Scanner to read user input
	 * @return full name of user
	 */
	public static String[] createName(Scanner scan) {

		String createFirstName = "Enter your first name: ";
		System.out.println(createFirstName);
		String firstName = scan.nextLine();
		System.out.println("Your registered first name is: " + firstName);

		System.out.println();

		String createLastName = "Enter your last name: ";
		System.out.println(createLastName);
		String lastName = scan.nextLine();
		System.out.println("Your registered last name is: " + lastName);

		System.out.println();

		String[] name = { firstName, lastName };
		return name;
	}

	/**
	 * Method to obtain contact number of user
	 * 
	 * @param scan Scanner to read user input
	 * @return contact number of user
	 */
	public static String createNumber(Scanner scan) {

		long number = -1;
		String createContact = "Enter your contact number: ";
		do {
			try {
				System.out.println(createContact);
				number = scan.nextLong();
			} catch (InputMismatchException e) {
				System.out.println("WARNING: Enter integer values ONLY.");
			}
			scan.nextLine();
		} while (number == -1);
		String contactNum = Long.toString(number);
		System.out.println("Your registered contact number is: " + contactNum);

		System.out.println();

		return contactNum;

	}

	/**
	 * Method to obtain email of user
	 * 
	 * @param scan Scanner to read user input
	 * @return user email
	 */
	public static String createEmail(Scanner scan) {

		String createEmail = "Enter your email (NO spaces allowed): ";
		System.out.println(createEmail);
		String email = scan.next().toLowerCase();
		scan.nextLine();
		System.out.println("Your registered email is: " + email);

		System.out.println();

		return email;
	}

	/**
	 * Method to obtain age of user
	 * 
	 * @param scan Scanner to read user input
	 * @return age of user
	 */
	public static int createAge(Scanner scan) {

		int age = -1;
		String createAge = "Enter your age: ";
		do {
			try {
				System.out.println(createAge);
				age = scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("WARNING: Enter integer values ONLY.");
			}
			scan.nextLine();
		} while (age == -1);
		System.out.println("Your registered age is: " + age);

		System.out.println();

		return age;
	}

	/**
	 * Method to handle registration process for new user
	 * 
	 * @param scan Scanner to read user input
	 */
	public static void registration(Scanner scan) {

		String accountType = createType(scan);
		int idNum = createIDNum(scan, accountType);
		String username = createUsername(scan);
		String password = createPassword(scan);
		String[] fullName = createName(scan);
		String firstName = fullName[0];
		String lastName = fullName[1];
		String contactNum = createNumber(scan);
		String email = createEmail(scan);
		int age = createAge(scan);

		if (accountType.contentEquals("c")) {
			Client newClient = new Client(idNum, username, password, firstName, lastName, contactNum, email, age);
		} else {
			Landlord newLandlord = new Landlord(idNum, username, password, firstName, lastName, contactNum, email, age);
		}

	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		String input = checkUser(scan);

		if (input.contentEquals("n")) {
			registration(scan);
		}

		scan.close();
	}

}
