package rackSpace;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class RackSpaceTest {
	
	RackSpace test = null;
	
	//real books downloaded from the suggested website.
	static final String NAME = "TeddyBears.txt";
	static final String NAME2 = "TheMarchingMorons.txt";
	
	//file which does not exist.
	static final String NAME3 = "nullTest.txt";
	
	//file which has very small input.
	static final String NAME4 = "smallTest.txt";

	@After
	public void tearDown() throws Exception {
		test = null;
	}

	@Test
	//test whether readFile function can store the words in hash map
	public void testReadFile() {
		test = new RackSpace(NAME);
		HashMap<String, Integer> map = test.readFile(NAME);
		assertNotNull(map);
		for (Map.Entry<String, Integer> entry : map.entrySet()){
			assertNotNull(entry.getValue());
			assertNotNull(entry.getKey());
		}
	}
	
	@Test
	//test whether readFile function can store the words in hash map
	public void testReadFile2() {
		test = new RackSpace(NAME2);
		HashMap<String, Integer> map = test.readFile(NAME2);
		assertNotNull(map);
		for (Map.Entry<String, Integer> entry : map.entrySet()){
			assertNotNull(entry.getValue());
			assertNotNull(entry.getKey());
		}
	}	
	
	@Test
	//test whether testFindTopTen function can return a String array of length 10. 
	//The array should include the most common word "the".
	public void testFindTopTen(){
		test = new RackSpace(NAME);
		HashMap<String, Integer> map = test.readFile(NAME);
		String[] topTen = test.findTopTen(map);
		assertEquals(10, topTen.length);
		String temp = null;
		for(String str : topTen){
			if (str.equals("the")) {
				temp = str;
				break;
			}	
		}
		assertNotNull(temp);
	}
	
	@Test
	//test whether testFindTopTen function can return a String array of length 10. 
	//The array should include the most common word "the".
	public void testFindTopTen2(){
		test = new RackSpace(NAME2);
		HashMap<String, Integer> map = test.readFile(NAME2);
		String[] topTen = test.findTopTen(map);
		assertEquals(10, topTen.length);
		String temp = null;
		for(String str : topTen){
			if (str.equals("the")) {
				temp = str;
				break;
			}	
		}
		assertNotNull(temp);
	}
	
	@Test(expected = Exception.class)
	//test whether the program throws the exception when the input argument refers 
	//to a non-exist file
	public void testException(){
		test = new RackSpace(NAME3);
		test.readFile(NAME3);
	}
	
	@Test
	//test the length of the String array returned by findTopTen function when the 
	//input file contains less than 10 words.
	public void testSmallInput(){
		test = new RackSpace(NAME4);
		HashMap<String, Integer> map = test.readFile(NAME4);
		String[] topTen = test.findTopTen(map);
		assertEquals(5, topTen.length);
	}
	

}
