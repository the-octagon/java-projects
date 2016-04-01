/*  Denomination Counter
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
        github: the-octagon*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.RoundingMode;

public class HowMuchMoney {

public static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {
		//declare and init vars to hold input ammount and the count of each type of bill/coin (100, 50, 20, 10, 5, 1, 0.25, 0.10, 0.05, 0.01)
		double totalMoney = 0;
		int[] count = new int[10];
		
		//declare DecimalFormat object named df, set pattern to money
		NumberFormat df = NumberFormat.getCurrencyInstance(Locale.US);
		
		//prompt user for input
		System.out.println("How much money do you have?");
		System.out.print("$");
		totalMoney = keyboard.nextDouble();
		
		//validation loop for input so totalMoney > 0
		while (totalMoney < 0) {
			System.out.println("Please enter a value greater than 0.");
			System.out.print("$");
			totalMoney = keyboard.nextDouble();
		}
		System.out.println();

		System.out.println("For the value of " + df.format(totalMoney) + ", you need:");

		//calculate how many of each bill/coin
		count = runCalcs(totalMoney);
	}
	
	public static int[] runCalcs(double workingTotal) {
		//create local count array to pass back to main
		int[] count = new int[10];
		//value for each denomination
		double[] value = {100,50,20,10,5,1,0.25,0.10,0.05,0.01};
		//init 2d array to hold names for each denomination, singular and plural
		String[][] denom = {{"hundred","hundreds"}, {"fifty","fifties"}, {"twenty","twenties"},	{"ten","tens"},	{"five","fives"}, {"one","ones"}, {"quarter","quarters"},{"dime","dimes"}, {"nickle","nickles"}, {"penny","pennies"}};
		DecimalFormat rounder = new DecimalFormat("#.####");
		rounder.setRoundingMode(RoundingMode.CEILING);		
		//run once for each denomination
		for (int index = 0;index < 10;index++) {
			//init each count var
			count[index] = 0;

			//as long as workingTotal is greater than each denomination, run again
			while (Double.parseDouble(rounder.format(workingTotal)) >= Double.parseDouble(rounder.format(value[index]))) {
				//subtract value of current denomination and increase counter value for coresponding denomination
				workingTotal = workingTotal - value[index];
				count[index]++;
			}
			if (count[index] == 1) {
				System.out.println(count[index] + " " + denom[index][0]);
			} else {
				System.out.println(count[index] + " " + denom[index][1]);
			}
		}
		return count;
	}
}
