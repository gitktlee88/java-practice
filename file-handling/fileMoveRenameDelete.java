// Java program to illustrate renaming and 
// moving a file permanently to a new loaction 

import java.io.*; 
import java.nio.file.Files; 
import java.nio.file.*; 

public class fileMoveRenameDelete 
{ 
	public static void main(String[] args) throws IOException 
	{ 
		Path temp = Files.move 
		(Paths.get("C:\\Users\\kyung.lee\\Desktop\\44.txt"), Paths.get("C:\\Users\\kyung.lee\\Desktop\\dest\\445.txt")); 

		if(temp != null) 
		{ 
			System.out.println("File renamed and moved successfully"); 
		} 
		else
		{ 
			System.out.println("Failed to move the file"); 
		} 


// Java program to illustrate Copying the file and deleting the original file 

    File file = new File("C:\\Users\\Mayank\\Desktop\\1.txt"); 
          
    // renaming the file and moving it to a new location 
    if(file.renameTo (new File("C:\\Users\\Mayank\\Desktop\\dest\\newFile.txt"))) 
    { 
        // if file copied successfully then delete the original file 
        file.delete(); 
        System.out.println("File moved successfully"); 
    } 
    else
    { 
        System.out.println("Failed to move the file"); 
    }    
	} 
} 
