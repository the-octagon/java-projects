// Andrew King
// COP2220
// Project 6
// 04/05/17

#include <ctype.h>
#include <errno.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define NUM_ITEMS 100
#define LINE_LENGTH 100
#define SUCCESS 0
#define FILE_READ_ERROR 1
#define LEN_NAME 75

typedef struct {
    char name[LEN_NAME];
    char city[LEN_NAME];
    char state[2+1];
    double distance;
} facility_t;

void clearStdIn( void );
void displayWelcomeMessage( void );
char getYesOrNo( void );
void getFileName( char *fileNamePtr, char defaultFileName[] );
void getSelection(int *selectionPtr);
int openAndReadFile( char defaultFileName[], facility_t *facilitiesPtr, int *countPtr );
void printFirstTen(facility_t *facilitiesPtr);
void printMenu(void);
int readInFromFile( char fileName[], facility_t *facilitiesPtr, int *countPtr );
void rollCredits( void );
void searchByCity(facility_t *facilitiesPtr, int count);
void searchByDistance(facility_t *facilitiesPtr, int count);

extern int errno;

int main( void ){
	int index = 0, selection = 0, count = 0;
	facility_t facilities[ NUM_ITEMS ] = {0};
	char defaultFileName[] = "AutismSpeaksInfo.txt", exit;

    displayWelcomeMessage();

    if( openAndReadFile( defaultFileName, facilities, &count) == SUCCESS ) {
        do {
            selection = 0;
            printMenu();
            getSelection(&selection);
            switch (selection) {
                case 1:
                    printFirstTen(facilities);
                    break;
                case 2:
                    searchByCity(facilities, count);
                    break;
                case 3:
                    searchByDistance(facilities, count);
                    break;
            }
        } while( selection != 4);
    }

    rollCredits();
	return SUCCESS;
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
	printf( "Welcome to Andrew King's Facility Information Resource.\n\n" );
}

