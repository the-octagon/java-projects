#include <ctype.h>
#include <errno.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define NUM_YEARS 7
#define LINE_LENGTH 25
#define MIN_MODE 0
#define MAX_MODE 1
#define SUCCESS 0
#define FILE_READ_ERROR 1

typedef struct {
    int year;
    int sampleSize;
    double percentage;
} dataPoint_t;

int calculateExpressors( double percentage, int sampleSize );
int calculateHighestOrLowest( dataPoint_t *dataSetPtr, int mode );
void displayWelcomeMessage( void );
char getYesOrNo( void );
void getFileName( char *fileNamePtr, char defaultFileName[] );
int getSelection( dataPoint_t *dataSetPtr );
int isYearValid( dataPoint_t *dataSetPtr, int year );
int openAndReadFile( char defaultFileName[], dataPoint_t *dataSetPtr );
void printSummary( dataPoint_t *dataSetPtr, int selection );
int readInFromFile( char fileName[], dataPoint_t *dataSetPtr );
void rollCredits( void );

extern int errno;

int main( void ){
	int index = 0, selection;
	dataPoint_t dataSet[ NUM_YEARS ] = {0};
	char defaultFileName[] = "AutismRates.txt", exit;

    displayWelcomeMessage();

    if( openAndReadFile( defaultFileName, &dataSet[ 0 ] ) == SUCCESS ) {
        do {
            selection = getSelection( &dataSet[ 0 ] );
            printSummary ( &dataSet[ 0 ], selection );
            printf( "Do you want to exit? " );
            exit = getYesOrNo();
        } while( exit != 'y' );
    }

    rollCredits();
    getchar();
	return SUCCESS;
}

// take a percentage and sample size and return the number of expressors
// takes a double holding the percentage, and an integer holding the sample size
// returns an integer holding the sample size that have expressed the trait
int calculateExpressors( double percentage, int sampleSize ){
	return round( sampleSize * percentage / 100 );
}

// take a pointer to an array of dataPoint_t's and returns the index of the highest or lowest percentage depending on the mode, MIN_MODE for lowest, MAX_MODE for highest
// takes parameters of pointer to first dataPoint_t in an array holding percentage member and integer denoting either max or min mode
// returns the integer index of the highest or lowest percentage
int calculateHighestOrLowest( dataPoint_t *dataSetPtr, int mode ){
    int index, result = 0;

	if( mode == MIN_MODE ) {
		for( index = 1; index <= NUM_YEARS - 1; index = index + 1 ) {
			if(( dataSetPtr + result)->percentage > (dataSetPtr + index)->percentage ) {
				result = index;
			}
		}
	}
	else{
		for(index = 1; index <= NUM_YEARS - 1; index = index + 1){
			if ( ( dataSetPtr + result )->percentage < ( dataSetPtr + index )->percentage ){
				result = index;
			}
		}
	}

	return result;
}

// consumes the remaining data in stdin after input is accepted, preventing unwanted data from remaining to be inadverntaly accepted at next read-in
// accepts nothing
// returns nothing
void clearStdIn( void ){
    char garbage;
    while ( ( garbage = getchar() ) != '\n' && garbage != EOF ) {
        // nothing
    }
}

// print a welcome message to stdout at the start of the program
// takes no parameters
// returns nothing
void displayWelcomeMessage( void ){
	printf( "Welcome to Andrew King's Sampling Calculator.\n\n" );
}

// prompts user to enter a file name containing data, if no name is entered, uses default value
// accepts a pointer to a char array holding the name of the file to be opened and a char array holding the default file name to be opened
// returns nothing
void getFileName( char *fileNamePtr, char defaultFileName[] ) {
    char fileName[ FILENAME_MAX ];
    char illegalChars[] = "\"*+,/:;<=>?\[]|";
    int invalidCharPresent = 0;
    int index, loopLimit = strlen( illegalChars );

    printf( "Enter a file name (default: %s): ", defaultFileName );
    fgets( fileName, FILENAME_MAX, stdin );
    fileName[ strlen( fileName ) - 1 ] = '\0';

    if( fileName[ 0 ] == '\0' ) {
        strcpy( fileName, defaultFileName );
    }

    do{
        for( index = 0; index < loopLimit; index++ ) {
            if( strchr( fileName, illegalChars[ index ] ) != NULL ){
                invalidCharPresent = 1;
                break;
            }
        }
        if( invalidCharPresent == 1 ) {
            printf( "Invalid file name.\nPlease try again: " );
            fgets( fileName, FILENAME_MAX, stdin );
            fileName[ strlen( fileName ) - 1 ] = '\0';
        }
    } while( invalidCharPresent );

    strcpy( fileNamePtr, fileName );
}


// print a prompt indicating valid choices and asking the user to choose a year or 'a' indicating all years, validates choice, and returns the index of the chosen year or NUM_YEARS for all
// takes an pointer to an array of dataPoint_t's with member year as parameter
// dependent function: isYearValid()
// returns an integer indicating the users choice
int getSelection( dataPoint_t *dataSetPtr ) {
	int index, selection, year;
	char all, c, input[ 5 ];

	printf( "Information is available for:\n" );
	for( index = 0; index <= NUM_YEARS - 1; index = index + 1 ){
		printf( "%i ", ( dataSetPtr + index )->year );
	}
	printf( "\n\nWhat year do you want information for?\n" );
	printf( "Enter a year or 'a' for all: ");
	scanf( "%s", &input );
	clearStdIn();
	if( tolower( input[ 0 ] ) == 'a'){
		selection = NUM_YEARS;
	} else{
		year = atoi( input );
		selection = isYearValid( dataSetPtr, year );
	}
	while( selection == -1 ) {
		printf( "Choose a valid year from the list or 'a' for all: ");
		scanf( "%s" , &input );
		clearStdIn();
		if( tolower( input[ 0 ] ) == 'a' ) {
			selection = NUM_YEARS;
		} else {
			year = atoi( input );
			selection = isYearValid( dataSetPtr, year );
		}
	}

	return selection;
}

