package rackSpace;

import java.io.*;
import java.util.*;

public class RackSpace {
	//This number can be changed whenever needed.
	public static int number = 10;
	
	String fileName;
	RackSpace(String name){
		fileName = name;
	}
	
	/* The following readFile function takes a blob of text as a parameter
	 * and tokenizes this blob into words. Words are delimited by any 
	 * character other than a-z, A-Z, or 0-9.*/
	
	/* At the same time, this function track all unique words encountered 
	 * and the number of times each was encountered in a hash map. Words 
	 * are matched in a case-insensitive manner. */
	public HashMap<String, Integer> readFile(String fileName){
		File file = new File(fileName);
		Scanner inputFile = null;
		HashMap<String, Integer> record = new HashMap<>();
		try {
			inputFile = new Scanner(file, "UTF-8");
			inputFile.useDelimiter("[^A-Za-z0-9]");
			
			while (inputFile.hasNext()){
				String current = inputFile.next().toLowerCase();
				if (current == null || current.length()==0) continue;
				if (!record.containsKey(current)){
					record.put(current, 1);
				}
				else if (record.containsKey(current)){
					int count = record.get(current);
					record.put(current, count+1);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			inputFile.close();
		}
		return record;
	}
	
	
	/* This function takes the hash map as input, go through the hash map
	 * and returns the words which have the highest 10 counts.
	 * */
	public String[] findTopTen(HashMap<String, Integer> record){
		if (record.size() == 0) return null;
		
		int size = record.size()<=number ? record.size():number;
		
		//the following two arrays are used to store the most popular words
		//currently.
		int[] count = new int[size];
		String[] word = new String[size];
		
		//minIndex is the index of word which has the smallest counts in arrays.
		int minIndex = 0;
		for (Map.Entry<String, Integer> entry : record.entrySet()){
			if (entry.getValue() == count[minIndex] && word[minIndex].compareTo(entry.getKey())<0
					||entry.getValue()> count[minIndex] ){
				
				count[minIndex] = entry.getValue();
				word[minIndex] = entry.getKey();
				int min = count[minIndex];
				
				//update the minIndex.
				for (int i=0; i<size; i++){
					if (count[i]<min) {
						minIndex = i;
						min = count[i];
					}
				}
			}			
		}
		return word;		
	}
	
	public void print(String[] res, HashMap<String, Integer> record){
		for (String str : res) System.out.println("The word \"" + str + "\" appears " + record.get(str) + " times.");
	}
	
	/* Find the top ten popular words from given text
	 * Command format: RackSpace [fileName]
	 * where the fileName is the name of a file or directory. 
	 */
	public static void main(String[] args) throws IOException {
		if (args == null || args.length == 0) return;
		
		int len = args.length;
		//read each file in the input arguments.
		for (int i=0; i<len; i++){
			String fileName = args[i];
			
			//If the input name is not a full name, add ".txt" at the end
			if (!fileName.endsWith(".txt")) fileName = fileName + ".txt";
			System.out.println("Counting the top " + number + " words in " + fileName + "...");
			
			//create an object which takes the fileName as argument
			RackSpace text = new RackSpace(fileName);
			
			//Words which appeared in the file are stored in a map, together with its count.
			HashMap<String,Integer> record = text.readFile(fileName);
			
			//the top ten popular words are stored in a String array.
			String[] result = text.findTopTen(record);
			
			//print the top ten words to standard output.
			text.print(result, record);
			System.out.println("---------------------------------------\n");
		}
		
	}
}
