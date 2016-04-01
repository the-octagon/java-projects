/*  Text Reverser
    Copyright (C) {2016}  {Andrew King}

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
        email: gitgub.the.octagon@gmail.com
        github: the-octagon
*/

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
