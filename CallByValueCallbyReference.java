
//•	Pass by Value: The method parameter values are copied to another variable(referrence) 
//                     and then the copied object is passed.
//•	Pass by Reference: When we declare a reference type variable, 
// the compiler allocates only space where the memory address of the object can be stored. 
// The space for the object itself isn't allocated. 
// The space for the object is allocated at the time of object creation using the new keyword.

// A variable of reference type differs from a variable of a primitive type 
// in the way that a primitive type variable holds the actual data, 
// while a reference type variable holds the pointer(address of the object) which it refers to and not the actual object. 	

class Number {
	int x;
}

class CallByValueCallByReference {

	public static void main(String[] args) {
		// callbyvalue
		int y = 3;
		System.out.println("Value of y before callbyvalue is " + y);
		increment(y);
		System.out.println("Value of y after callbyvalue is " + y);

		// callbyreference
		Number instancea = new Number();
		instancea.x = 3;
		System.out.println("Value of instancea.x before callbyreference is " + instancea.x);
		increment(instancea);
		System.out.println("Value of instancea.x after callbyreference is " + instancea.x);
	}

	public static void increment(int a) {
		System.out.println("Value of a before incrementing is " + a);
		a = a + 1;
		System.out.println("Value of a after incrementing is " + a);
	}

	public static void increment(Number n) {
		System.out.println("Value of n.x before incrementing x is " + n.x);
		n.x = n.x + 1;
		System.out.println("Value of n.x after incrementing x is " + n.x);
	}
}
