class FooRunnable implements Runnable {
	public void run() {
		for(int x = 1; x < 6; x++) {
			System.out.println("Runnable running " + x);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
class MeRunnable implements Runnable {
	public void run() {
		for(int y = 1; y < 6; y++) {
			System.out.println("Run II " + y);
			try {
				Thread.sleep(70);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
public class myThreadProgram {
	public static void main (String [] args) {
		FooRunnable r = new FooRunnable();
		MeRunnable rMe = new MeRunnable();
		Thread t = new Thread(r);
		Thread tMe = new Thread(rMe);
		t.start();
		tMe.start();
	}
}
