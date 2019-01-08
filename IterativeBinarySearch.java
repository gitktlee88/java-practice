//import java.util.Arrays;

// Driver class
public class Iterative { 
	//public int runBinarySearchIteratively(int[] sortedArray, int key, int low, int high) {
	public int runBinarySearchIteratively(int[] sortedArray, int key) {
		int low = 0;
		int high = sortedArray.length -1;
		int index = Integer.MAX_VALUE;

		while (low <= high) {
			int mid = (low + high) / 2;
			
			if (sortedArray[mid] < key) {
				low = mid + 1;
			} else if (sortedArray[mid] > key) {
				high = mid - 1;
			} else if (sortedArray[mid] == key) {
				index = mid;
				break;
			}			
		}
		
		if (index == Integer.MAX_VALUE) {
			index = -1;
		}
		
		return index;
	}
  
    public static void main(String[] args) { 
    	
    	Iterative mainobj = new Iterative();
    	
    	int arr[] = { 10, 12, 15, 22, 35, 44, 55, 66, 77, 88, 99 }; 
    	int res = mainobj.runBinarySearchIteratively(arr, 33);
        if (res >= 0) { 
            System.out.println("Found " + arr[res]); 
        } 
    	  else { 
    		  System.out.println("Not found "); 
    	  } 
        
    } 
} 
