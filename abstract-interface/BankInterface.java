//For creating a default method in java 8 interface, we need to use “default” keyword with the method signature.

public interface BankInterface {
	default void deposit(){   // default of interface method
		System.out.println("default interface method from java 8 : deposit() interface");
	}
	       abstract void withdrow();     // public abstract is default of interface method
	public abstract void calcinterest(); // same effect as above methods.
}	
