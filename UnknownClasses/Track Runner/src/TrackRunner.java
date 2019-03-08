import java.util.*;
/*
 * Adapted from code page 551
 * Sierra, K., & Bates, B. (2015). OCA/OCP JavaÂ® SE 7
 * Programmer I & II Study Guide (Exams 1Z0-803 & 1Z0-804).
 * Retrieved from University of Phoenix eBook Collection database
 */

 public class TrackRunner {

	private Vector<String> availableItems = null;

	// Calls the Constructor.
	// and only one copy will be available throughout the program.
	private static final Antiques listOfAntiques = new Antiques();  // (this is the singleton)

	// Constructor
	private Antiques() {
		availableItems = new Vector<String>();
		availableItems.add("1954 Red Wine");
		availableItems.add("George Washington axe");
		availableItems.add("Abe Lincoln hat");
		availableItems.add("Bob Hope tie");
		availableItems.add("Clint Eastwood holster");
	}

	// This is the only public access to the list for an external program
	// Prevents creation of additional/duplicate array of antiques
	public static Antiques createInstance() {
		return listOfAntiques;
	}


	public boolean buyItem(String itemDesired) {
		boolean itemAvailable = false;
		int exists = availableItems.indexOf(itemDesired);  // returns -1 if item not in list
		if (exists != -1) {
			// item was in list, mark true, and remove it from the list.
			itemAvailable = true;
			availableItems.remove(itemDesired);
		}
		return itemAvailable;
	}

	public static void main(String[] args) {
		// The line below 'listOfAntiques.createInstance();' would be only way
		//   an external program could access 'Antiques'.
		//   Either called here or the Constructor is called by listOfAntiques.buyItem()
		listOfAntiques.createInstance();
		DesireToPurchase("1954 Red Wine");     //as items are purchased they
		DesireToPurchase("Bob Hope tie");     //can no longer be bought again
		DesireToPurchase("1954 Red Wine");     // <-- this results in a false output
	}

    private static void DesireToPurchase(String desiredItem) {
        System.out.println(desiredItem + " availability: " + listOfAntiques.buyItem(desiredItem));
    }
}