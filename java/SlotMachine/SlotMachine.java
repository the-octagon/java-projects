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
		int[] wheel = new int[3];
		String[] wheelHuman = new String[3];
		int combo = 0;
		boolean threeWay = false;

		for (int index = 0; index < 3; index++) {
			wheel[index] = rollWheel();
			wheelHuman[index] = getWheelHuman(wheel[index]);
		}

		if (wheel[0] == wheel[1] && wheel[0] == wheel[2]) {
			threeWay = true;
		}

		if (threeWay == true) {
			if (wheel[0] == 1) {
				combo = 1;
			} else if (wheel[0] == 2) {
				combo = 2;
			} else if (wheel[0] == 3) {
				combo = 3;
			} else if (wheel[0] == 4) {
				combo = 4;
			} else if (wheel[0] == 5) {
				combo = 5;
			} else if (wheel[0] == 6) {
				combo = 6;
			}
		} else if ((wheel[0] == 3|| wheel[0] == 4 || wheel[0] == 5) && (wheel[1] == 3|| wheel[1] == 4 || wheel[1] == 5) && (wheel[2] == 3|| wheel[2] == 4 || wheel[2] == 5)) {
			combo = 7;
		} else if (wheel[0] == 6 || wheel[1] == 6 || wheel[2] == 6) {
			combo = 8;
		} else {
			combo = 9;
		}

//		results(combo);

		System.out.println(wheel[0] + " " + wheel[1] + " " + wheel[2]);
		System.out.println(wheelHuman[0] + " " + wheelHuman[1] + " " + wheelHuman[2]);
		System.out.println(combo);


	}

	public static int rollWheel() {

		//random between 1 and 7
		return 1 + (int)(Math.random() * ((6 - 1) + 1));

	}

	public static String getWheelHuman(int wheel) {
		String wheelHuman = new String();

		switch (wheel) {
			case 1:
				wheelHuman = "Diamonds";
				break;

			case 2:
				wheelHuman = "Seven";
				break;

			case 3:
				wheelHuman = "Bar x3";
				break;

			case 4:
				wheelHuman = "Bar x2";
				break;

			case 5:
				wheelHuman = "Bar";
				break;

			case 6:
				wheelHuman = "Cherry";
				break;
		}
		return wheelHuman;

	}
/*
	public static void results(int combo, int[] wheel) {
		switch(combo) {
			case 1:
				
				break;

			case 2:
				
				break;

			case 3:
				
				break;

			case 4:
				
				break;

			case 5:
				
				break;

			case 6:
				
				break;

			case 7:
				
				break;

			case 8:
				
				break;
		}

	}
*/
}

