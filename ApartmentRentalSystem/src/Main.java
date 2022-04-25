import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	/**
	 * Method to print error message for invalid inputs
	 */
	public static void invalidInputError() {
		System.out.println("Your input is invalid. Please try again.");
	}

	/**
	 * Method to print error message for invalid username or password
	 */
	public static void invalidCredentialsError() {
		System.out.println("Your credentials are invalid. Please try again.");
	}

	/**
	 * Method to print successful exit of application
	 */
	public static void exitMessage() {
		System.out.println("You have exited the application. Thank you for using ApartmentRentals.");
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
					System.out.print(createID);
					clientID = scan.nextInt();
				} catch (InputMismatchException e) {
					invalidInputError();
				}
				scan.nextLine();
			} while (clientID == -1);
		} else {
			String createID = "Enter your desired landlord ID (numbers only): ";
			do {
				try {
					System.out.print(createID);
					landlordID = scan.nextInt();
				} catch (InputMismatchException e) {
					invalidInputError();
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
		System.out.print(createUsername);
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
		System.out.print(createPassword);
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
		System.out.print(createFirstName);
		String firstName = scan.nextLine();
		System.out.println("Your registered first name is: " + firstName);

		System.out.println();

		String createLastName = "Enter your last name: ";
		System.out.print(createLastName);
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
				System.out.print(createContact);
				number = scan.nextLong();
			} catch (InputMismatchException e) {
				invalidInputError();
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
		System.out.print(createEmail);
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
				System.out.print(createAge);
				age = scan.nextInt();
			} catch (InputMismatchException e) {
				invalidInputError();
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

		String success = "You have successfully registered for an account. You will now be redirected to the login window.";
		System.out.println(success);

		System.out.println();

	}

	/**
	 * Method to handle existing user login process
	 * 
	 * @param scan Scanner to read user input
	 */
	public static String[] existingUser(Scanner scan) {

		String enterUsername = "Are you a client or landlord? Enter 'c' for client or 'l' for landlord. Enter 'e' to exit.";
		System.out.println(enterUsername);
		String inputType = scan.nextLine().toLowerCase();
		while (!(inputType.contentEquals("c") || inputType.contentEquals("l") || inputType.contentEquals("e"))) {

			invalidInputError();

			System.out.println();

			System.out.println(enterUsername);
			inputType = scan.nextLine().toLowerCase();
		}

		if (inputType.contentEquals("e")) {
			exitMessage();
			System.exit(0);
		}

		String enterID = "Please enter your ID number: ";
		System.out.println();
		int inputID = -1;
		do {
			try {
				System.out.print(enterID);
				inputID = scan.nextInt();
			} catch (InputMismatchException e) {
				invalidInputError();
				System.out.println();
			}
			scan.nextLine();
		} while (inputID == -1);

		System.out.println();

		String enterPW = "Please enter your password: ";
		System.out.print(enterPW);
		String inputPW = scan.nextLine();

		System.out.println();

		String queryPW;
		if (inputType.contentEquals("c")) {
			queryPW = ClientDatabase.queryPW(inputID);
		} else {
			queryPW = LandlordDatabase.queryPW(inputID);
		}
		if (queryPW.contentEquals(inputPW)) {
			System.out.println("Login Successful");
		} else {
			invalidCredentialsError();
			System.out.println();
			existingUser(scan);
		}

		System.out.println();

		String[] toRet = { inputType, Integer.toString(inputID) };

		return toRet;

	}

	/**
	 * Method to print list of menu options for clients
	 */
	public static int clientMenu(Scanner scan) {

		int MAX_MENU = 7;
		int MIN_MENU = 1;

		System.out.println("MENU OPTIONS \n" + "1. Update price range \n" + "2. Update desired number of bedrooms \n"
				+ "3. Search for available housing \n" + "4. Rent an apartment \n" + "5. Update contact number \n"
				+ "6. Update email address \n" + "7. Exit application");

		System.out.println();

		String choice = "Please enter your choice: ";
		int inputChoice = -1;
		do {
			try {
				System.out.print(choice);
				inputChoice = scan.nextInt();
				System.out.println();
				if (inputChoice < MIN_MENU || inputChoice > MAX_MENU) {
					System.out.println("Please enter a valid option.");
					System.out.println();
				}
			} catch (InputMismatchException e) {
				invalidInputError();
				System.out.println();
			}
			scan.nextLine();
		} while (inputChoice < MIN_MENU || inputChoice > MAX_MENU);

		return inputChoice;

	}

	/**
	 * Method to update budget of client
	 * 
	 * @param scan     Scanner to read user input
	 * @param clientID unique client ID
	 */
	public static void processBudget(Scanner scan, int clientID) {

		String lowerb = "Please enter the lower bound of your budget: ";
		int inputLower = -1;
		do {
			try {
				System.out.print(lowerb);
				inputLower = scan.nextInt();
				System.out.println();
				if (inputLower < 0) {
					System.out.println("Please enter a positive number.");
					System.out.println();
				}
			} catch (InputMismatchException e) {
				invalidInputError();
			}
			scan.nextLine();
		} while (inputLower < 0);

		String upperb = "Please enter the upper bound of your budget: ";
		int inputUpper = -1;
		do {
			try {
				System.out.print(upperb);
				inputUpper = scan.nextInt();
				System.out.println();
				if (inputUpper < 0) {
					System.out.println("Please enter a positive number.");
					System.out.println();
				} else {
					if (inputUpper < inputLower) {
						System.out.println("Please enter an upper bound that is greater than the lower bound");
						System.out.println();
					}
				}
			} catch (InputMismatchException e) {
				invalidInputError();
			}
			scan.nextLine();
		} while (inputUpper < 0 || (inputUpper < inputLower));

		ClientDatabase.updateBounds(clientID, inputLower, inputUpper);

		System.out.println("Successfully updated the lower bound of your budget to $" + inputLower
				+ " and the upper bound of your budget to $" + inputUpper);

		System.out.println();

		System.out.println("Returning to main menu");

		System.out.println();

	}

	/**
	 * Method to update desired number of bedrooms for clients
	 * 
	 * @param scan     Scanner to read user input
	 * @param clientID unique client ID
	 */
	public static void processNumBR(Scanner scan, int clientID) {

		String numbr = "Please enter the required number of bedrooms: ";
		int inputBR = -1;
		do {
			try {
				System.out.print(numbr);
				inputBR = scan.nextInt();
				System.out.println();
				if (inputBR < 0) {
					System.out.println("Please enter a positive number.");
					System.out.println();
				}
			} catch (InputMismatchException e) {
				invalidInputError();
			}
			scan.nextLine();
		} while (inputBR < 0);

		ClientDatabase.updateBR(clientID, inputBR);

		System.out.println("Successfully updated the required number of bedrooms to " + inputBR);

		System.out.println();

		System.out.println("Returning to main menu");

		System.out.println();

	}

	/**
	 * Method enabling clients to search for matching and available properties for
	 * rent
	 * 
	 * @param scan     Scanner to read user input
	 * @param clientID unique client ID
	 */
	public static void processSearch(Scanner scan, int clientID) {

		int[] bounds = ClientDatabase.queryBounds(clientID);
		PropertyDatabase.queryMatch(bounds[0], bounds[1]);

		System.out.println();

		System.out.println("Returning to main menu");

		System.out.println();
	}

	/**
	 * Method enabling clients to reserve a property
	 * 
	 * @param scan     Scanner to read user input
	 * @param clientID unique client ID
	 */
	public static void processReservation(Scanner scan, int clientID) {

		String enterAddress = "Please enter the address of your desired property: ";
		System.out.println(enterAddress);
		String inputAddress = scan.nextLine();
		PropertyDatabase.makeReservation(clientID, inputAddress);

		System.out.println(
				"You have successfully made a reservation at " + inputAddress + "! The landlord will be notified.");

		System.out.println();

		System.out.println("Returning to main menu");

		System.out.println();

	}

	/**
	 * Method enabling clients to update contact number
	 * 
	 * @param scan     Scanner to read user input
	 * @param clientID unique client ID
	 */
	public static void processUpdateContact(Scanner scan, int clientID) {

		long number = -1;
		String updateContact = "Enter your updated contact number: ";
		do {
			try {
				System.out.print(updateContact);
				number = scan.nextLong();
			} catch (InputMismatchException e) {
				invalidInputError();
			}
			scan.nextLine();
		} while (number == -1);

		String contactNum = Long.toString(number);
		ClientDatabase.updateContact(clientID, contactNum);

		System.out.println();

		System.out.println("You have successfully updated your contact number to: " + contactNum);

		System.out.println();

		System.out.println("Returning to main menu");

		System.out.println();

	}

	/**
	 * Method to enable clients to update email
	 * 
	 * @param scan     Scanner to read user input
	 * @param clientID unique client ID
	 */
	public static void processUpdateEmail(Scanner scan, int clientID) {

		String updateEmail = "Enter your email (NO spaces allowed): ";
		System.out.print(updateEmail);
		String email = scan.next().toLowerCase();
		scan.nextLine();
		System.out.println("Your registered email is: " + email);

		ClientDatabase.updateEmail(clientID, email);

		System.out.println();

		System.out.println("You have successfully updated your email address to: " + email);

		System.out.println();

		System.out.println("Returning to main menu");

		System.out.println();

	}

	/**
	 * Method to print list of menu options for landlords
	 */
	public static int landlordMenu(Scanner scan) {

		int MIN_MENU = 1;
		int MAX_MENU = 4;

		System.out.println(
				"MENU OPTIONS \n" + "1. Add a property \n" + "2. Remove a property" + "3. View owned properties \n"
						+ "4. Update a property listing \n" + "5. Update personal information \n");

		System.out.println();

		String choice = "Please enter your choice: ";
		int inputChoice = -1;
		do {
			try {
				System.out.print(choice);
				inputChoice = scan.nextInt();
				System.out.println();
				if (inputChoice < MIN_MENU || inputChoice > MAX_MENU) {
					System.out.println("Please enter a valid option.");
					System.out.println();
				}
			} catch (InputMismatchException e) {
				invalidInputError();
				System.out.println();
			}
			scan.nextLine();
		} while (inputChoice < MIN_MENU || inputChoice > MAX_MENU);

		return inputChoice;

	}

	/**
	 * Method to obtain address of the new property
	 * 
	 * @param scan Scanner to read user input
	 * @return address of the property
	 */
	public static String createAddress(Scanner scan) {

		String createAddress = "Enter the address of your property: ";
		System.out.print(createAddress);
		String inputAddress = scan.nextLine();
		System.out.println("The registered address of your property is: " + inputAddress);

		System.out.println();

		return inputAddress;

	}

	/**
	 * Method to obtain type of property
	 * 
	 * @param scan Scanner to read user input
	 * @return 'a' if property is an apartment or 'h' if property is a house
	 */
	public static String createPropertyType(Scanner scan) {

		String type = "Is this property an APARTMENT or HOUSE? Enter 'a' for APARTMENT or 'h' for HOUSE.";

		System.out.println(type);
		String inputType = scan.nextLine().toLowerCase();
		while (!(inputType.contentEquals("a") || inputType.contentEquals("h"))) {
			invalidInputError();
			System.out.println(type);
			inputType = scan.nextLine().toLowerCase();
		}

		System.out.println();

		return inputType;

	}

	/**
	 * Method to obtain zip code of property
	 * 
	 * @param scan Scanner to read user input
	 * @return zip code of the property
	 */
	public static int createZip(Scanner scan) {

		int zip = -1;
		String zipCode = "Enter the zip code of your property: ";
		do {
			try {
				System.out.print(zipCode);
				zip = scan.nextInt();
			} catch (InputMismatchException e) {
				invalidInputError();
			}
			scan.nextLine();
		} while (zip == -1);
		System.out.println("The registered zip code of your address is: " + zip);

		System.out.println();

		return zip;

	}

	/**
	 * Method to obtain monthly rate of the property
	 * 
	 * @param scan Scanner to read user input
	 * @return monthly rate of the property
	 */
	public static int createRate(Scanner scan) {

		int rate = -1;
		String price = "Enter the expected monthly rate of your property: ";
		do {
			try {
				System.out.print(price);
				rate = scan.nextInt();
			} catch (InputMismatchException e) {
				invalidInputError();
			}
			scan.nextLine();
		} while (rate == -1);
		System.out.println("The registered zip code of your address is: " + rate);

		System.out.println();

		return rate;

	}

	/**
	 * Method to obtain number of bedrooms of the property
	 * 
	 * @param scan Scanner to read user input
	 * @return number of bedrooms of the property
	 */
	public static int createnumBR(Scanner scan) {

		int numBR = -1;
		String createBR = "Enter the number of bedrooms in your property (NOTE: Enter 0 for a studio apartment): ";
		do {
			try {
				System.out.print(createBR);
				numBR = scan.nextInt();
			} catch (InputMismatchException e) {
				invalidInputError();
			}
			scan.nextLine();
		} while (numBR == -1);
		System.out.println("The registered number of bedrooms in your apartment is: " + numBR);

		System.out.println();

		return numBR;

	}

	/**
	 * Method to allow landlords to add a new property
	 * 
	 * @param scan       Scanner to read user input
	 * @param landlordID unique landlord ID
	 */
	public static void processAddProperty(Scanner scan, int landlordID) {

		String address = createAddress(scan);
		String propertyType = createPropertyType(scan);
		int zipCode = createZip(scan);
		int numBR = createnumBR(scan);
		int monthlyRate = createRate(scan);
		int status = 0;
		int cID = 0;
		int lID = landlordID;

		Property property = new Property(address, propertyType, zipCode, numBR, status, monthlyRate, cID, lID);

	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		String input = checkUser(scan);
		String[] existingUser;

		if (input.contentEquals("n")) {
			registration(scan);
			existingUser = existingUser(scan);
		} else {
			existingUser = existingUser(scan);
		}

		int choice;
		int iD = Integer.parseInt(existingUser[1]);

		if (existingUser[0].contentEquals("c")) {
			do {
				choice = clientMenu(scan);
				switch (choice) {
				case 1:
					processBudget(scan, iD);
					break;
				case 2:
					processNumBR(scan, iD);
					break;
				case 3:
					processSearch(scan, iD);
					break;
				case 4:
					processReservation(scan, iD);
					break;
				case 5:
					processUpdateContact(scan, iD);
					break;
				case 6:
					processUpdateEmail(scan, iD);
					break;
				case 7:
					exitMessage();
					scan.close();
					System.exit(0);
				default:
					// NOTE: Will not get here because clientMenu handles invalid inputs
					System.out.println("Oops...Something went wrong...");
					scan.close();
					System.exit(0);
				}
			} while (true);

		} else {

			do {
				choice = landlordMenu(scan);
				switch (choice) {
				case 1:
					processAddProperty(scan, iD);
					break;
				case 7:
					exitMessage();
					scan.close();
					System.exit(0);
				default:
					// NOTE: Will not get here because landlordMenu handles invalid inputs
					System.out.println("Oops...Something went wrong...");
					scan.close();
					System.exit(0);
				}
			} while (true);
		}

	}

}
