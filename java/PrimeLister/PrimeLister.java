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
