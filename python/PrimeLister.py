import sys
number = 0
pageLineCount = 0
exit = " "

print("The following numbers are prime numbers:")

def isNumberPrime(number):
	primeOrNot = True
	divisor = number - 1

	while (divisor > 1 and primeOrNot == True):
		if ((number%divisor) == 0):
			primeOrNot = False
		divisor = divisor - 1
	return primeOrNot

while (True):
	if (pageLineCount == 20):
		print("Press enter to continue or type \"exit\"")
		exit = raw_input()
		if (exit=="exit"):
			sys.exit()
		pageLineCount = 0
	if (isNumberPrime(number)):
		print(number)
		pageLineCount = pageLineCount + 1
	number = number + 1
