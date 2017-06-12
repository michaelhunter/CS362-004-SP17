/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import junit.framework.TestCase;



/**
 * Performs Validation Test for url validations.
 *
 * @version $Revision: 1128446 $ $Date: 2011-05-27 13:29:27 -0700 (Fri, 27 May 2011) $
 */
public class UrlValidatorTest extends TestCase {

	private boolean printStatus = false;
	private boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.

	private ResultPair[] testScheme = {
			new ResultPair("http://", true),
			new ResultPair("https://", true),
			new ResultPair("htp://", false),
			new ResultPair("http//", false),
			new ResultPair("http:/", false),
			new ResultPair("", false)
	};

	private ResultPair[] testAuthority = {
			new ResultPair("www.google.com", true),
			new ResultPair("google.com", true),
			new ResultPair("ecampus.oregonstate.edu", true),
			new ResultPair("en.wikipedia.org", true),
			new ResultPair("www.asp.net", true),
			new ResultPair("www.amazon.jp", true),
			new ResultPair("www.sellercentral-japan.amazon.jp", true),
			new ResultPair("www.amazon.co.jp", true),
			new ResultPair("www.nic.ru/en", true),
			new ResultPair("www.google.se", true),
			new ResultPair("www.hangouts.google.com", true),
			new ResultPair("ww.amazon.jp", false),
			new ResultPair("", false),
			new ResultPair("www.amazon.1234", false)
	};

	private ResultPair[] testPort = {
			new ResultPair(":8008", true),
			new ResultPair("", true),
			new ResultPair(":81", true),
			new ResultPair(":81a", false),
			new ResultPair(":-8008", false),
			new ResultPair("/en", false)

	};

	private ResultPair[] testPath = {
			new ResultPair("/path", true),
			new ResultPair("/path/data", true),
			new ResultPair("/path/data/more", true),
			new ResultPair("/path/", false),
			new ResultPair("//path", false),
			new ResultPair("", true)	   
	};

	private ResultPair[] testQuery = {
			new ResultPair("?q=test", true),
			new ResultPair("?q=test&r=test2", true),
			new ResultPair("?q=test;r=test2", true),
			new ResultPair("?q=test&r=test2;s=test3", true),
			new ResultPair("?q=test;r=test2&s=test3", true),
			new ResultPair("?q=test&r=test2&s=test3", true),
			new ResultPair("?q=test;r=test2;s=test3", true),
			new ResultPair("??=test", false),
			new ResultPair("", true)
	};

	private ResultPair[] testFragment = {
			new ResultPair("#test", true),
			new ResultPair("#test/path/test", false),
			new ResultPair("#test#", false),
			new ResultPair("#test?q=test", true),
			new ResultPair("", true)
	};

	public void testYourFirstPartition()
	{
		for (int i = 0; i < this.testScheme.length; i++)
		{
			String url = "";
			url = url + this.testScheme[i].item;
			url = url + "ecampus.oregonstate.edu";
			runTest(url, this.testScheme[i].valid);   
		}
	}

	public void testYourSecondPartition()
	{
		for (int i = 0; i < this.testAuthority.length; i++)
		{
			String url = "http://";
			url = url + this.testAuthority[i].item;
			runTest(url, this.testAuthority[i].valid);   
		}	   
	}

	public void testYourThirdPartition()
	{
		for (int i = 0; i < this.testPort.length; i++)
		{
			String url = "http://ecampus.oregonstate.edu";
			url = url + this.testPort[i].item;
			runTest(url, this.testPort[i].valid);   
		}
	}

	public void testYourFourthParition()
	{
		for (int i = 0; i < this.testPath.length; i++)
		{
			String url = "http://ecampus.oregonstate.edu";
			url = url + this.testPath[i].item;
			runTest(url, this.testPath[i].valid);   
		}
	}

	public void testYourFifthPartition()
	{
		for (int i = 0; i < this.testQuery.length; i++)
		{
			String url = "http://ecampus.oregonstate.edu/";
			url = url + this.testQuery[i].item;
			runTest(url, this.testQuery[i].valid);   
		}
	}

	public void testYourSixthPartition()
	{
		for (int i = 0; i < this.testFragment.length; i++)
		{
			String url = "http://ecampus.oregonstate.edu/";
			url = url + this.testFragment[i].item;
			runTest(url, this.testFragment[i].valid);   
		}
	}

	public void runTest(String url, boolean expect) {
		UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);

		if(urlVal.isValid(url) != expect){
			printErrors(url, expect, urlVal.isValid(url));
		}

