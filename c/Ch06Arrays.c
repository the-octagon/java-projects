#include <stdio.h>
#define TEMPS 7

double getAverage (int temps[7]);
// Program:	Ch06Array
// Author:		AFK
// Course:		COP2220

int main (void)
{
	int i;
	int highs[TEMPS] = { 80, 72, 60, 72, 74, 76, 78 }, lows[TEMPS] = { 53, 63, 39, 43, 50, 53, 55 };
	printf( "The high and low temperatures are: \n\n" );
	for (i = 0; i < TEMPS; i++)
		printf( "Day %2i: %3i  and %3i.\n", i+1, highs[i], lows[i] );
	printf( "\nThe average high is %.2lf, and the average low is %.2lf.\n\n", getAverage (highs), getAverage (lows) );
	return 0;
}

double getAverage (int temps[7])
{
	int sum = 0;
	int i;
	double average;
	for (i = 0; i < TEMPS; i++)
		sum += temps[i];
	average = (double) sum / TEMPS;
	return average;
}

