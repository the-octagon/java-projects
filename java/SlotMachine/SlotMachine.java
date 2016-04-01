/*  Slot Machine
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

//for scanner and exption catching
import java.util.*;

public class SlotMachine {

	//key = Scanner object reading keyboard
	static Scanner key = new Scanner(System.in);

	public static void main(String[] args) {
		//declare local vars
		int option = 0;
		String optionString = "";

		while (true) {
			//print menut to stdout
			System.out.println("Choose an option:");
			System.out.println("1. Pull lever");
			System.out.println("2. exit");	

			//input menu option as string
			optionString = key .nextLine();

			//try catch to ensure numerical input
 			try {
				option = Integer.parseInt(optionString);
			} catch (NumberFormatException e) {
			} catch (InputMismatchException f) {
			}

			//case switch for option menu
			switch (option) {
				case 1:
					pullLever();
					break;
				case 2:
					System.exit(0);
			}
		}
	}

	public static void pullLever() {
		//gen random 1
		//gen random 2
		//gen random 3
		//gen random 4
		//how many things are on a slot machine???

		//if combo, then win?
	}


}
