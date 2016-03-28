from random import randint

secretnumber = randint(0,100)
print("Guess a number between 0 and 100")
guess = int(raw_input())
upperlimit = 100
lowerlimit = 0

while (guess != secretnumber):
	if (guess < secretnumber):
		lowerlimit = guess		
		print("Too low. Try again.")
		print("Guess a number between %s and %s" % (lowerlimit, upperlimit))
		guess = int(raw_input())
		print("")

	else:
		upperlimit = guess		
		print("Too high. Try again.")
		print("Guess a number between %s and %s" % (lowerlimit, upperlimit))
		guess = int(raw_input())
		print("")


print("")
print("You guessed it! %s is correct!" % secretnumber)
