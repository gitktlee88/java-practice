// Java program to remove duplicates from ArrayList 
// and Array.sort() & Collections.sort()

import java.util.*; 
import java.util.Arrays; 

public class RemoveDuplicate { 

	// Function to remove duplicates from an ArrayList 
	public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) 
	{ 
		// Create a new ArrayList 
		ArrayList<T> newList = new ArrayList<T>(); 

		// Traverse through the first list 
		for (T element : list) { 
			// If this element is not present in newList, then add it 
			if (!newList.contains(element)) { 
				newList.add(element); 
			} 
		} 

		// return the new list 
		return newList; 
	} 

	// A sample Java program to demonstrate working of 
	// Arrays.sort(). 
	// It by default sorts in ascending order. 
	public static void intSort() { 
		int[] arr = { 13, 7, 6, 45, 21, 9, 101, 102 }; 
		Arrays.sort(arr); 
		System.out.printf("Arrays.sort(), default in ascending : %s" + '\n', Arrays.toString(arr)); 		 
	} 

	// A sample Java program to sort an array 
	// in descending order using Arrays.sort(). 
	public static void descendingSort(){
		
			// Note that we have Integer here instead of 
			// int[] as Collections.reverseOrder doesn't 
			// work for primitive types. 
			Integer[] arr = { 13, 7, 6, 45, 21, 9, 101, 102 }; 

			// Sorts arr[] in descending order 
			Arrays.sort(arr, Collections.reverseOrder()); 

			System.out.printf("Arrays.sort(), in descending : %s" + '\n', Arrays.toString(arr)); 
		} 

	// Java program to demonstrate working of Collections.sort() 
	// to descending order. 
	public static void collectionsSort() 
	{ 
		// Create a list of strings 
		ArrayList<String> al = new ArrayList<String>(); 
		al.add("Geeks For Geeks"); 
		al.add("Friends"); 
		al.add("Dear"); 
		al.add("Is"); 
		al.add("Superb"); 

		/* Collections.sort method is sorting the 
		elements of ArrayList in ascending order. */
		Collections.sort(al, Collections.reverseOrder()); 

		// Let us print the sorted list 
		System.out.println("Collection.sort() elements of ArrayList<String> : " + al + '\n'); 
	} 	
	
	// Driver code 
	public static void main(String args[]) 
	{ 

		// Get the ArrayList with duplicate values 
		ArrayList<Integer> list = new ArrayList<>( Arrays.asList(1, 10, 1, 2, 2, 3, 3, 10, 3, 4, 5, 5)); 

		// Print the Arraylist 
		System.out.println("ArrayList with duplicates: " + list); 

		// Remove duplicates 
		ArrayList<Integer> newList = removeDuplicates(list); 

		// Print the ArrayList with duplicates removed 
		System.out.println("ArrayList with duplicates removed: " + newList); 
		
		//sort in descending order
		Collections.sort(newList);
		System.out.println("ArrayList with descending-sorted: " + newList); 
		
		GFG.intSort();
		descendingSort();
		collectionsSort();
		
	} 
} 
