/*Christian Brown
 * 6/13/2016
 * University of Phoenix
 * The key parts of the singleton pattern are:
A private static variable to store the single instance called the singleton
A public static method for callers to get a reference to the instance
A private constructor so no callers can instantiate the object directly
Using these key parts, write a Java program that will allow a user of the program to assign only one runner to each of the 8 lanes of running track in a field.
 */
package field.runner.pkg2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

/**
 *
 * @author wikit
 */
public class FieldRunner2 {
static public int looped = 0;//keeps track of how many runners are added
static public boolean set = true; // watches for a valid selection
public static String[] rl;//runner location
private Vector<String> availableRunners = null;

// Calls the Constructor.
// and only one copy will be available throughout the program.
private static final FieldRunner2 ListOfRunners = new FieldRunner2();  // (this is the singleton)

// Constructor
private FieldRunner2() {
availableRunners = new Vector<String>();
availableRunners.add("Lane 1");
availableRunners.add("Lane 2");
availableRunners.add("Lane 3");
availableRunners.add("Lane 4");
availableRunners.add("Lane 5");
availableRunners.add("Lane 6");
availableRunners.add("Lane 7");
availableRunners.add("Lane 8");
	}

// This is the only public access to the list for an external program
// Prevents creation of additional/duplicate array of antiques
public static FieldRunner2 createInstance() {
return ListOfRunners;
	}

	public boolean PlaceRunner(String location) {
	boolean locationAvailable = false;
	int exists = availableRunners.indexOf(location);  // returns -1 if item not in list
	if (exists != -1) {
                   
	// marks a position as filled with a runner and send a confirmation back
		locationAvailable = true;
		availableRunners.remove(location);
                looped++;
		}
                else
                {
                    
                }
		return locationAvailable;
	}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        rl = new String[8];//stores runner locations
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);//lets user input strings
        BufferedReader reader = new BufferedReader(inputStreamReader);
        ListOfRunners.createInstance();
        OUTER://loop in charge of assigning runners to locations using user input
        for (int i = 0; i<8; i++) 
                {
                    i = looped; // sets the loop to the correct runner
      
                    System.out.println("Where would you like Runner " + i + "? ''1-8''");
                       
                    if (i == 7)
        {//last time loop can run
            System.out.println("This is the last Runner you can assign right now");
        }
                    String read = reader.readLine();
                    SetLane("Lane " + read);
                    rl[i] = read;
                    if (set == true)//only lets this part run if a runner has been placed
                    {
                    if(i<7){
        System.out.println("is there another runner you would like to assign? Y/N");
                switch (reader.readLine()) {
                case "y"://watches for yes
                case "Y":
                   
                break;
                case "n"://watches for no
                case "N"://prints runner locations if all spots that are needed are used
                System.out.println("Printing runner locations");
                for (int j = 0; j < looped; j++)
                    {
                        System.out.println("Runner "+(j) + " is in Lane" + rl[j]);
                    }
                break OUTER;
                default://throws an error if no acceptable input was detected
                   // error();
                break;
                    }
                    }
                    else
                    {//prints out all runners and locations if track is full
                    System.out.println("Max number of Runners, Printing runner locations");
                    for (int j = 0; j < looped; j++)
                    {
                        System.out.println("Runner "+(j) + " is in Lane" + rl[j]);
                    }
                    }
        }
      }
        
    }//checks if a location is available or not and prints the results.
    private static void SetLane(String desiredLocation) {

        if (ListOfRunners.PlaceRunner(desiredLocation) == true)
        {
            
            System.out.println("Runner " + looped +" is at Lane " + desiredLocation);
            set = true;
        }
        else
        {   set = false;
            System.out.println("Lane " + desiredLocation + " is not available" + " please pick a new location");
            
        }
       
     }
    
}
