"""  Number Guessing Game
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
"""

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
