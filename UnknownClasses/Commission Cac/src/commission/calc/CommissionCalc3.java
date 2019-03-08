/*Christian Brown
 * 5/30/2016
 * University of Phoenix
 * The purpose of this program is a simple one. To print an adjusted sallary after taking commissions into account. It should also print sales members 
 * in order of how much they sold as well as how much they were shy of being in first place for sales.
 */
package commission.calc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author Christian Brown
 * Library used is for displaying a visual box that allows user interaction
 */
//variables used throughout the program
public class CommissionCalc3 {
static double salary = 85000;
static double target = 150000;
public static double[] num; //used for storing sales ammounts
public static double[] total;
public static int members = 1;
public static int index = 0;
public static String[] salesMan;
public static double[] sort;
public static boolean more = true;

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        //////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////
        // initializing arrays for use in the program
        salesMan = new String[11];
        num = new double[11];
        total = new double[11];
        sort = new double[11];
          //Allows the program to process user input from console
          InputStreamReader inputStreamReader = new InputStreamReader(System.in);
          BufferedReader reader = new BufferedReader(inputStreamReader);
          String Commission;
          //////////////////////////////////////////////////////////////////////
          //////////////////////////////////////////////////////////////////////
          //the loop responsible for getting salesmembers names "will run 10 times max"
    OUTER:
    for (int i = 0; i<= 9; i++) {
      if (i == 0)
        {//first time loop runs
            System.out.println("Hello, please enter salesmans name");
        }
        else
        {//every time after that
            System.out.println("Please enter salesmans name");
        }
        if (i == 9)
        {//last time loop can run
            System.out.println("This is the last salesman you can enter right now");
        }//gets input and assigns it to aray location
        salesMan[i] = reader.readLine();
        //checks if more members are wanting to be added or not
        if(i<9){
        System.out.println("is there another sales member you would like to add? Y/N");
                switch (reader.readLine()) {
                case "y"://watches for yes
                case "Y":
                    members++;
                    break;
                case "n"://watches for no
                case "N":
                    break OUTER;
                default://throws an error if no acceptable input was detected
                    error();
                    break;
            }
        }
        }
    //loop responsible getting sales figures for salesmen
    for( int i =0; i< members; i ++)
          {
          System.out.println("What was the total sales ammount for the year for " + salesMan[i] + ":");
          Commission = reader.readLine();
          ///////////////////////////////////////////////////////////////////////
          ///////////////////////////////////////////////////////////////////////
          // reads user input and and checks for a valid response and terminates if one isnt found
          if (Commission == null|| "".equals(Commission)) 
        {
            error();
        }
          //////////////////////////////////////////////////////////////////////
          //////////////////////////////////////////////////////////////////////
          //if a valid double is found it sends the data to be calculated and assigned to the correct location
         else
        {
            
         num[i] = Double.parseDouble(Commission); 
        total[i] = Calculate(num[i], salary, target);
        }
      }//////////////////////////////////////////////////////////////////////
       //////////////////////////////////////////////////////////////////////
       //prints commision table for all members entered
          for(int i = 0; i<members; i++)
          {
              print();
              index++;
          }
          //makes a coppy of the sales numbers for indexing
         System.arraycopy(num, 0, sort, 0, members);
      //sorts values to make them easier to work with from smallest to largest in the array
          Arrays.sort(num);
          //////////////////////////////////////////////////////////////////////
          //////////////////////////////////////////////////////////////////////
          //loop takes newly sorted array and compares sales between salesmen and prints highest to lowest value as well as sales needed for 1st place
           for (int i = 10; i > 10-members; i--)//runs through array highest value to lowest
         {
             for(int j =0; j<= members; j++)//sets loop running for index refferencing
             {
             if(sort[j] == num[i])//references the index to correctly label sales to salesmen
             {
                 if(i ==10)
                 {//prints seller with most sales and value
                 System.out.println("The Seller with the most sales is " + salesMan[j] + " with sales totaling $" + num[i]);
                 }
                 else
                 {//prints sellers and what would have been needed for first place
                System.out.println( salesMan[j] + "'s sales totaled $" + num[i] + " leaving them $" +(num[10]-num[i]) + " short of first place");
                 }
             }
             }
        }
   
   
    }
    public static void print()
    {
        
    System.out.println ("\n--------------------------------------------------");
    System.out.println (salesMan[index]+"'s total salary plus commission $" + total[index]);
    System.out.println ("--------------------------------------------------");
    System.out.println ("\n--------------------------------------------------");
    System.out.println ("Potential anual compensation" );
    System.out.println ("--------------------------------------------------");
    for (double i = num[index]; i <= num[index]*1.5; i = i +20000)
    {
    System.out.println ("--------------------------------------------------");
    System.out.println (":::Sales $" + i + ":::" + "::: Total $" +Calculate(i,salary,target)+ ":::");
    System.out.println ("--------------------------------------------------");
    /////////////////////////////////////////////////////////////////////////  
    /////////////////////////////////////////////////////////////////////////  
    }
    //////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    //prints the total as well as a table displaying other possible commissions.
    
    }
    public static void error() // is used for error handeling of invalid responses
    {
        System.out.println("User canceled or didnt enter an acceptable value. terminating program");
        System.exit(0);
    }
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    //is used for calculating the final salary and returns the value
     public static double Calculate(double sales, double fixrate, double target)
    {
        //checks if the minimum sales were met for a commission
         double calculated = 0;
        if (sales < target*.8)
        {
            calculated = fixrate;
        }
        /////////////////////////////////////////////////////
        ////////////////////////////////////////////////////
        //Checks if a commission is going to be payed out, if they have reached the accelorated factor or not
        else if(sales >= target*.8 && sales <target)
        {
            calculated = sales*.15 + fixrate;
        }
        //////////////////////////////////////////////////
        //////////////////////////////////////////////////
        //handles the accelerated commission factor if one is needed
        else if (sales >= target)
        {
            double factor = sales/target;
            for (double i = 2; i<84;  )
            {
                if ( factor>=2)
                {
                    factor = sales/(target*i);
                    i= i+2;
                    
                }
                else
                {
                    calculated = fixrate + (sales*(i/100+.15));
                    i = 90;
                }
            }
        }
        //return whatever value is calculated
        return calculated;
    }
}
