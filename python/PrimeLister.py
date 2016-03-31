""" Prime Number Lister
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
