
//<< Runnable or Thread, Which one to use? >>
//
// The first method, where you create a thread by extending from Thread class is very limited because once you 
// extend your class from Thread, you cannot extend from any other class since Java doesn’t allow multiple inheritance.
// Also, 
// If you follow good design practice, Inheritance is meant for extending the functionality of the parent class, 
// but when you create a thread, you don’t extend the functionality of Thread class, 
// you merely provide the implementation of run() method.
//
// So, In general, You should always use Runnable object to create a thread. This method is more flexible. 
// It allows your class to extend from any other class. 
// Also, you can use anonymous class syntax and Java 8’s lambda expression with Runnable to make your code more concise.

// << Why don’t we call run() method directly, why call start() method? >>

// We can call run() method if we want but then it would behave just like a normal method and we would not be 
// able to take the advantage of multithreading. 
// When the run method gets called though start() method then a new separate thread is being allocated to 
// the execution of run method, so if more than one thread calls start() method that means their run method is 
// being executed by separate threads (these threads run simultaneously).


public class ThreadExample extends Thread {

    // run() method contains the code that is executed by the thread.
    @Override
    public void run() {
        System.out.println("Inside : " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        System.out.println("Inside : " + Thread.currentThread().getName());

        System.out.println("Creating thread...");
        Thread thread = new ThreadExample();

        System.out.println("Starting thread...");
        thread.start();
    }
}



# Output
Inside : main
Creating thread...
Starting thread...
Inside : Thread-0


public class RunnableExample implements Runnable {

    public static void main(String[] args) {
        System.out.println("Inside : " + Thread.currentThread().getName());

        System.out.println("Creating Runnable...");
        Runnable runnable = new RunnableExample();

        System.out.println("Creating Thread...");
        Thread thread = new Thread(runnable);

        System.out.println("Starting Thread...");
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("Inside : " + Thread.currentThread().getName());
    }
}


# Output
Inside : main
Creating Runnable...
Creating Thread...
Starting Thread...
Inside : Thread-0



Anonymous classes enable you to make your code more concise. 
They enable you to declare and instantiate a class at the same time. - From Java doc.

public class RunnableExampleAnonymousClass {

    public static void main(String[] args) {
        System.out.println("Inside : " + Thread.currentThread().getName());

        System.out.println("Creating Runnable...");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Inside : " + Thread.currentThread().getName());
            }
        };

        System.out.println("Creating Thread...");
        Thread thread = new Thread(runnable);

        System.out.println("Starting Thread...");
        thread.start();
    }
}


// The above example can be made even shorter by using Java 8’s lambda expression -

import java.util.concurrent.TimeUnit;

public class RunnableExampleLambdaExpression {

    public static void main(String[] args) {
        System.out.println("Inside : " + Thread.currentThread().getName());

        System.out.println("Creating Runnable...");
        Runnable runnable = () -> {
            System.out.println("Inside : " + Thread.currentThread().getName());
        };

        System.out.println("Creating Thread...");
        Thread thread = new Thread(runnable);

        System.out.println(thread.currentThread().getName() + " Starting new Thread...");
        thread.start();
        if(thread.isAlive()) {
        	System.out.println(thread.getName() + " alive");
        }
        
      //Let's wait to see server thread stopped 
        try {
			TimeUnit.MILLISECONDS.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        if(!thread.isAlive()) {
        	System.out.println(thread.getName() + " dead now.");
        }

    }
}
