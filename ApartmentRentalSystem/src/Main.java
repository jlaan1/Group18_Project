import java.util.Scanner;

public class Main {

	public static void checkUser() {
		System.out.println(
				"Are you a NEW or EXISTING user? Enter 'n' if you are a NEW user or 'e' if you are an EXISTING user.");
	}

	public static void invalidInputError() {
		System.out.println("Your input is invalid. Please try again.");
	}

	public static boolean validUser(String input) {
		return input.toLowerCase().contentEquals("n") || input.toLowerCase().contentEquals("e");
	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		checkUser();
		String input = scan.nextLine();
		while (!validUser(input)) {
			invalidInputError();
			checkUser();
			input = scan.nextLine();
		}

		scan.close();
	}

}