// accept yes or no, validating to only accept 'y', 'Y', 'n', or 'N', returning the choice
// takes no parameters
// returns a char holding the users choice as either 'y' or 'n'
char getYesOrNo( void ) {
	char yesOrNo;

    printf( "(Y/N) " );
    scanf( "%c", &yesOrNo );
    clearStdIn();
	yesOrNo = tolower( yesOrNo );
	while ( yesOrNo != 'y' && yesOrNo != 'n' ){
		printf( "Please enter a 'y' for yes or 'n' for no: " );
		scanf( "%c", &yesOrNo );
		clearStdIn();
		yesOrNo = tolower( yesOrNo );
	}

	return yesOrNo;
}

// takes valid years and a choice, returns the first index of the year if valid or -1 if not found
// takes a pointer to an array of DataPoint_t's containing integer member year and an integer year as parameters
// returns an integer holding the first index of the year if valid or -1 in not found
int isYearValid( dataPoint_t *dataSetPtr, int year ) {
	int index, position = -1;

	for( index = 0; index <= NUM_YEARS - 1; index = index + 1 ) {
		if ( ( dataSetPtr + index )->year == year ) {
			position = index;
			break;
		}
	}

	return position;
}

// opens a file given a specified file name (or using program default) and populates an array of dataPoint_t structs
// accepts char array containing the default file name and a pointer to an array of dataPoint_t structs
// returns an integer denoting success
int openAndReadFile( char defaultFileName[], dataPoint_t *dataSetPtr ) {
    char tryAgain;
    char fileName[ FILENAME_MAX ];

    do {
        getFileName( &fileName[ 0 ], defaultFileName );

        if( readInFromFile( &fileName[ 0 ], dataSetPtr ) == SUCCESS ) {
            return SUCCESS;
        } else {
            printf( "Try again? " );
            tryAgain = getYesOrNo();
        }
    } while( tryAgain == 'y' );
}

// prints a summary of the requested data in a table to stdout, either all years and the max and min or a single year
// opted for calculating expressors and high/low as needed to save calculation time when only one year is displayed
// takes an integer array of years, a double array of percentages, an integer array of sample sizes, and an integer indicating the users choice
// dependent function: calculateExpressors() calculateHighestOrLowest()
// returns nothing
void printSummary( dataPoint_t *dataSetPtr, int selection ){
    int index, highest, lowest;

	printf( "\n\nYear\t\tTimes Observed\t\tSample Size\t\tPercentage\n" );
	if( selection == NUM_YEARS){
		highest = calculateHighestOrLowest( dataSetPtr, MAX_MODE );
		lowest = calculateHighestOrLowest( dataSetPtr, MIN_MODE );
		for( index = 0; index <= NUM_YEARS - 1; index = index + 1 ){
			printf( "%i\t\t%5i\t\t\t%6i\t\t\t%5.2lf%%\n", ( dataSetPtr + index )->year, calculateExpressors( ( dataSetPtr +index )->percentage , ( dataSetPtr + index )->sampleSize), ( dataSetPtr + index )->sampleSize, ( dataSetPtr + index )->percentage );
		}
		printf( "\nHighest year: %i (%.2lf%%)\n", ( dataSetPtr + highest )->year, ( dataSetPtr + highest )->percentage );
		printf( "Lowest year: %i (%.2lf%%)\n\n", ( dataSetPtr + lowest )->year, ( dataSetPtr + lowest )->percentage );
	} else {
	    printf( "%i\t\t%5i\t\t\t%6i\t\t\t%5.2lf%%\n", ( dataSetPtr + selection )->year, calculateExpressors( ( dataSetPtr + selection )->percentage , ( dataSetPtr + selection )->sampleSize ), ( dataSetPtr + selection )->sampleSize, ( dataSetPtr + selection )->percentage );
	}
}

// read in a given space delimited text file containing data strucure "year(integer) percentage(float) sampleSize(integer)" and place contents in an array of dataPoint_t structs, prints error message if unsuccessful
// accepts a char array holding a file name to be opened and a pointer to a dataPoint_t array
// returns an integer denoting SUCCESS or FILE_READ_ERROR
int readInFromFile( char fileName[], dataPoint_t *dataSetPtr ) {
    FILE *filePtr;
    int index = 0;
    char line[ LINE_LENGTH ];
    int errnum;

    if( filePtr = fopen( fileName, "r" ) ) {
		while( index < NUM_YEARS && fgets( line, LINE_LENGTH, filePtr ) ) {
			sscanf( line, "%i %lf %i", &( dataSetPtr + index )->year, &( dataSetPtr + index )->percentage, &( dataSetPtr + index )->sampleSize );
			index++;
		}
		fclose( filePtr );

		return SUCCESS;
	}
	else {
        errnum = errno;
        fprintf( stderr, "%s - ", fileName );
        perror( "" );
		return FILE_READ_ERROR;
	}
}

// prints a message to stdout giving credit for the program
// takes no parameters
// returns nothing
void rollCredits (void) {
	printf( "\n\nResults were provided by Andrew King\n\n" );
}
