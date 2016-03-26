import java.util.Scanner;
//import java.io.*;

public class TextReverse {
	static Scanner keyboard = new Scanner(System.in);
	
	public static void main(String[] args){
		//get word
		String input = getInput();
		
		//reverse word
		char[] tupni = getTupni(input);
		
		//print output
		displayResults(input, tupni);
		
    }
	
	public static String getInput(){
		System.out.println();
		System.out.println("Enter a word:");
		String input = keyboard.nextLine();
		System.out.println();
		
		return input;
	}
	
	public static char[] getTupni(String input) {
		int inputLength = input.length()-1;
		int index = 0;
		char[] tupni = new char[inputLength+1];
		
		while (index < inputLength+1) {
			tupni[index] = input.charAt(inputLength-index);
			index++;
		}
		
		return tupni;
	}
	
	public static void displayResults(String input, char[] tupni){
		System.out.println();
		System.out.println("The reverse of " + input + " is " + new String(tupni) + ".");
		if (input.replace(" ", "").equalsIgnoreCase(new String(tupni).replace(" ", ""))) {
			System.out.println("Congratulations! You've found a palindrome.");
		}
		System.out.println();
	}
}