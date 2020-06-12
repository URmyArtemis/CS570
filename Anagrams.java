//Name:  CWID:
package anagrams;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Anagrams {
	//constant primes should be initialized to an array consisting of the first 26 prime numbers:
	final Integer[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
	Map<Character,Integer> letterTable; //associate each letter in the alphabet with a prime number. 
	Map<Long,ArrayList<String>> anagramTable = new HashMap<Long,ArrayList<String>>();
	
	public Anagrams() {
		buildLetterTable();
	}
/**
 * The main method is processFile which receives the name of a text file containing words, 
 * one per line, and builds the hash table anagramTable. For that it uses the addWord method. 
 * This is a provided method.
 * @param s
 * @throws IOException
 */
	private void processFile(String s) throws IOException { 
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream)); 
		String strLine;
		while ((strLine = br.readLine()) != null) { 
			this.addWord(strLine);
		} br.close();
	}
/**
 * build the hash table letterTable.
 */
	public void buildLetterTable() {
		letterTable = new HashMap<Character, Integer>();
		char[] letters = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		for (int i = 0; i < letters.length; i++) {
			letterTable.put(letters[i], primes[i]);
		}
	}
	/**
	 * This method, given a string s, should compute its hash code. 
	 * @param s
	 * @return the hash code of String s
	 */
	public long myHashCode(String s) {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		int wordLength = s.length();
		long myHash = 1;
		for (int i = 0; i < wordLength; i++) {
			myHash *= letterTable.get(s.charAt(i));
		}
		return myHash;		
	}
	/**
	 * This method should compute the hash code of the string s passed as argument, 
	 * and should add this word to the hash table anagramTable.
	 * @param s
	 */
	public void addWord(String s) {
		long hc = myHashCode(s);
		if(anagramTable.get(hc) != null) {
			anagramTable.get(hc).add(s);
		}else{
			ArrayList<String> tempList = new ArrayList<String>();
			tempList.add(s);
			anagramTable.put(hc, tempList);
		}
		
	}
	/**
	 * return the entries in the anagramTable that have the largest number of anagrams.
	 * @return
	 */
	public ArrayList<Map.Entry<Long, ArrayList<String>>> getMaxEntries() {
		ArrayList<Map.Entry<Long, ArrayList<String>>> maxNum = new ArrayList<Map.Entry<Long, ArrayList<String>>>();
		int largest = 0;
		for (Map.Entry<Long,ArrayList<String>> entry : anagramTable.entrySet()) {
			int newLength = entry.getValue().size();
			if (newLength > largest) { 
				largest = newLength;
			}
		}
		for (Map.Entry<Long,ArrayList<String>> entry : anagramTable.entrySet()) {
			int newLength = entry.getValue().size();
			if (newLength == largest) { 
				maxNum.add(entry);
			}
		}
		return maxNum;
	}
	/**
	 * read all the strings in a file, place them in the hash table of anagrams and then 
	 * iterate over the hash table to report which words have the largest number of anagrams.
	 * The code for main method is provided.
	 * @param args
	 */
	public static void main(String[] args) {
		Anagrams a = new Anagrams ();
		final long startTime = System.nanoTime(); 
		try {
			a.processFile("words_alpha.txt"); 
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries(); 
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000); 
		System.out.println("Time: "+ seconds);
		System.out.println("List of max anagrams: "+ maxEntries);
	}
}
