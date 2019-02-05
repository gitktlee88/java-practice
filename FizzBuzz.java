public class FizzBuzz {
	//static int[] intArray;
	
	public static void main(String args[]) {
		int[] intArray = new int[30];
		
		for (int i = 1, j=0; i<=30; i++){
			
			if (i%3 ==0 &&  i%5==0) {
				System.out.println("FizzBuzz");
			}else if (i%3 == 0){
				System.out.println("Fizz");
			}else if (i%5 == 0){
				System.out.println("Buzz");
			}else{
				intArray[j] = i;
				j++;
			}		
			
		}
		for(int i=0; i < intArray.length; i++){
			System.out.println(intArray[i]);
		}
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

