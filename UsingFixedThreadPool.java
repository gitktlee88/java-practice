import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//import LoopTaskA;

public class UsingFixedThreadPool {

	public static void main(String[] args) {
		System.out.println("main thread start here...");
		
		ExecutorService execService = Executors.newFixedThreadPool(3);
	
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
