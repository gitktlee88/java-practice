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
