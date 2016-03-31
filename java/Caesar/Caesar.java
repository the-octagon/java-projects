//for password input
import java.io.Console;
//for string and int funcs
import java.lang.*;
//for scanner and exception catching
import java.util.*;

public class Caesar {
	//global scanner object
	static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args){
		//menu option
		int option = 0;
		String optionString = new String("");
		while (true) {
			System.out.println("Choose from the following:");
			System.out.println("1. Encrypt");
			System.out.println("2. Decrypt");
			System.out.println("3. Exit");
			//input into string with try catch leading into validation loop, ensuring 1 2 or 3 put into options case switch
			optionString = keyboard.nextLine();
			try {
				option = Integer.parseInt(optionString);
			}catch (NumberFormatException e) {
			}catch (InputMismatchException f) {
			}

			while (option!=1&&option!=2&&option!=3){
				System.out.println("Please enter a valid choice of 1, 2, or 3");
				optionString = keyboard.nextLine();
				try {
					option = Integer.parseInt(optionString);
				}catch (NumberFormatException e) {
				}catch (InputMismatchException f) {
				}
			}
			switch (option) {
				case 1:
					encryptMessage();
					break;
				case 2:
					decryptMessage();
					break;
				case 3:
					System.exit(0);
			}
		}
	}


	//steps to run through for encryption route
	public static void encryptMessage() {
		int shift = getShift();
		String message = getMessage();
		String encrypted = encrypt(message,shift);
		printOut(encrypted);
	}

	//steps to run through for decryption route
	public static void decryptMessage() {
		int shift = getShift();
		String message = getMessage();
		String decrypted = decrypt(message,shift);
		printOut(decrypted);
	}

	//take user input for shift value, feed through try catch to check value is numeric and doesn't violate rules
	public static int getShift(){
		System.out.println("Enter the shift value");
		int shift = 0;
		String shiftString = keyboard.nextLine();
		try {
			shift = Integer.parseInt(shiftString);
		} catch (InputMismatchException e) {
		}catch (NumberFormatException f){
		}
		while (shift % 41 == 0){
			System.out.println("Please enter a whole number that is not a multiple of 41.");
			shiftString = keyboard.nextLine();
			try {
				shift = Integer.parseInt(shiftString);
			} catch (InputMismatchException e) {
			}
		}

		return shift;
	}

	//take user input with readPassword function to hide CLI input and save to char array, save to string for easy toLower
	public static String getMessage() {
		Console console = System.console();
		System.out.println("Enter your message. (Text will not appear)");
		char[] messageArray = console.readPassword();
		String message = new String(messageArray);
		System.out.println();
		message = message.toLowerCase();
		return message;
	}

	//dump message to stdout
	public static void printOut(String message) {
		System.out.println("Your secret message is:");
		System.out.println("\""+message+"\"");
		System.out.println();
		System.out.println();
	}

	//v2 of encrypt function that removes most limitations on functionality.
	public static String encrypt(String message, int shift) {
		char[] key =  {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' ','.',',','?','/','\'','1','2','3','4','5','6','7','8','9','0'};
		int length = message.length();
		char[] messageArray = message.toCharArray();
		char[] encryptedArray = new char[length];
		int keyIndex = 0;
		int shiftIndex = 0;
		//run once per char of word
		for (int index = 0;index < length;index++) {
			//search for char of message in key array
			while (messageArray[index]!=key[keyIndex]){
				keyIndex++;
			}
			//get index of shifted letter
			shiftIndex = keyIndex + shift;
			//normalize for too high values
			while (shiftIndex>41){
				shiftIndex = shiftIndex - 41;
			}
			//normalize for too low values, account for 0 as allowed value
			while (shiftIndex<0){
				shiftIndex = shiftIndex + 42;
			}
			//set letter in encrypted message as normalized shifted letter
			encryptedArray[index] = key[shiftIndex];
			//reset keyIndex
			keyIndex = 0;
		}
		//change to string
		String encrypted = new String(encryptedArray);
		return encrypted;
	}

	//v2 of decrypt function... see above for step by step. only difference is subtract instead of add shiftIndex
        public static String decrypt(String message, int shift) {
                char[] key =  {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' ','.',',','?','/','\'','1','2','3','4','5','6','7','8','9','0'};
                int length = message.length();
                char[] messageArray = message.toCharArray();
                char[] decryptedArray = new char[length];
                int keyIndex = 0;
                int shiftIndex = 0;
                for (int index = 0;index < length;index++) {
                        while (messageArray[index]!=key[keyIndex]){
                                keyIndex++;
                        }
                        shiftIndex = keyIndex - shift;
                        while (shiftIndex<0) {
                                shiftIndex = shiftIndex + 41;
                        }
			while (shiftIndex>41) {
				shiftIndex = shiftIndex - 42;
			}
                        decryptedArray[index] = key[shiftIndex];
                        keyIndex = 0;
                }
                String decrypted = new String(decryptedArray);
                return decrypted;
        }

}
