
#include "dominion.h"
#include <stdio.h>
#include <assert.h>
#include <string.h>


/* Tests playMine
 *
 *
 *
 *
*/



int main(){
	
	//Initialize gamestate
	int seed = 1000;
	int i;
	int numPlayers = 2;
	int p = 0; //player number
	int handCount = 5; //number of cards
	
	int hand[MAX_HAND] = {mine, copper, copper, silver, gold};
	int handPos = 0; //position of card before it is played
	int numCopper = 2;
	int numSilver = 1;
	int numGold = 1;

	int choice1 = 1; //position of card to be trashed (copper)
	int choice2 = 5; //supply pile of card to be gained (silver)

	int k[10] = {adventurer, council_room, feast, gardens, mine, remodel,
						smithy, village, baron, great_hall};
	struct gameState G;
	initializeGame(numPlayers, k, seed, &G);

	G.handCount[p] = handCount;
	G.whoseTurn = p;
	memcpy(G.hand[p], hand, sizeof(int) * handCount);


	//call function
	playMine(&G, handPos, choice1, choice2); //trash a copper, gain a silver

	int newNumCopper = 0;
	int newNumSilver = 0;
	int newNumGold = 0;
	for(i = 0; i < G.handCount[p]; i++){
		if(G.hand[p][i] == copper)
			newNumCopper++;
		if(G.hand[p][i] == silver)
			newNumSilver++;
		if(G.hand[p][i] == gold)
			newNumGold++;
	}
	//assert results
	//assert(newNumCopper = numCopper - 1); //check one copper has been trashed
	//assert(newNumSilver = numSilver + 1); //check one silver has been gained
	//assert(G.hand[p][handPos] != mine); //check played card was discarded

	if(newNumCopper != numCopper - 1)
		printf("mine -1 copper test: FAIL\n");
	else
		printf("mine -1 copper test: SUCCESS\n");

	if(newNumSilver != numSilver + 1)
		printf("mine +1 silver test: FAIL\n");
	else
		printf("mine +1 silver test: SUCCESS\n");

	if(G.hand[p][handPos] == mine)
		printf("mine discard test: FAIL\n");
	else
		printf("mine discard test: SUCCESS\n");

	return 0;
}

