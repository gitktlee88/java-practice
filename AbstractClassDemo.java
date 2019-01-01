
public class AbstractClassDemo {

	public static void main(String args[]){
		Bank hsbc = new HSBC();
		hsbc.deposit();
		hsbc.withdrow();
		hsbc.calcinterest();
		
		Bank barclays = new Barclays();
		barclays .deposit();
		barclays .withdrow();
		barclays .calcinterest();		
	}
}

abstract class Bank {
	public void deposit(){
		System.out.println("Common deposit method across all Banks");
	}
	
	public void withdrow(){
		System.out.println("Common withdrow method across all Banks");
	}
	public abstract int calcinterest();
}

class HSBC extends Bank {

	@Override
	public int calcinterest() {
		// TODO Auto-generated method stub
		System.out.println("HSBC calcinterest method implemented");
		return 0;
	}	
}

class Barclays extends Bank {

	@Override
	public int calcinterest() {
		// TODO Auto-generated method stub
		System.out.println("Barclays  calcinterest method implemented");
		return 0;
	}	
}
