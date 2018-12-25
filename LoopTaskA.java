import java.util.concurrent.TimeUnit;

public class LoopTaskA implements Runnable {
	private static int count = 0;
	private int id;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("### Starting task id-" + id);

		for (int i=5; i>0; i--){
			System.out.println("task - " + id+ "  tick tick " + i);
			try {
				TimeUnit.MILLISECONDS.sleep((long)(Math.random() * 1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println("***  task id-" + id+ " Done");
	}
	
	public LoopTaskA() {
		this.id = ++count;
	}

}
