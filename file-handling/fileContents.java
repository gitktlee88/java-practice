import java.io.BufferedReader; 
import java.io.File; 
import java.io.IOException; 
import java.io.InputStreamReader; 
  
//Displaying the contents of a directory 
class fileContents 
{ 
    public static void main(String[] args) throws IOException { 
        //enter the path and dirname from keyboard 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
  
        System.out.println("Enter dirpath:"); 
        String dirpath=br.readLine(); 
        System.out.println("Enter the dirname"); 
        String dname=br.readLine(); 
  
        //create File object with dirpath and dname 
        File f = new File(dirpath, dname); 
  
        //if directory exists,then 
        if(f.exists()) 
        { 
            //get the contents into arr[] 
            //now arr[i] represent either a File or Directory 
            String arr[]=f.list(); 
  
            //find no. of entries in the directory 
            int n=arr.length; 
  
            //displaying the entries 
            for (int i = 0; i < n ; i++) { 
                System.out.println(arr[i]); 
                //create File object with the entry and test 
                //if it is a file or directory 
                File f1=new File(arr[i]); 
                if(f1.isFile()) 
                    System.out.println(": is a file"); 
                if(f1.isDirectory()) 
                    System.out.println(": is a directory"); 
            } 
            System.out.println("No of entries in this directory "+n); 
        } 
        else
            System.out.println("Directory not found"); 
    } 
} 




/////// String version /////////

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FizzBuzz {
	// static int[] intArray;

	public static void main(String args[]) throws IOException {
		//enter the path and dirname from keyboard 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        System.out.println("Enter star number:"); 
        String start=br.readLine();
        int snum = Integer.valueOf(start);
       
//       System.out.println("Enter end number:"); 
//       String end=br.readLine();
//       int endnum = Integer.valueOf(end);
       
       //this console read works on command line not on IDE(Eclipse)
//		  System.out.println("Enter end number: ");
//	    String end = System.console().readLine();
//      int endnum = Integer.valueOf(end);
       
       Scanner scanner = new Scanner(System.in);
       System.out.println("Enter end number: ");
       String end = scanner.next();
       int endnum = Integer.valueOf(end);
       
        FizzBuzz fizbuz = new FizzBuzz();
		String[] strarr = fizbuz.fizzBuzz(snum, endnum);
		for (int i = 0; i < strarr.length; i++) {
			System.out.println(strarr[i]);
		}
	}

	public String[] fizzBuzz(int start, int end) {
		if (start > end) {
			System.out.println("start is bigger than end");
			// exit();
		}

		String[] strArray = new String[30];

		for (int i = start; i <= end; i++) {
			if (i % 3 == 0 && i % 5 == 0) {
				strArray[i] = "FizzBuzz";
			} else if (i % 3 == 0) {
				strArray[i] = "Fizz";
			} else if (i % 5 == 0) {
				strArray[i] = "Buzz";
			} else {
				strArray[i] = String.valueOf(i);
			}

		}
		return strArray;
	}
}

