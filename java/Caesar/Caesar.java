import java.util.Scanner;
import java.lang.String;

public class Caesar {
	static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args){
		int shift = getShift();
		String word = getWord();

		String encrypted = encrypt(word);
	}

	public static int getShift(){
		System.out.println("Enter the shift value");
		int shift = keyboard.nextInt();

		return shift;
	}

	public static String getWord() {
		System.out.println("Enter your message.");
		keyboard.next();
		String word = keyboard.nextLine();

		return word;
	}

	public static String encrypt(String word){
		String encrypted = word;
		return encrypted;
	}
}
