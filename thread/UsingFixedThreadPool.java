/*
 * The Java ExecutorService is a construct that allows you to pass a task to be executed by a thread asynchronously. 
 * The executor service creates and maintains a reusable pool of threads for executing submitted tasks. 
 * The service also manages a queue, which is used when there are more tasks than the number of threads in the pool 
 * and there is a need to queue up tasks until there is a free thread available to execute the task.
 */

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
