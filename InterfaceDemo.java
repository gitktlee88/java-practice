public class InterfaceDemo {	
	public static void main(String args[]){
		HSBC2 hsbc = new HSBC2();
		hsbc.deposit();
		hsbc.withdrow();
		hsbc.calcinterest();
		
		Barclays2 barclays = new Barclays2();
		barclays .deposit();
		barclays .withdrow();
		barclays .calcinterest();		
	}
}

class HSBC2 implements BankInterface {

	@Override
	public void calcinterest() {
		// TODO Auto-generated method stub
		System.out.println("HSBC interest implemented");
	}

	@Override
	public void deposit() {
		// TODO Auto-generated method stub
		System.out.println("HSBC deposit  implemented");
	}

	@Override
	public void withdrow() {
		// TODO Auto-generated method stub
		System.out.println("HSBC withdrow  implemented");
	}

}

class Barclays2 implements BankInterface {

	@Override
	public void calcinterest() {
		// TODO Auto-generated method stub
		System.out.println("Barclays  interest implemented");
	}

	@Override
	public void deposit() {
		// TODO Auto-generated method stub
		System.out.println("Barclays  deposit  implemented");
	}

	@Override
	public void withdrow() {
		// TODO Auto-generated method stub
		System.out.println("Barclays withdrow  implemented");
	}	
}
