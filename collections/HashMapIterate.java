// Java program to traverse through a hashmap using iterator,
// for-Each and forEach
import java.util.*; 

class HashMapIterate { 
	public static void main(String[] args) 
	{ 
		// Consider the hashmap contains 
		// student name and their marks 
		HashMap<String, Integer> hm = new HashMap<String, Integer>(); 

		// Adding mappings to HashMap 
		hm.put("GeeksforGeeks", 54); 
		hm.put("A computer portal", 80); 
		hm.put("For geeks", 82); 
		//hm.put("GeeksforGeeks", 55);    // it replaces for an existing key

		// Printing the HashMap 
		System.out.println("Created hashmap is" + hm + '\n'); 
		//System.out.println();
		
		// Getting an iterator 
		Iterator hmIterator = hm.entrySet().iterator(); 

		// Iterate through the hashmap 
		// and add some bonus marks for every student 
		System.out.println("HashMap after adding bonus marks:"); 

		while (hmIterator.hasNext()) { 
			Map.Entry mapElement = (Map.Entry)hmIterator.next(); 
			int marks = ((int)mapElement.getValue() + 10); 
			hm.replace((String)mapElement.getKey(), (Integer)marks);
			System.out.println(mapElement.getKey() + " : " + marks); 
		} 
		
		// Loop through the hashmap Using for-each loop 
		System.out.println("HashMap traverse with for-Each:"); 
		for (Map.Entry mapElement : hm.entrySet()) { 
			String key = (String)mapElement.getKey(); 

			// Add some bonus marks 
			// to all the students and print it 
			int value = ((int)mapElement.getValue() + 10); 

			System.out.println(key + " : " + value); 
		} 
		
		// Loop through the hashmap Using Hashmap.forEach() 
		System.out.println("HashMap traverse with forEach:"); 
		
		hm.forEach((k, v) -> System.out.println(k + " : " + (v + 10)));		
		
	} 
} 
