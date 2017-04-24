
#include "dominion.h"
#include <stdio.h>
#include <assert.h>
#include <string.h>


/* Tests playVillage
 * checks +2 actions
 * checks +1 card
 * checks card is dicarded
 *
*/



int main(){
	
	//Initialize gamestate
	
	int seed = 1000;
	int i;
	int numPlayers = 2;
	int p = 0; // player number
	int handCount = 5; //number of cards in hand

	int hand[MAX_HAND];

	int handPos = 0; //position card is played from
	hand[0] = village;
	for(i = 0; i < handCount; i++){
		hand[i] = copper;
	}

	int k[10] = {adventurer, council_room, feast, gardens, mine, remodel,
						smithy, village, baron, great_hall};
	struct gameState G;
	initializeGame(numPlayers, k, seed, &G);

	G.handCount[p] = handCount;
	G.whoseTurn = p;
	memcpy(G.hand[p], hand, sizeof(int) * handCount);

	int numActions = G.numActions;

	//call function
	playVillage(&G, handPos);


	//assert results
	//assert(G.numActions == numActions + 2); //check +2 actions
	//assert(G.handCount[p] == handCount); //check +1 card 
	//assert(G.hand[p][handPos] != village); //check that card was discarded
	//printf("Village test: SUCCESS\n");

	if(G.numActions != numActions + 2)
		printf("village +2 actions: FAIL\n");
	else
		printf("village +2 actions: SUCCESS\n");

	if(G.handCount[p] != handCount)
		printf("village +1 card: FAIL\n");
	else
		printf("village +1 card: SUCCESS\n");

	if(G.hand[p][handPos] == village)
		printf("village discard: FAIL\n");
	else
		printf("village discard: SUCCESS\n");


	return 0;
}