		//assertEquals(urlVal.isValid(url), expect);
	}

	public void printErrors(String url, boolean expect, boolean actual){
		String message = url + " Actual: " + actual + " Expected: " + expect;
		System.out.println(message);
		System.out.println("");
	}

}




/*Tests all combinations of the URL parts in testScheme, testAuthority
 testPort, testPath, testQuery, and testFragment.  Counts the number
 of URLs tested and the number of failed URLs (result != expect).
 Writes the list of failed URLs to a file ./failedurls.txt
 */
public void testIsValid()
{

	int numURLs = 0;
	int failedURLs = 0;

	try{
		//initialize file writing
		FileWriter writer = new FileWriter("./failedurls.txt", false);
		writer.write("URLs such that expected != return\r\n");
		writer.close();
	}catch (IOException except) {
		except.printStackTrace();
	}

	for(int a = 0; a < testScheme.length; a++){
		for(int b = 0; b < testAuthority.length; b++){
			for(int c = 0; c < testPort.length; c++){
				for(int d = 0; d < testPath.length; d++){
					for(int e = 0 ; e < testQuery.length; e++){
						for(int f = 0; f < testFragment.length; f++){

							boolean expect = true;
							String url = "";

							url += testScheme[a].item;
							expect &= testScheme[a].valid;

							url += testAuthority[b].item;
							expect &= testAuthority[b].valid;

							url += testPort[c].item;
							expect &= testPort[c].valid;

							url += testPath[d].item;
							expect &= testPath[d].valid;

							url += testQuery[e].item;
							expect &= testQuery[e].valid;

							url += testFragment[f].item;
							expect &= testFragment[f].valid;

							numURLs++;

							UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);


							if(printStatus){
								if(urlVal.isValid(url) == expect)
									System.out.print("PASS: ");
								else
									System.out.print("FAIL: ");

								if(urlVal.isValid(url))
									System.out.print(".");
								else
									System.out.print("X");
								if(expect)
									System.out.print(".  ");
								else
									System.out.print("X  ");
								System.out.println(url);
							}


							if(urlVal.isValid(url) != expect){
								failedURLs++;
								try {
									FileWriter writer2 = new FileWriter("./failedurls.txt", true);
									writer2.write(url);
									writer2.write("\r\n");
									writer2.close();
								} catch (IOException except) {
									except.printStackTrace();
								}
							}



						}
					}
				}
			}
		}
	}
	System.out.print("total URLs tested:");
	System.out.println(numURLs);
	System.out.print("failed URLs:");
	System.out.println(failedURLs);

	//end of testIsValid
}


/* Tests queries by testing valid URLs (url=scheme+authority+port+path)
 * 	and then adding a query (url=scheme+authority+port+path+query).
 * 	Counts the number of valid URLs (numURLs) and the number of valid
 * 	URLs that become invalid when a query is added (failedURLs)
 */
public void testQueries()
{

	int numURLs = 0;
	int failedURLs = 0;


	try{
		//initialize file writing
		FileWriter writer = new FileWriter("./failedurls.txt", false);
		writer.write("URLs such that expected != return\r\n");
		writer.close();
	}catch (IOException except) {
		except.printStackTrace();
	}

	for(int a = 0; a < testScheme.length; a++){
		for(int b = 0; b < testAuthority.length; b++){
			for(int c = 0; c < testPort.length; c++){
				for(int d = 0; d < testPath.length; d++){
					for(int e = 0 ; e < testQuery.length; e++){

						boolean expect = true;
						String url = "";

						UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);

						boolean result1 = false;

						if(testScheme[a].valid &&
								testAuthority[b].valid &&
								testPort[c].valid &&
								testPath[d].valid){

							url += testScheme[a].item;
							expect &= testScheme[a].valid;

							url += testAuthority[b].item;
							expect &= testAuthority[b].valid;

							url += testPort[c].item;
							expect &= testPort[c].valid;

							url += testPath[d].item;
							expect &= testPath[d].valid;

							result1 = urlVal.isValid(url);
						}


						if(result1 && testQuery[e].valid && testQuery[e].item != ""){
							//prints failed urls
							System.out.print("valid:   ");
							System.out.println(url);
							url += testQuery[e].item;
							expect &= testQuery[e].valid;
							numURLs++;
							boolean result2 = urlVal.isValid(url);
							if(result2 == false){
								failedURLs++;
								System.out.print("invalid: ");
								System.out.println(url);
							}

						}

					}
				}
			}
		}
	}
	System.out.print("total URLs tested:");
	System.out.println(numURLs);
	System.out.print("failed URLs:");
	System.out.println(failedURLs);

	//end of testQueries
}
