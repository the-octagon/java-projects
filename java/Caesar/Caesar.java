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

	public static void encryptMessage() {
		int shift = getShift();
		String message = getMessage();
		String encrypted = encrypt(message,shift);
		printOut(encrypted);
	}

	public static void decryptMessage() {
		int shift = getShift();
		String message = getMessage();
		String decrypted = decrypt(message,shift);
		printOut(decrypted);
	}
	public static int getShift(){
		System.out.println("Enter the shift value");
		int shift = 0;
		String shiftString = keyboard.nextLine();
		try {
			shift = Integer.parseInt(shiftString);
		} catch (InputMismatchException e) {
		}catch (NumberFormatException f){
		}
		while (shift==31||shift==0){
			System.out.println("Please enter a numerical value.");
			shiftString = keyboard.nextLine();
			try {
				shift = Integer.parseInt(shiftString);
			} catch (InputMismatchException e) {
			}
		}

		return shift;
	}

	public static String getMessage() {
		Console console = System.console();
		System.out.println("Enter your message. (Text will not appear)");
		char[] messageArray = console.readPassword();


		String message = new String(messageArray);
		System.out.println();
		message = message.toLowerCase();
		return message;
	}

	//version 1 of encrypted, very limited
/*	public static String encrypt(String message,int shift){
		int length = message.length();
		char[] messageArray = message.toCharArray();
		char[] encryptedArray = new char[length];
		for (int index = 0;index < length; index++) {
			encryptedArray[index] = (char)(messageArray[index] + shift);
		}
		String encrypted = new String(encryptedArray);
		return encrypted;
	}*/

/*	public static String decrypt(String message,int shift) {
		int length = message.length();
		char[] messageArray = message.toCharArray();
		char[] decryptedArray = new char[length];
		for (int index = 0;index < length; index++) {
			decryptedArray[index] = (char)(messageArray[index]-shift);
		}
		String decrypted = new String(decryptedArray);
		return decrypted;
	}*/

	public static void printOut(String message) {
		System.out.println("Your secret message is:");
		System.out.println("\""+message+"\"");
		System.out.println();
		System.out.println();
	}

	public static String encrypt(String message, int shift) {
		char[] key =  {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' ','.',',','?','/','\''};
		int length = message.length();
		char[] messageArray = message.toCharArray();
		char[] encryptedArray = new char[length];
		int keyIndex = 0;
		int shiftIndex = 0;
		for (int index = 0;index < length;index++) {
			while (messageArray[index]!=key[keyIndex]){
				keyIndex++;
			}
			shiftIndex = keyIndex + shift;
			while (shiftIndex>31){
				shiftIndex = shiftIndex - 31;
			}
			while (shiftIndex<0){
				shiftIndex = shiftIndex + 32;
			}
			encryptedArray[index] = key[shiftIndex];
			keyIndex = 0;
		}
		String encrypted = new String(encryptedArray);
		return encrypted;
	}

        public static String decrypt(String message, int shift) {
                char[] key =  {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' ','.',',','?','/','\''};
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
                                shiftIndex = shiftIndex + 31;
                        }
			while (shiftIndex>31) {
				shiftIndex = shiftIndex - 32;
			}
                        decryptedArray[index] = key[shiftIndex];
                        keyIndex = 0;
                }
                String decrypted = new String(decryptedArray);
                return decrypted;
        }

}

