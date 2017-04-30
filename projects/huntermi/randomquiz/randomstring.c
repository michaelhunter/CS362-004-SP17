/* inputChar()
 * 
 * For this function, I just used rand() % 256 to
 * get a random char value between 0 and 255
 * and returned that value.
 *
 * inputString()
 *
 * This function uses an array of the letters
 * r, e, s, and t.  I allocate memory for a
 * char array of 5 characters and use rand()
 * to fill each element of the array with one
 * of the four letters.  That string is returned.
 *
 * I added a line in testme() to free s after it
 * used so that there weren't memory leaks.'
 *
 * Initially, I tried to just fill a 5 character
 * array with random chars like I did with the
 * previous part, but it was going to take forever
 * to get to the error.
 *
 * Then I tried using random lowercase letters only,
 * but that still wasn't producing the error because 
 * it was so unlikely to produce "reset".
 *
 *
*/
