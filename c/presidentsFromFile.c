#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>

#define LEN_LINE 35
#define NUM_LINES 50
#define LEN_NAME 25

typedef struct {
    char firstName[LEN_NAME];
    char lastName[LEN_NAME];
    int birthYear;
    int birthMonth;
    int birthDay;
} president_t;

char *getMonth ( int monthNumber );
int getSelection (void);
void printAll ( president_t *presidents, int count );
void searchByMonth ( president_t *presidents, int count );
void searchByMonthName ( president_t *presidents, int count, char searchMonth[] );
void searchByMonthNumber ( president_t *presidents, int count, int searchMonth );
void searchByName ( president_t *presidents, int count );
void searchByYear ( president_t *presidents, int count );

//as global variable, serves two functions: array to validate against and array to get name of month by index
char validMonths[12][LEN_NAME] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

int main (void) {
    president_t presidents[NUM_LINES] = {};
    int index = 0, count, choice = 0;
	FILE *filePtr;
	char fileName[] = "Presidents3.txt", line[LEN_LINE], searchName[LEN_NAME];
	char *startPtr;
	char *endPtr;

	if (filePtr = fopen(fileName, "r")) {
		while (index < NUM_LINES && fgets(line, LEN_LINE, filePtr)) {

            //to scan using RegEx
			//sscanf (line, "%[^|]|%[^|]|%i|%i|%i", &presidents[index].firstName, &presidents[index].lastName, &presidents[index].birthYear, &presidents[index].birthMonth, &presidents[index].birthDay);

			//to scan using string functions
			//first name
			startPtr = line;
			endPtr = strchr(line, '|');
			strncpy(presidents[index].firstName, startPtr, endPtr - startPtr);
			startPtr = endPtr + 1;

			//last name
			endPtr = strchr(startPtr, '|');
			strncpy(presidents[index].lastName, startPtr, endPtr - startPtr);
			startPtr = endPtr + 1;

			//everything else
			sscanf(startPtr, "%i|%i|%i", &presidents[index].birthYear, &presidents[index].birthMonth, &presidents[index].birthDay);

			index++;
		}
		count = index;
		fclose (filePtr);

		while (choice != 5) {
            choice = getSelection();
            switch(choice) {
            case 1:
                printAll(&presidents[0], count);
                break;
            case 2:
                searchByMonth(&presidents[0], count);
                break;
            case 3:
                searchByName(&presidents[0], count);
                break;
            case 4:
                searchByYear(&presidents[0], count);
                break;
            case 5:
                break;
            default:
                printf("Please choose a valid option.\n\n");
            }
        }
	}
	else {
		printf("The file '%s' could not be found.", fileName);
	}
    getchar();
	return 0;
}

//takes an integer denoting a month
//returns a string containing the name of the month
char *getMonth( int monthNumber ){
    return validMonths[monthNumber - 1];
}

//takes no parameters
//prints an option menu
//returns the users choice
int getSelection (void) {
    int choice;
    printf("Choose an option:\n1. Display all\n2. Search by birth month\n3. Search by name\n4. Search by year\n5. Exit\n");
    scanf("%i", &choice);
    return choice;
}

//takes an array of president_t structs and how many it contains
//prints all data to stdout
//returns nothing
void printAll(president_t *presidentsPtr, int count) {
    int index = 0;
    for (index = 0; index < count; index++) {
        printf("%s %s\n", (presidentsPtr + index)->firstName, (presidentsPtr+ index].lastName);
        printf("Born: %s %i, %i\n\n", getMonth(presidents[index].birthMonth), presidents[index].birthDay, presidents[index].birthYear);
	}
}

//takes an array of president_t structs and how many it contains
//prints prompt asking for a month as an integer or a string
//calls on search function for proper data type
//returns nothing
void searchByMonth(president_t presidents[], int count) {
    char input[LEN_NAME];
    int searchNumber;
    printf("\n\nSearch by birth month\nEnter a number or month name: ");
    scanf("%s", &input);
    if (isalpha(input[0])) {
        searchByMonthName(presidents, count, input);
    } else if ((searchNumber = atoi(input)) != 0 && searchNumber > 0 && searchNumber < 13) {
        searchByMonthNumber(presidents, count, searchNumber);
    } else {
        printf("Something went wrong. Please try again.\n\n");

    }
}