// prompts user to enter a file name containing data, if no name is entered, uses default value
// accepts a pointer to a char array holding the name of the file to be opened and a char array holding the default file name to be opened
// returns nothing
void getFileName( char *fileNamePtr, char defaultFileName[] ) {
    char fileName[ FILENAME_MAX ];
    char illegalChars[] = "\"*+,/:;<=>?\[]|";
    int invalidCharPresent;
    int index, loopLimit = strlen( illegalChars );

    printf( "Enter a file name (default: %s): ", defaultFileName );
    fgets( fileName, FILENAME_MAX, stdin );
    fileName[ strlen( fileName ) - 1 ] = '\0';

    if( fileName[ 0 ] == '\0' ) {
        strcpy( fileName, defaultFileName );
    }

    do{
        invalidCharPresent = 0;
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

// accepts user input from menu and validates it
// accepts a pointer to an integer holding the user's selection
// returns nothing
void getSelection(int *selectionPtr) {
    scanf("%d", selectionPtr);
    clearStdIn();
    while (*selectionPtr < 1 || *selectionPtr > 4) {
        printf("Please choose an option from the menu above.\n");
        scanf("%d", selectionPtr);
        clearStdIn();
    }
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

// opens a file given a specified file name (or using program default) and populates an array of dataPoint_t structs
// accepts char array containing the default file name and a pointer to an array of dataPoint_t structs
// returns an integer denoting success
int openAndReadFile( char defaultFileName[], facility_t *facilitiesPtr, int *countPtr ) {
    char tryAgain;
    char fileName[ FILENAME_MAX ];

    do {
        getFileName( fileName, defaultFileName );

        if( readInFromFile( fileName, facilitiesPtr, countPtr ) == SUCCESS ) {
            return SUCCESS;
        } else {
            printf( "Try again? " );
            tryAgain = getYesOrNo();
        }
    } while( tryAgain == 'y' );
}

// prints first ten facilities
// takes a pointer to an array of facility_t
// returns nothing
void printFirstTen(facility_t *facilitiesPtr){
    int index;
    for (index = 0; index < 10; index++) {
        printf( "%s\n", (facilitiesPtr + index)->name );
        printf( "%s, %s\n", (facilitiesPtr + index)->city, (facilitiesPtr + index)->state );
        printf( "distance: %.2lf mi\n\n", (facilitiesPtr + index)->distance );
    }
}

// prints available options
// takes nothing
// returns nothing
void printMenu(void){
    printf( "1. Display first ten resources\n2. Query resources by city\n3. Query resources by distance\n4. Exit\n" );
}

// read in a given pipe delimited text file containing data strucure "name(string)|city(string)|state(string)|distance(double)" and place contents in an array of facility_t structs, prints error message if unsuccessful
// accepts a char array holding a file name to be opened and a pointer to a facility_t array
// returns an integer denoting SUCCESS or FILE_READ_ERROR
int readInFromFile( char fileName[], facility_t *facilitiesPtr, int *countPtr ) {
    FILE *filePtr;
    int index = 0;
    char line[ LINE_LENGTH ];
    int errnum;

    if( filePtr = fopen( fileName, "r" ) ) {
		while( index < NUM_ITEMS && fgets( line, LINE_LENGTH, filePtr ) ) {
			sscanf( line, "%[^|]|%[^|]|%[^|]|%lf", &( facilitiesPtr + index )->name, &( facilitiesPtr + index )->city, &( facilitiesPtr + index )->state, &( facilitiesPtr + index )->distance );
			index++;
		}
		*countPtr = index;
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
	printf( "\n\nResults were provided by Andrew King\n\nPress [Enter] to exit. " );
	getchar();
}

// prints prompt and searches for facilities by their city
// takes a pointer to an array of facilities and an integer denoting how many are in said array
// returns nothing
void searchByCity(facility_t *facilitiesPtr, int count) {
    char queryValue[LEN_NAME];
    int index, searchMax = 0;

    printf("What city would you like to search for?\n");
    scanf("%s", &queryValue);
    searchMax = strlen(queryValue);

    printf("\n\nFacilities in %s:\n", queryValue);
    for (index = 0; index < count; index++) {
        if (strncasecmp((facilitiesPtr + index)->city, queryValue, searchMax) == 0) {
            printf("%s\n", (facilitiesPtr + index)->name);
            printf("%s, %s\n", (facilitiesPtr + index)->city, (facilitiesPtr + index)->state);
            printf("distance: %.2lf mi\n\n", (facilitiesPtr + index)->distance);

        }
    }
}

// prints prompt and searches for facilities by distance
// takes a pointer to an array of facilities and an integer denoting how many are in said array
// returns nothing
void searchByDistance(facility_t *facilitiesPtr, int count) {
    double queryValue = 0;
    int index, searchMax = 0;

    printf("How many miles away would you like to search for?\n");
    scanf("%lf", &queryValue);
    clearStdIn();

    printf("\n\nFacilities within %.0lf mi:\n", queryValue);
    for (index = 0; index < count; index++) {
        if ((facilitiesPtr + index)->distance <= queryValue) {
            printf("%s\n", (facilitiesPtr + index)->name);
            printf("%s, %s\n", (facilitiesPtr + index)->city, (facilitiesPtr + index)->state);
            printf("distance: %.2lf mi\n\n", (facilitiesPtr + index)->distance);

        }
    }
}


/*
BEGIN TEST DATA

FIRST TEN FACILITIES:
A Golden Life Personal Care Home, LLC
Snellville, GA
distance: 286.38 mi

Active Day Center
Charleston, SC
distance: 194.13 mi

Active Day Center
Florence, SC
distance: 290.55 mi

Active Day Center
Georgetown, SC
distance: 249.76 mi

Active Day Center
Goose Creek, SC
distance: 206.53 mi

Active Day Center
Greenwood, SC
distance: 273.48 mi

Active Day Center
Myrtle Beach, SC
distance: 283.28 mi

Active Day Center
Ridgeland, SC
distance: 154.28 mi

Active Day Center
Sumter, SC
distance: 259.20 mi

Active Day Center
Winnsboro, SC
distance: 283.63 mi

IN "sumter":
Active Day Center
Sumter, SC
distance: 259.20 mi


WITHIN 150mi:
Angelwood Inc.
Jacksonville, FL
distance: 8.31 mi

LIFE
Savannah, GA
distance: 119.89 mi

LYF Inc.
Wesley Chapel, FL
distance: 148.77 mi

Marion County School Board
Ocala, FL
distance: 85.95 mi

Osceola ARC
Kissimmee, FL
distance: 137.66 mi

University Behavioral Center
Orlando, FL
distance: 120.08 mi



BEGIN SAMPLE RUN


Welcome to Andrew King's Facility Information Resource.

Enter a file name (default: AutismSpeaksInfo.txt): badFileName
badFileName - No such file or directory
Try again? (Y/N) y
Enter a file name (default: AutismSpeaksInfo.txt): illegal*File?Name
Invalid file name.
Please try again: validBadFileName
validBadFileName - No such file or directory
Try again? (Y/N) y
Enter a file name (default: AutismSpeaksInfo.txt):
1. Display first ten resources
2. Query resources by city
3. Query resources by distance
4. Exit
1
A Golden Life Personal Care Home, LLC
Snellville, GA
distance: 286.38 mi

Active Day Center
Charleston, SC
distance: 194.13 mi

Active Day Center
Florence, SC
distance: 290.55 mi

Active Day Center
Georgetown, SC
distance: 249.76 mi

Active Day Center
Goose Creek, SC
distance: 206.53 mi

Active Day Center
Greenwood, SC
distance: 273.48 mi

Active Day Center
Myrtle Beach, SC
distance: 283.28 mi

Active Day Center
Ridgeland, SC
distance: 154.28 mi

Active Day Center
Sumter, SC
distance: 259.20 mi

Active Day Center
Winnsboro, SC
distance: 283.63 mi

1. Display first ten resources
2. Query resources by city
3. Query resources by distance
4. Exit
2
What city would you like to search for?
sumter


Facilities in sumter:
Active Day Center
Sumter, SC
distance: 259.20 mi

1. Display first ten resources
2. Query resources by city
3. Query resources by distance
4. Exit
3
How many miles away would you like to search for?
150


Facilities within 150 mi:
Angelwood Inc.
Jacksonville, FL
distance: 8.31 mi

LIFE
Savannah, GA
distance: 119.89 mi

LYF Inc.
Wesley Chapel, FL
distance: 148.77 mi

Marion County School Board
Ocala, FL
distance: 85.95 mi

Osceola ARC
Kissimmee, FL
distance: 137.66 mi

University Behavioral Center
Orlando, FL
distance: 120.08 mi

1. Display first ten resources
2. Query resources by city
3. Query resources by distance
4. Exit
4


Results were provided by Andrew King

Press [Enter] to exit.


*/
