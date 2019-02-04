//
// To create an instance of generic class 
// BaseType <Type> obj = new BaseType <Type>()

// Note: In Parameter type we can not use primitives like 'int','char' or 'double'.

class Test<T> {
	T obj; // An object of type T is declared

	Test(T obj) {
		this.obj = obj;
	} // constructor

	public T getObject() {
		return this.obj;
	}
}

public class GenericType {
	public static void main(String[] args)
	{
		//instance of Integer type 
		Test <Integer> iObj = new Test <Integer>(15); 
		System.out.println( iObj.getObject() );
		
		//instance of String type
		Test <String> sObj = new Test <String>("GeeksForGeeks");
		System.out.println( sObj.getObject() );
	}
}


////////////////////////////////////
// Example of multiple parameters //
////////////////////////////////////

class Test<T, U> {
	T obj1; // An object of type T is declared
	U obj2; // An object of type U is declared	

	Test(T obj1, U obj2) {
		this.obj1 = obj1;
		this.obj2 = obj2;
	} // constructor

	public void print() 
       { 
        System.out.println(obj1);
        System.out.println(obj2);
	}
}

public class GenericType2 {
	public static void main(String[] args)
	{
		//instance of Test 
		Test <String, Integer> obj = new Test <String, Integer>("multiple type parameter",15); 
		obj.print();
	}
}

