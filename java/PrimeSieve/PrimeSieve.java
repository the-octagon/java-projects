import java.util.Date;

class PrimeSieve {
	public static void main(String[] args) {
		boolean primes[];
		int max = 0;
		if (args.length == 0){
			System.out.println("\nWelcome to a demonstration of the Sieve of Eratosthenes\n\nThis program will print all prime numbers up to a given value.\nFor usage and license information, use option '-h'\n");
		} else	if (args[0].equals("-h") || args[0].equals("--help")) {
				System.out.println("Usage: java PrimeSieve [OPTION | N]\nList all prime numbers between 2 and N, inclusive.\nOptions:\n-h, --help\t\t\tprint this help page\n-v, --version\t\t\toutput version information and exit\n\n\nThis program is free software: you can redistribute it and/or modify it under\nthe terms of the GNU General Public License as published by the Free Software\nFoundation, either version 3 of the License, or (at your option) any later version.\nThis program is distributed in the hope that it will be useful, but WITHOUT\nANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS\nFOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.\n\nYou should have received a copy of the GNU General Public License along with\nthis program.  If not, see <http://www.gnu.org/licenses/>.\nFor more information, visit <http://www.githubb.com/the-octagon>");
		} else	if (args[0].equals("-v") || args[0].equals("--version")) {
			System.out.println("PrimeSieve v1.0 by Andrew King <http://www.github.com/the-octagon>");
		} else if(args[0].substring(0,1).equals("-")) {
			System.out.println(args[0] + " is not a valid option. Please use -h for more information");
		} else {
			try {
				max = Integer.parseInt(args[0]);
			} catch (Exception e){
				System.out.println(e);
				System.out.println(args[0] + " is not a valid input. Please choose an integer greater than 2");
			}
			primes = calcPrimes(max);
			printPrimes(primes);
		}
	}
	
	static boolean[] calcPrimes(int max) {
		long startTime = System.currentTimeMillis();

		max = ++max;
		boolean primes[] = new boolean[max];
		int index;
		
		for (int i = 2; i < max; i++) {
			primes[i] = true;
		}
		
		for (int j = 2; j < Math.sqrt(max); j++) {
			if (primes[j]) {
				index = 0;
				for (int k = j*j; k < max; k = (j*j) + (index * j)) {
					primes[k] = false;
					index++;
				}
			}
		}
		long totalTime = System.currentTimeMillis() - startTime;
		System.out.println("Calculations took " + totalTime + "ms");
		return primes;
	}
	
 	static void printPrimes(boolean primes[]) {
		int length = primes.length;
		boolean first = true;
		
		for (int index = 2; index < length; index++) {
			if (!first && primes[index]) {
				System.out.print(", " + index);
			} else if (primes[index]) {
				System.out.print(index);
				first = false;
			}
		}
		System.out.println();
	}
}
