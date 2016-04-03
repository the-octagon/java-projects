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
import java.text.*;

public class SlotMachine {

	//key = Scanner object reading keyboard
	static Scanner key = new Scanner(System.in);

	public static void main(String[] args) {
		//declare local vars
		int option = 0;
		String optionString = "";
		int[] coinsPlays = {16,0};
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);

		while (true) {
			option = 0;
			optionString = "";
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("Bank: " + currencyFormatter.format((double)(coinsPlays[0] * 0.25)));
			System.out.println("Plays: " + coinsPlays[1]);
			System.out.println();
			//print menut to stdout
			System.out.println("Choose an option:");
			System.out.println("1. Insert dollar");
			System.out.println("2. Pull lever");
			System.out.println("3. exit");	

			//input menu option as string
			optionString = key.nextLine();

			//try catch to ensure numerical input
 			try {
				option = Integer.parseInt(optionString);
			} catch (NumberFormatException e) {
				}

			while (option != 1 && option != 2 && option != 3) {

				System.out.println("Please choose a valid option.");
				optionString = key.nextLine();
				try {
					option = Integer.parseInt(optionString);
				} catch (NumberFormatException e) {
					}

			}

			//case switch for option menu
			switch (option) {
				case 1:
					coinsPlays = insertCoin(coinsPlays);
					break;
				case 2:
					pullLever(coinsPlays);
					break;
				case 3:
					System.exit(0);
			}
		}
	}

	public static int[] insertCoin(int[] coinsPlays) {

		if (coinsPlays[0] != 0) {
			coinsPlays[0] = coinsPlays[0] - 4;
			coinsPlays[1] = coinsPlays[1] + 1;
		} else if (coinsPlays[0] == 0 && coinsPlays[1] != 0) {
			System.out.println("You're out of money, but you have some plays left.");
		} else {
			System.out.println("You're out of money and plays! Goodbye.");
			System.exit(0);
		}

		return coinsPlays;

	}


	public static int[] pullLever(int[] coinsPlays) {
		int[] wheel = new int[3];
		String[] wheelHuman = new String[3];
		int combo = 8;
		boolean threeWay = false;


		if (coinsPlays[1] != 0) {
			for (int index = 0; index < 3; index++) {
				wheel[index] = rollWheel();
			}

			if (wheel[0] == wheel[1] && wheel[0] == wheel[2]) {
				threeWay = true;
			}

			if (threeWay == true) {
				if (wheel[0] == 0) {
					combo = 0;
					coinsPlays[0] = coinsPlays[0] +  2000;
				} else if (wheel[0] == 1) {
					combo = 1;
					coinsPlays[0] = coinsPlays[0] +  500;
				} else if (wheel[0] == 2) {
					combo = 2;
					coinsPlays[0] = coinsPlays[0] +  52;
				} else if (wheel[0] == 3) {
					combo = 3;
					coinsPlays[0] = coinsPlays[0] +  32;
				} else if (wheel[0] == 4) {
					combo = 4;
					coinsPlays[0] = coinsPlays[0] +  16;
				} else if (wheel[0] == 5) {
					combo = 5;
					coinsPlays[0] = coinsPlays[0] +  16;
				}
			} else if ((wheel[0] == 2|| wheel[0] == 3 || wheel[0] == 4) && (wheel[1] == 2 || wheel[1] == 3 || wheel[1] == 4) && (wheel[2] == 2 || wheel[2] == 3 || wheel[2] == 4)) {
				combo = 6;
				coinsPlays[0] = coinsPlays[0] +  4;
			} else if (wheel[0] == 5 || wheel[1] == 5 || wheel[2] == 5) {
				combo = 7;
				coinsPlays[0] = coinsPlays[0] +  4;
			} else {
				combo = 8;
			}
			coinsPlays[1] = coinsPlays[1] - 1;

			results(combo, wheel);

		} else {
			System.out.println("You have run out of plays. Please insert more coins.");
		}

		return coinsPlays;

	}

	public static int rollWheel() {

		//random btwn 0-5
		int roll = (int)(Math.random() * 5);

		return roll;

	}

	public static String getWheelHuman(int wheel) {
		String wheelHuman = new String();
		String[] wheelHumanArray = {
			"Diamonds	",
			"Seven		",
			"Bar x3		",
			"Bar x2		",
			"Bar		",
			"Cherry		"};

		wheelHuman = wheelHumanArray[wheel];
		return wheelHuman;

	}

	public static void results(int combo, int[] wheel) {

		switch(combo) {
			case 0:
				printWheelHuman(wheel);
				System.out.println();
				youWin("JACKPOT!",2000);
				break;

			case 1:
				printWheelHuman(wheel);
				youWin("WINNER!", 500);
				break;

			case 2:
				printWheelHuman(wheel);
				youWin("Winner!",52);
				break;

			case 3:
				printWheelHuman(wheel);
				youWin("Winner!",32);
				break;

			case 4:
				printWheelHuman(wheel);
				youWin("Winner!",16);
				break;

			case 5:
				printWheelHuman(wheel);
				youWin("Winner!",16);
				break;

			case 6:
				printWheelHuman(wheel);
				youWin("Winner!",4);
				break;

			case 7:
				printWheelHuman(wheel);
				youWin("Winner!",4);
				break;

			case 8:
				printWheelHuman(wheel);
				youWin("Better luck next time!",0);
				break;
		}

	}

	public static void printWheelHuman(int[] wheel) {

                int[] wheelPlusOne = new int[3];
                int[] wheelMinusOne = new int[3];


                for (int index = 0; index < 3; index++) {
                        if  (wheel[index] == 0) {
                                wheelMinusOne[index] = 5;
                                wheelPlusOne[index] = 1;
                        } else if (wheel[index] == 5) {
                                wheelMinusOne[index] = 4;
                                wheelPlusOne[index] = 0;
                        } else {
                                wheelMinusOne[index] = wheel[index] - 1;
                                wheelPlusOne[index] = wheel[index] + 1;
                        }
                }
			System.out.println("     		" + getWheelHuman(wheelMinusOne[0]) + "" + getWheelHuman(wheelMinusOne[1]) + "" + getWheelHuman(wheelMinusOne[2]));
			System.out.println("-----		" + getWheelHuman(wheel[0]) + "" + getWheelHuman(wheel[1]) + "" + getWheelHuman(wheel[2]) + "-----");
			System.out.println("     		" + getWheelHuman(wheelPlusOne[0]) + "" + getWheelHuman(wheelPlusOne[1]) + "" + getWheelHuman(wheelPlusOne[2]));

	}

	public static void youWin(String alert, int prize) {
		System.out.println(alert);
		if (prize > 0) {
			System.out.println("You win " + prize + " quarters!");
		} else {
			System.out.println();
		}
	}
}

