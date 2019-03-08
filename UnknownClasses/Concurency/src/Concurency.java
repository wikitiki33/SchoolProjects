/*Christian Brown
 * 7/11/2016
 * University of Phoenix
Write a Java program (non-GUI preferred) that has a method named atomic().
Demonstrate in the program how two threads can, sometimes, invoke atomic() concurrently.

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



class FooRunnable implements Runnable {
	public void run() {
		A.Atomic(true);//calls atomic and flags for thread one
                try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
}
class MeRunnable implements Runnable {
	public void run() {
            A.Atomic(false);//calls atomic and flags for thread two
		try {
				Thread.sleep(70);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
}
class A {
    static void Atomic(boolean one) {
        if (one == true)//runs thread ones request
        {
            for(int x = 1; x < 6; x++) {
			System.out.println("Runnable running " + x);
			
		}
        }
        else if(one == false)//runs thread twos request
        {
        for(int y = 1; y < 6; y++) {
                    
			System.out.println("Run II " + y);
			
		}
        }
    }
}
public class Concurency {
	public static void main (String [] args) throws IOException {
             InputStreamReader inputStreamReader = new InputStreamReader(System.in);//setting up reader for user input
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String read;
		FooRunnable r = new FooRunnable();//create the threads
		MeRunnable rMe = new MeRunnable();
                
		Thread t ;
		Thread tMe ;

		
                boolean run = true;
                while(run)
                {
                    
                    System.out.println("Would you like to run thread 1, 2 or 3/both? enter 4 to exit");
                    read = reader.readLine();
                    if ("1".equals(read) || "2".equals(read) || "3".equals(read)|| "both".equals(read) || "4".equals(read))
                    {
                    switch (read) {
                        case "1"://runs thread one alone
                            t = new Thread(r);
                            t.start();
                        break;
                        case "2"://runs thread two alone
                            tMe = new Thread(rMe);
                            tMe.start();
                        break;
                        case "3":
                        case "both"://runs both threads at once
                            tMe = new Thread(rMe);
                            t = new Thread(r);
                            t.start();
                            tMe.start();
                        break;
                        case "4":
                            run = false;
                            break;
                    }                              
                }
                    else
                    {
                        System.out.println("please enter a valid selection");
                    }

                }
        }
	}


