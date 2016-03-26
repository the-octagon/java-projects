from random import randint

secretnumber = randint(0,100)
print("Guess a number between 0 and 100")
guess = raw_input()
upperlimit = 100
lowerlimit = 0
print(secretnumber)
while (guess != secretnumber):
	if (guess < secretnumber):
		lowerlimit = guess		
		print("Too low. Try again.")
		print("Guess a number between %s and %s" % (lowerlimit, upperlimit))
		guess = raw_input()
		print("%s and %s" % guess, secretnumber)
	else:
		upperlimit = guess		
		print("Too high. Try again.")
		print("Guess a number between %s and %s" % (lowerlimit, upperlimit))
		guess = raw_input()
		print("%s and %s" % (guess, secretnumber))

print("You guessed it! %s is correct!" % secretnumber)
