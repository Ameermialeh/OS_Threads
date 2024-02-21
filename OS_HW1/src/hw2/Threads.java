package hw2;
//Ameer mialeh 11924682


public class Threads {
	
	static int N = 1182; //682 + 500 = 1182

	// some time passes

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		int expected_mem = (int)Math.pow(N, 2);
		Thread[] threads = new SampleThread[N];
		long start = System.currentTimeMillis();
		for(int i = 0; i < N; i++) {
			threads[i] = new SampleThread(1);
			threads[i].start(); 
		}
		
        for (int i = 0; i < N; i++) {
            threads[i].join();
        }
    	long end = System.currentTimeMillis();
    	long elapsedTime = end - start;
    	System.out.println("Time: "+elapsedTime +" ms");
        System.out.println("Shared memory: "+SharedMem.getShared_mem());
        System.out.println("Expected memory : " + expected_mem);
	}

}

class SharedMem {
	private static  int shared_mem=0;
	
	public static  void inc() {
		setShared_mem(getShared_mem() + 1);
	}

	public static int getShared_mem() {
		return shared_mem;
	}

	public static void setShared_mem(int shared_mem_new) {
		shared_mem = shared_mem_new;
	}
}
 
class SampleThread extends Thread {
		public int proccessingCount = 0;

		SampleThread(int proccessingCount){
			
			this.proccessingCount = proccessingCount;
		}
		
		@Override
		public void run() {
			
			//long TID = Thread.currentThread().getId();
	        // long sleepTime = TID % 10;  
	        int N = 1182; //682 + 500 = 1182
	        
	        // System.out.println("I am thread " + TID + " about to go to sleep for " + sleepTime + " nanoseconds.");
	        /*while(proccessingCount > 0) {
	        	 try {
	                 Thread.sleep(sleepTime);
	             } catch (InterruptedException e) {
	                 e.printStackTrace();
	             }
	        	 proccessingCount--;
	        }*/
	        System.out.println("I am thread: " + Thread.currentThread().getId() + " about to increment the counter, old value was "+ SharedMem.getShared_mem() );			
	        for (int j = 0; j < N; j++) {
	            incrementSharedMem();
	        }
	        System.out.println("I am thread: " + Thread.currentThread().getId()+" finished incrementing the counter, new value is "+ SharedMem.getShared_mem());
		}
		
		 public static synchronized void incrementSharedMem() {// 11924682 % 3 = 0  ==> synchronized method
				SharedMem.inc();
		    }
		 
	}
 
 