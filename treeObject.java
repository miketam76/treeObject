import java.io.*;
import java.text.Collator;
import java.util.Locale;
import java.util.Scanner;

public class treeObject {
	
	private static int MAX_PLAYERS = 10;
	private treeNode[] arrNode = new treeNode[MAX_PLAYERS];
	private int lastRecord = 0;
	
	// Default constructor
	public treeObject() {}
	
	// Open file stored in treeFile parameter and copy contents
	// to arrNode array
	public boolean fileInit(String treeFile)
	{
		try
		{
			// Load file
			File scoreFile = new File(treeFile);
			
			// Set up file buffer
			Scanner scoreReader = new Scanner(scoreFile);
			
			for (int i = 0; i < MAX_PLAYERS; i++)
			{
				// Create temp treeNode object to store each record
				treeNode temp = new treeNode();
				
				// This file has 4 fields of the following types
				// String, int, int, int
				temp.setPlayer(scoreReader.next());
				temp.setGoals(scoreReader.nextInt());
				temp.setAssists(scoreReader.nextInt());
				temp.setPoints(scoreReader.nextInt());
				treeInsertItem(temp); // Save to array
				
				// Check if you reached the last player in the list
				if (i != (MAX_PLAYERS - 1))
					scoreReader.nextLine(); // Goes to the next line in file
			}
					
			scoreReader.close(); // Close file
			return true;
		}
		catch(FileNotFoundException fnf )
		{
			System.out.println(treeFile + " not found");
			return false;
		}
	}
	
	// Return lastRecord value
	public int getLastRecord() { return lastRecord; }
	
	// Saves treeNode object to arrNode array
	public void treeInsertItem(treeNode input)
	{
		arrNode[lastRecord] = input;
		lastRecord++; // increment record counter
	}
	
	// Display all records in arrNode array
	public void displayPlayers ()
	{
		System.out.println("Player Name\tGoals\tAssists\tPoints");
		for (int i = 0; i < getLastRecord(); i++)
			System.out.print(arrNode[i].toString());
		System.out.println("\n");
	}
	
	// Search arrNode array for player name
	public treeNode treeSearch(String search)
	{
		boolean found = false;
		int i = 0;
		treeNode foundRecord = new treeNode();
		
		while (i < getLastRecord() && !found)
		{
			
			if (arrNode[i].getPlayer().equals(search))
			{
				foundRecord = arrNode[i];
				found = true;
			}
			i++;
		}
		
		if (found)
			return foundRecord;
		else
			return new treeNode();
		
	}
	
	/* UPDATE : DO NOT USE THIS ONE - older method
	   Sort arrNode array in alpha-numerical order
	   using the following method.
	   TreeNode a initially has the first record in array
	   TreeNode b initially has the record after first in array
	   
	   
	   Compare the first letter in a and b to see either of
	   the following:
	   #1 - a is greater than b
	   #2 - a is equal to b - if this is true, repeat #1 
	   on the second character in a and b and switch places
	   with each other if true else no changes
	   Increment counter  in b to  the list until end of array
	   then increments counter in a and resets the counter in b
	   to current counter of a + 1
	   
	*/
//	public void sort_Ver1() {
//		treeNode a = new treeNode();
//		treeNode b = new treeNode();
//		int i = 0; // Set counter for treeNode a
//		int j = 1; // Set counter for treeNode b
//		
//		
//		while (i < lastRecord)
//		{
//			while (j < lastRecord)
//			{
//				a = arrNode[i];
//				b = arrNode[j];
//				
//				// Compare two objects if a is greater than b
//				// if its true, switch places
//				if(isGreater(a,b))
//				{
//					// switch places
//					arrNode[i] = b;
//					arrNode[j] = a;
//				}
//				j++;
//			}
//			i++;
//			j = i + 1;	
//		}			
//	}
	
	// Utility method used to compare String objects by
	// comparing the first 4 characters - DISCONTINUED
//	private boolean isGreater(treeNode a, treeNode b)
//	{
//		
//		char aa = a.getPlayer().charAt(0);
//		char bb = b.getPlayer().charAt(0);
//		if (aa > bb)
//			return true; // a object is greater than b on the first character
//		else if (aa == bb)
//		{
//			char a1 = a.getPlayer().charAt(1);
//			char b1 = b.getPlayer().charAt(1);
//			if(a1 > b1)
//				return true;
//			else if (a1 == b1)
//			{
//				char a2 = a.getPlayer().charAt(2);
//				char b2 = b.getPlayer().charAt(2);
//				if(a2 > b2)
//					return true;
//				else if(a2 == b2)
//				{
//					char a3 = a.getPlayer().charAt(3);
//					char b3 = b.getPlayer().charAt(3);
//					if(a3 > b3)
//						return true;
//				}
//			}
//		}
//		// if you reached here, it means a is lesser than b
//		return false;		
//	}
		
	// Updated version of the sort method using Collator class
	public void sort()
	{
		// Set up Collator to US English and set its strength to PRIMARY
		Collator usCollator = Collator.getInstance(Locale.US);
		usCollator.setStrength(Collator.PRIMARY);
		
		// Set up 2 treeNode objects to sort the objects to be sorted
		treeNode a = new treeNode();
		treeNode b = new treeNode();
		
		int i = 0; // set counter for a
		int j = 1; // set counter for b
		
		while (i < lastRecord)
		{
			while(j < lastRecord)
			{
				a = arrNode[i]; 
				b = arrNode[j];
				
				// Compare if player name in a is greater than player name in b
				if (usCollator.compare(a.getPlayer(), b.getPlayer()) > 0)
				{
					// Switch places
					arrNode[i] = b;
					arrNode[j] = a;
				}
				j++;
			}
			i++;
			j = i + 1;
		}
	}
	
	// Checks if there are any treeNode objects in array
	public boolean isTreeEmpty()
	{
		if (getLastRecord() != 0)
			return false;
		else
			return true;
	}
	
	// Sort method using bubble method - replaces sort_V1
	// TODO - NEEDS FIXING for string values
	public void sortBubble() {
		int numSort = 0; // determines how times it was sorted 
		boolean sorted = false;
		
		do {
			for(int i = 0; i < getLastRecord(); i++) {
				if((i+1) < getLastRecord()) {
					treeNode temp1 = arrNode[i];
					treeNode temp2 = arrNode[i+1];
					int current = stringToInt(arrNode[i].getPlayer());
					int next = stringToInt(arrNode[i+1].getPlayer());
					if(current > next) {
						arrNode[i] = temp2;
						arrNode[i+1] = temp1;
						numSort++;
					}
				}
			}
			 // Determines how many times the LinkList is sorted and reset the value
			// for the next round of sorting
			if(numSort > 0)
				numSort = 0;
			else
				sorted = true; // set to true if tried to sort but nothing has changed indicating its done
		}
		while(!sorted); // only exits if sorting the LinkList has finished and no sorts have been done
		System.out.println("Player list has been sorted!");
	}
	
	// Converts String value to int value equivalent using accumulated ASCII values
	private int stringToInt(String convert) {
		int result = 0;
		for(int i = 0; i < convert.length(); i++) {
			result = result + convert.charAt(i);
		}
		return result;
	}

}
