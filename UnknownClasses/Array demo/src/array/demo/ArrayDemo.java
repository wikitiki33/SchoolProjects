/*Christian Brown
 * 6/27/2016
 * University of Phoenix
The program should allow a user to do the following:
Add, edit, delete different types of animals
Select an animal, and the corresponding characteristics will be displayed (such as color, vertebrate or invertebrate, can swim, etc.)
The program must use ArrayList(s) to work with these animal objects. It must also be able to save and retrieve said list

 */
package array.demo;
//needed imports
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 *
 * @author wikit
 */
public class ArrayDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = null;
        int selection;//setting up variables and array lists
        boolean more = true;
        boolean run;
        int index;
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);//setting up reader for user input
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String read;
        ArrayList animal = new ArrayList();
        ArrayList location = new ArrayList();
        //setup
        try {//checks for previous save.
            String sCurrentLine;
            System.out.println("Welcome back");
            //use the BufferReader to read the first line in the file
            br = new BufferedReader(new FileReader("animals.txt"));
        } catch (IOException e) {

            System.out.println("Hello, I am designed to keep make a list of animals and attributes for you.");
            System.out.println("Please enter an animal now");//recieves input for first animal
            index = 0;
            animal.add(index, reader.readLine());
            location.add(index);
            while (more == true) {
                System.out.println("Please add a characteristic of this animal now like ''HAIR, BROWN'' ");//adds at least one attribute to animal
                animal.add(reader.readLine());
                System.out.println("Would you like to add another characteristic? y,n");
                switch (reader.readLine()) {
                    case "y"://watches for yes
                    case "Y":

                        break;
                    case "n"://watches for no
                    case "N":
                        more = false;
                        index = animal.size();
                        break;
                }
            }
        }
        run = true;//starts loop after setup
        more = true;
        selection = 0;
        //
        //loop
        while (run == true)//runs until program ended
        {
            while (selection == 0)//runs after each user selection "main menu"
            {
                System.out.println("Now that you have an animal entered, you may" + "\n" + " ''1'' add another animal," + "\n" + " ''2'' edit an animal," + "\n" + " ''3'' delete an animal," + "\n" + " ''4'' select an animal to print," + "\n" + " ''5'' to save list," + "\n" + " ''6'' to retrieve saved list or anything else to quit");
                read = reader.readLine();
                if ("1".equals(read) || "2".equals(read) || "3".equals(read) || "4".equals(read) || "5".equals(read) || "6".equals(read))//checks for user action requested
                {
                    selection = Integer.parseInt(read); //selects correct action
                    run = true;
                    more = true;
                } else {
                    run = false;
                    selection = 10;//ends loop
                    break;
                }
            }
            while (selection == 1)//adds a new animal
            {
                System.out.println("Please enter an animal now");
                index = animal.size();//indexes animal location in array list for retrieval later
                animal.add(reader.readLine());
                location.add(index);
                while (more == true) {
                    System.out.println("Please add a characteristic of this animal now like ''HAIR, BROWN'' ");//same as setup
                    animal.add(reader.readLine());
                    System.out.println("Would you like to add another characteristic? y,n");
                    switch (reader.readLine()) {
                        case "y"://watches for yes
                        case "Y":
                            break;
                        case "n"://watches for no
                        case "N":
                            selection = 0;
                            more = false;
                            index = animal.size();
                            break;
                    }
                }
            }
            while (selection == 2)//allows a user to eddit an animal and attributes
            {
                System.out.println("select an animal to edit");
                //System.out.print(" : ");
                for (int j = 0; j < location.size(); j++)//displays only the animals to edit
                {
                    int retrieve = (int) location.get(j);
                    if (animal.get(retrieve) != "deleted") {
                        System.out.print(j + " : " + animal.get(retrieve));
                        System.out.println("");
                    }
                }
                read = reader.readLine();//checks to see user selection
                if (read != null) {
                    int retrieve = (int) location.get(Integer.parseInt(read));
                    if (retrieve < location.size() - 1)//looks at location of animal to see how many times to run the loop
                    {
                        int end = (int) location.get(Integer.parseInt(read + 1));
                        for (int i = retrieve; i < end; i++) {
                            System.out.println("Please enter a replacement for " + animal.get(i));
                            animal.set(i, reader.readLine());
                        }
                    } else//this is the last animal in the arraylist if chosen
                    {
                        for (int i = retrieve; i < animal.size(); i++) {
                            System.out.println("Please enter a replacement for " + animal.get(i));
                            animal.set(i, reader.readLine());
                        }
                    }
                    selection = 0;//sends back to main menue
                }
                break;
            }
            while (selection == 3) {
                System.out.println("select an animal to delete");//allows a user to remove an animal and all attributes from the list
                //System.out.print(" : ");
                for (int j = 0; j < location.size(); j++) {
                    int retrieve = (int) location.get(j);//same as the eddit function
                    if (animal.get(retrieve) != "deleted") {
                        System.out.print(j + " : " + animal.get(retrieve));
                        System.out.println("");
                    }
                }
                read = reader.readLine();
                if (read != null) {
                    int retrieve = (int) location.get(Integer.parseInt(read));
                    if (retrieve < location.size() - 1) {
                        int end = (int) location.get(Integer.parseInt(read + 1));
                        for (int i = retrieve; i < end; i++) {
                            animal.set(i, "deleted");  //flags selected animal and attributes for deletion and overwrites their contents
                        }
                    } else {
                        for (int i = retrieve; i < animal.size(); i++) {
                            animal.set(i, "deleted");  //flags selected animal and attributes for deletion and overwrites their contents
                        }
                    }
                    selection = 0;
                }
                break;
            }
            while (selection == 4) {
                System.out.println("select an animal to see all attributes");//lets a user select one animal to look at with its attributes
                //System.out.print(" : ");
                for (int j = 0; j < location.size(); j++)//displays all animals available
                {
                    int retrieve = (int) location.get(j);
                    if (animal.get(retrieve) != "deleted") {
                        System.out.print(j + " : " + animal.get(retrieve));
                        System.out.println("");
                    }
                }
                read = reader.readLine();//looks for user input and selects the correct animal
                if (read != null) {
                    int retrieve = (int) location.get(Integer.parseInt(read));
                    if (retrieve < location.size() - 1) {
                        int end = (int) location.get(Integer.parseInt(read + 1));
                        for (int i = retrieve; i < end; i++) {
                            System.out.print(animal.get(i) + " : ");
                        }
                    } else {
                        for (int i = retrieve; i < animal.size(); i++) {
                            System.out.print(animal.get(i) + " : ");
                        }
                    }
                    selection = 0;
                }
                break;
            }
            while (selection == 5) {//this is in charge of saving a list
                try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream("animals.txt"), "utf-8"))) {//creates/overwrites the save file
                    for (int i = 0; i < location.size(); i++) {

                        writer.write((int) location.get(i) + ";");//writes values of location keys first

                    }
                    for (int i = 0; i < animal.size(); i++) {
                        if (animal.get(i) != "deleted") {

                            writer.write(":" + (String) animal.get(i) + ":");//writes animals second with their info

                        }

                    }

                    System.out.println("File saved");
                    selection = 0;
                }
                break;
            }
            while (selection == 6) {//retrieves the saved data
                animal.clear();//resets the list for being rewritten
                location.clear();
                try {//looks for save file
                    String sCurrentLine;
                    //use the BufferReader to read the first line in the file
                    br = new BufferedReader(new FileReader("animals.txt"));
                    //print the following text
                    //while the current line being readin not null print the current line
                    while ((sCurrentLine = br.readLine()) != null) {
                        // System.out.println(sCurrentLine);
                        String expr = sCurrentLine;
                        String nums = "[;]";//parses out location keys
                        String[] numbers = expr.split(nums);
                        String anims = "[:]";//parses out animals
                        String[] animals = expr.split(anims);
                        for (String number : numbers) {
                            try {
                                int x = Integer.parseInt(number);
                                //create an array and add read line to the arraylist
                                location.add(x);//writes the new location keys
                            } catch (NumberFormatException nfe) {
                            }
                        }
                        for (int i = 1; i < animals.length; i = i + 2) {

                            //adds new animals to list
                            animal.add(animals[i]);

                        }

                    }
                    selection = 0;
                } catch (IOException e) {//looks for file not found
                    System.out.println("File not found, please ensure you created a file first");
                    selection = 0;
                }
                System.out.println("File Read");
                break;
            }
        }
        System.out.println("Now printing all animals and attributes");//runs at the end of the program and shows a final list of all animals and attributes sorted 
        //in the order they were added.
        for (int i = 0; i < animal.size(); i++) {
            if (animal.get(i) != "deleted") {
                System.out.print(" : ");
                System.out.print(animal.get(i) + " : ");
            }
            for (int j = 0; j < location.size(); j++) {
                int retrieve = (int) location.get(j);
                if (i == retrieve - 1) {
                    System.out.println("");
                }
            }
        }

    }

}
