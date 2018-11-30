import java.util.Random;

public class BruteForceCrackerThreads implements Runnable{
    public void run(){
        System.out.println("Thread "+Thread.currentThread().getId()+ " is running.");
        int ctr = Math.toIntExact((Thread.currentThread().getId()-10)*1000000);
		while(!checkCode(ctr++) && ctr!=Math.toIntExact((Thread.currentThread().getId()-9)*1000000));
		
		endTime = System.currentTimeMillis();
		elapsedTime = (float)(endTime - startTime);
		elapsedTime /= 1000.f;
		System.out.println("Total time taken: " + elapsedTime + " seconds");
        System.out.println("The code was " + code +".");
    }
    static long code = (long)(new Random().nextDouble() * 1_000_000_000);
	
	static long startTime;
	static long endTime;
	static float elapsedTime;
    public static void main(String[] args) {
        for(int i=0; i<10; i++){
            Thread object = new Thread(new BruteForceCrackerThreads());
            object.start();
        }
        startTime=System.currentTimeMillis();

	}
	
	public static boolean checkCode(long p){
		if(p == code){
			return true;
		}else{
			return false;
		}
	}
}
    
