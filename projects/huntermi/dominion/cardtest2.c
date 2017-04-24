#include "dominion.h"
#include <stdio.h>
#include <assert.h>
#include <string.h>


/* Tests playAdventurer
 * checks that 2 cards are drawn
 * checks that 2 treasure cards are drawn
 * checks that adventurer card is discarded
 *
*/



int main(){
	
	//Initialize gamestate
	int seed = 1000;
	int i;
	int numPlayers = 2;
	int p = 0; //player number
	int handCount = 5; // number of cards

	int hand[MAX_HAND]; //hand that will be tested

	int handPos = 0; //position of card before it is played
	hand[handPos] = adventurer;
	for(i = 1; i < handCount; i++){
		hand[i] = estate;
	}
	int numTreasures = 0;

	int k[10] = {adventurer, council_room, feast, gardens, mine, remodel,
						smithy, village, baron, great_hall};
	
	struct gameState G;
	initializeGame(numPlayers, k, seed, &G);

	G.handCount[p] = handCount;
	G.whoseTurn = p;
	memcpy(G.hand[p], hand, sizeof(int) * handCount);

	//call function
	playAdventurer(&G, handPos);


	//find number of treasures in the hand afterwards
	int newNumTreasures = 0;
	for(i = 0; i < G.handCount[p]; i++){
		if(G.hand[p][i] == copper || G.hand[p][i] == silver || G.hand[p][i] == gold){
			newNumTreasures++;
		}
	}


	//assert results
	//assert(G.handCount[p] == handCount + 1); //check that two cards have been drawn
	//assert(newNumTreasures == numTreasures + 2); //check that two treasure cards have been drawn
	//assert(G.hand[p][handPos] != adventurer); // check that played card is discarded
	
	if(G.handCount[p] != handCount + 1)
		printf("adventurer +2 cards test: FAIL\n");
	else
		printf("adventurer +2 cards test: SUCCESS\n");

	if(newNumTreasures != numTreasures + 2)
		printf("adventurer +2 treasure test: FAIL\n");
	else
		printf("adventurer +2 treasure test: SUCCESS\n");


	if(G.hand[p][handPos] == adventurer)
		printf("adventurer discard test: FAIL\n");
	else
		printf("adventurer discard test: SUCCESS\n");
	
	
	return 0;
}

