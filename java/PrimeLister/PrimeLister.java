/*  Prime Number Lister
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

import java.io.IOException;

public class PrimeLister {

	public static void main(String[] args) throws IOException{
		int number = 0;
		int pageLineCount = 0;

		System.out.println("The following numbers are prime numbers:");
		while (true) {			
			if (pageLineCount == 20) {
				System.out.println("Press Enter to continue...");
    			System.in.read();
				pageLineCount = 0;
			}			
			if (isNumberPrime(number)) {			
				System.out.println(number);
				pageLineCount++;
			}
			number++;		
		}	
	}
	
	public static boolean isNumberPrime(int number) {
		boolean primeOrNot = true;
		int divisor = number - 1;

		while (divisor > 1 && primeOrNot == true) {
//			System.out.println(number + " % " + divisor + " = " + number % divisor);
			if (number % divisor == 0) {
				primeOrNot = false;
			}
		divisor--;		
		}

	return primeOrNot;
	} 
}
