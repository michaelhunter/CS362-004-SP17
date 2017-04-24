#include "dominion.h"
#include <stdio.h>
#include <assert.h>
#include <string.h>


int main(){

	//initialize the gamestate

	int seed = 1000;
	int i;
	int numPlayers = 2;
	int p = 0; //player number
	int handCount = 5; //number of cards
	

	int k[10] = {adventurer, council_room, feast, gardens, mine, remodel,
						smithy, village, baron, great_hall};
	struct gameState G;
	initializeGame(numPlayers, k, seed, &G);



	//call the function




	//assert the results


	return 0;
}