//takes an array of president_t structs, how many it contains, and a search string
//tests if string is a match on first three letters and normalizes it
//iterates through array printing those that match the string
//returns nothing
void searchByMonthName(president_t presidents[], int count, char searchMonth[]) {

    int index, valid = 0;

    for ( index = 0; index < 12; index++) {
        if (tolower(searchMonth[0]) == tolower(validMonths[index][0]) && tolower(searchMonth[1]) == tolower(validMonths[index][1]) && tolower(searchMonth[2]) == tolower(validMonths[index][2])) {
            strcpy (searchMonth, validMonths[index]);
            valid = 1;
            break;
        }
    }
    if (valid == 1) {
        printf("Presidents born in month %s:\n", searchMonth);
        for (index = 0; index < count; index++) {
                if (strcmp(getMonth(presidents[index].birthMonth), searchMonth) == 0) {
                    printf("%s %s\n", presidents[index].firstName, presidents[index].lastName);
                    printf("Born: %s %i, %i\n\n", getMonth(presidents[index].birthMonth), presidents[index].birthDay, presidents[index].birthYear );
                }
        }
    } else {
        printf("Something went wrong. Please try again.\n\n");
    }
}

//takes an array of president_t structs, how many it contains, and a search integer
//tests if integer is a match
//iterates through array printing those that match the integer
//returns nothing
void searchByMonthNumber(president_t presidents[], int count, int searchMonth) {
    int index;
    printf("Presidents born in month %s:\n", getMonth(searchMonth));
	for (index = 0; index < count; index++) {
            if (presidents[index].birthMonth == searchMonth) {
                printf("%s %s\n", presidents[index].firstName, presidents[index].lastName);
                printf("Born: %s %i, %i\n\n", getMonth(presidents[index].birthMonth), presidents[index].birthDay, presidents[index].birthYear );
            }
	}
}

//takes an array of president_t structs, how many it contains
//prints prompt and accepts a search string
//normalizes the search string
//iterates through array printing those that are a partial match on first name
//iterates through array printing those that are a partial match on last name
//returns nothing
void searchByName (president_t presidents[], int count) {
    char searchName[LEN_NAME];
    int index;

    printf("\n\nSearch by name\nEnter a name: ");
	scanf("%s", &searchName);
	int searchStringLength = strlen(searchName);
	searchName[0] = toupper(searchName[0]);
	for(index = 1; index < searchStringLength; index++) {
        searchName[index] = tolower(searchName[index]);
	}
    printf("\n\nPresidents with names starting with '%s':\n", searchName);
	for (index = 0; index < count; index++) {
            if (strncmp(presidents[index].firstName, searchName, searchStringLength) == 0) {
                printf("%s %s\n", presidents[index].firstName, presidents[index].lastName);
                printf("Born: %s %i, %i\n\n", getMonth(presidents[index].birthMonth), presidents[index].birthDay, presidents[index].birthYear );
            }
	}
	for (index = 0; index < count; index++) {
            if (strncmp(presidents[index].lastName, searchName, searchStringLength) == 0) {
                printf("%s %s\n", presidents[index].firstName, presidents[index].lastName);
                printf("Born: %s %i, %i\n\n", getMonth(presidents[index].birthMonth), presidents[index].birthDay, presidents[index].birthYear );
            }
	}
}

void searchByYear ( president_t presidents[], int count) {
    char input[LEN_NAME], garbage;
    int startYear, endYear, index;


    printf("\n\nSearch by birth year\nEnter one year or two years separated by a space: ");
    while (((garbage = getchar()) != '\n') && garbage != EOF ) {


    }
    fgets(input, 10, stdin);
    startYear = atoi(input);

    if (strchr(input,' ') == NULL) {
        printf("Presidents born in %i:\n", startYear);
        for (index = 0; index < count; index++) {
                if (presidents[index].birthYear == startYear) {
                    printf("%s %s\n", presidents[index].firstName, presidents[index].lastName);
                    printf("Born: %s %i, %i\n\n", getMonth(presidents[index].birthMonth), presidents[index].birthDay, presidents[index].birthYear );
                }
        }
    } else {
        endYear = atoi(strchr(input, ' '));
        printf("Presidents born in between %i and %i:\n", startYear, endYear);
        for (index = 0; index < count; index++) {
                if (presidents[index].birthYear >= startYear && presidents[index].birthYear <= endYear) {
                    printf("%s %s\n", presidents[index].firstName, presidents[index].lastName);
                    printf("Born: %s %i, %i\n\n", getMonth(presidents[index].birthMonth), presidents[index].birthDay, presidents[index].birthYear );
                }
        }
    }



    /*
    printf("Presidents born in month %s:\n", getMonth(searchMonth));
	for (index = 0; index < count; index++) {
            if (presidents[index].birthMonth == searchMonth) {
                printf("%s %s\n", presidents[index].firstName, presidents[index].lastName);
                printf("Born: %s %i, %i\n\n", getMonth(presidents[index].birthMonth), presidents[index].birthDay, presidents[index].birthYear );
            }
	}*/

}
