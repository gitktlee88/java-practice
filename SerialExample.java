// Java code for serialization and deserialization of a Java object.

//	1. If a parent class has implemented Serializable interface then child class doesn’t need to implement it 
//	   but vice-versa is not true.
//	2. Only non-static data members are saved via Serialization process.
//	3. Static data members and transient data members are not saved via Serialization process.
//		   So, if you don’t want to save value of a non-static data member then make it transient.
//	4. Constructor of object is never called when an object is deserialized.
//	5. Associated objects must be implementing Serializable interface.

// Serialization is a mechanism of converting the state of an object into a byte stream. 
// Deserialization is the reverse process where the byte stream is used to recreate the actual Java object in memory. 
// This mechanism is used to persist the object.

import java.io.*; 

class Emp implements Serializable { 
	private static final long serialversionUID = 129348938L; 
	transient int a; 
	static int b; 
	String name; 
	int age; 

	// Default constructor 
	public Emp(String name, int age, int a, int b) 
	{ 
		this.name = name; 
		this.age = age; 
		this.a = a; 
		this.b = b; 
	} 

} 

public class SerialExample { 
	public static void printdata(Emp object1) 
	{ 
		System.out.println("name = " + object1.name); 
		System.out.println("age = " + object1.age); 
		System.out.println("a = " + object1.a); 
		System.out.println("b = " + object1.b); 
	} 

	public static void main(String[] args) 
	{ 
		Emp object = new Emp("ab", 20, 2, 1000); 
		String filename = "shubham.txt"; 

		// Serialization 
		try { 
			// Saving of object in a file 
			FileOutputStream file = new FileOutputStream(filename); 
			ObjectOutputStream out = new ObjectOutputStream(file); 

			// Method for serialization of object 
			out.writeObject(object); 
			out.close(); 
			file.close(); 

			System.out.println("Object has been serialized\n"
							+ "Data before Deserialization."); 
			printdata(object); 

			// value of static variable changed 
			object.b = 2000; 
		} 

		catch (IOException ex) { 
			System.out.println("IOException is caught"); 
		} 

		object = null; 

		// Deserialization 
		try { 
			// Reading the object from a file 
			FileInputStream file = new FileInputStream(filename); 
			ObjectInputStream in = new ObjectInputStream(file); 

			// Method for deserialization of object 
			object = (Emp)in.readObject(); 

			in.close(); 
			file.close(); 
			System.out.println("Object has been deserialized\n"
								+ "Data after Deserialization."); 
			printdata(object); 

			// System.out.println("z = " + object1.z); 
		} 

		catch (IOException ex) { 
			System.out.println("IOException is caught"); 
		} 

		catch (ClassNotFoundException ex) { 
			System.out.println("ClassNotFoundException" + 
								" is caught"); 
		} 
	} 
} 
