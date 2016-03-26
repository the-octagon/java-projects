import java.util.Scanner;
import java.io.*;

public class Cypher {
	static Scanner keyin = new Scanner(System.in);

	public static void main(String[] args){
		char[] message;
		char[] keyphrase;
		String endProgram = "no";

		while (endProgram.equals("no")) {
			//call options menu to encode or decode		
			callMenu();


			//exit using sentinel value
			System.out.println("Do you wish to end the program? (yes/no)");
			endProgram = keyin.next();
			endProgram = endProgram.toLowerCase();

			//validation loop for sentinel value
			while (!endProgram.equals("yes")&&!endProgram.equals("no")) {			
				keyin.next();
				System.out.println("Please enter yes or no.");
				endProgram = keyin.nextLine();
			}

		}


	}

	public static void callMenu() {
		int option = 0;

		System.out.println("Please choose from the following");
		System.out.println("1. encode a message");
		System.out.println("2. decode a message");
		option = keyin.nextInt();
		
		switch (option) {
		case 1 :
			encodeMessage();
			break;
		case 2:
			decodeMessage();
			break;
		}

	}

	public static void encodeMessage() {
		char[] message = getMessage();
		char[] keyphrase = getKey();
//		encoded = encode(message,keyphrase);
//		displayMessage(encoded);
	}

	public static void decodeMessage() {
		char [] codedMessage = getCoded();
		char [] keyphrase = getKey();
//		char [] decoded = decode(codedMessage,keyphrase);
//		displayMessage(decoded);
	}

	public static char[] getMessage() {
		Console cnsl = System.console();

		System.out.println("Please enter message to be encoded.");
		char[] message = cnsl.readPassword("Message: ");
	
		return message;
	}
	
	public static char[] getKey() {
		Console cnsl = System.console();

		System.out.println("Enter your keyphrase.");
		char[] keyphrase=cnsl.readPassword("Keyphrase: ");

		return keyphrase;
	}

	public static char[] getCoded() {
		
		return null;
	}

	public static void displayMessage(char[] output) {
		System.out.println("Your encoded message is: " + output);
	}
}
