import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//import LoopTaskA;

public class UsingFixedThreadPool {

	public static void main(String[] args) {
		//Finding number of Cores in Java
		int cores = Runtime.getRuntime().availableProcessors();		
		System.out.println("main thread start here...cores# " + cores);		

		ExecutorService execService = Executors.newFixedThreadPool(cores);	
			
		execService.execute(new LoopTaskA());
		execService.execute(new LoopTaskA());
		execService.execute(new LoopTaskA());
		execService.execute(new LoopTaskA());
		execService.execute(new LoopTaskA());
		execService.execute(new LoopTaskA());
		
		execService.shutdown();
		System.out.println("main thread ends here...");
		
		//execService.execute(new LoopTaskA());
	}
}
