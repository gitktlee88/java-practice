//we have made the only critical section (part of the code which is creating 
//an instance of singleton) synchronized and saved some performance. 

public class Singleton {
	private static volatile Singleton _instance;

	public static Singleton getInstance() {
		if (_instance == null) {
			synchronized (Singleton.class) {
				if (_instance == null)	{
					_instance = new Singleton();
					System.out.println(" _instance created" + _instance);
				}
				
			}
		}
		return _instance;
	}
	
	public static void main(String[] args){
		for (int i=3; i>0; i--){			
			System.out.println(Singleton.getInstance());
		}
	}
}
