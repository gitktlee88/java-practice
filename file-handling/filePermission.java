// Java program to change the file permissions 
import java.io.*; 

public class filePermission 
{ 
	public static void main(String[] args) 
	{ 
		// creating a new file instance 
		File file = new File("C:\\Users\\Mayank\\Desktop\\1.txt"); 
		
		// check if file exists 
		boolean exists = file.exists(); 
		if(exists == true) 
		{ 
			// changing the file permissions 
			file.setExecutable(true); 
			file.setReadable(true); 
			file.setWritable(false); 
			System.out.println("File permissions changed."); 

			// printing the permissions associated with the file currently 
			System.out.println("Executable: " + file.canExecute()); 
			System.out.println("Readable: " + file.canRead()); 
			System.out.println("Writable: "+ file.canWrite()); 
			
		} 
		else
		{ 
			System.out.println("File not found."); 
		} 
	} 
} 
