#include "dominion.h"
#include <stdio.h>
#include <assert.h>
#include <string.h>

/*  Tests playSmithy()
 *  checks that card is discarded
 *  checks that three cards are drawn
 *
 *
*/

int main(){
	//initialize the gamestate
	
	int seed = 1000;
	int i;
	int numPlayers = 2;
	int p = 0; //player number
	int handCount = 5; //number of cards
	
	int hand[MAX_HAND];

	int handPos = 0; //position of card before it is played
	hand[0] = smithy;
	for(i = 1; i < handCount; i++){
		hand[i] = copper;
	}

	int k[10] = {adventurer, council_room, feast, gardens, mine, remodel,
						smithy, village, baron, great_hall};
	struct gameState G;
	initializeGame(numPlayers, k, seed, &G);

	G.handCount[p] = handCount;
	G.whoseTurn = p;
	memcpy(G.hand[p], hand, sizeof(int) * handCount);


	playSmithy(&G, handPos);

	//assert results
	//assert(G.hand[p][handPos] != smithy); // check card has been discarded
	//assert(G.handCount[p] == handCount + 2);	//check that 3 cards have been drawn
	//printf("Smithy test: SUCCESS\n");
	
	if(G.hand[p][handPos] == smithy)
		printf("smithy discard: FAIL\n");
	else
		printf("smithy discard: SUCCESS\n");

	if(G.handCount[p] != handCount + 2)
		printf("smithy +3 cards: FAIL\n");
	else
		printf("smithy +3 cards: SUCCESS\n");

	return 0;
}

