/*
*The program will read from a file and iterate each line of an animal and its color.
*Once there are no more animals in the list the program will let you know and end.
 */
package iteratordemo;

/**
 *
 * @author AMoon
 */
import java.util.*;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class IteratorDemo {

   public static void main(String args[]) {
       BufferedReader br = null;  //create bufferreader called br
       try {
           
           String sCurrentLine;
           //use the BufferReader to read the first line in the file
           br = new BufferedReader(new FileReader("animals.txt"));
           //print the following text
           System.out.print("The first animal in the file list is a: ");
           //while the current line being readin not null print the current line
           while ((sCurrentLine = br.readLine()) != null) {
               System.out.println(sCurrentLine);
              
               //create an array and add read line to the arraylist
               ArrayList al = new ArrayList();
                al.add("");
       

       System.out.print("The next animal in the file list is a: ");
       
       //go to the next line in the arraylist
       Iterator itr = al.iterator();
       
           }
           
       } catch (IOException e) {
       } finally {
           try {
               //if the line is null then print the text and close
               if (br != null)br.close();
               System.out.print("There are no more animals in the list.");
           } catch (IOException ex) {
           }
       }
   }
}


   
 

		

	