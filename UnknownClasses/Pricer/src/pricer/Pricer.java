/*
    Pricer Program Sep 29, 2016
    Your task is to write a program, Pricer, that analyzes such a log file.
    Pricer takes one command-line argument: target-size. Pricer then reads a 
    market data log on standard input. As the book is modified, Pricer prints 
    (on standard output) the total expense you would incur if you bought 
    target-size shares (by taking as many asks as necessary, lowest first), 
    and the total income you would receive if you sold target-size shares 
    (by hitting as many bids as necessary, highest first). Each time the income
    or expense changes, it prints the changed value.
 */
package pricer;

/**
 *
 * @author Christian Remwood
 * notes: 
 * 
 * Inputs
 * 
 * An order on the book should look like timestamp A order-id side price size
 * timestamp: is the time a message was generated in milliseconds
 * A: a literal string identifying this as an order on the book message
 * order-id: A unique string that subsequent "Reduce order" messages will use to modify this order
 * side: A "b" if this is buy order or a bid, or an "s"  if its a sell order or an ask
 * price: the limit price of this order
 * size: the size in shares of this order when initially sent to market
 * 
 * 
 * A Reduce order message should look like timestamp R order-id size
 * timestamp: same as above
 * R: a literal string identifying this as a reduce order message
 * order-id: the unique id signifying which order to be reduced
 * size: the amount by which to reduce the size of the order. 
 * if size is >= the existing size, the order is removed from the book
 * 
 * 
 * Outputs
 * 
 * Pricer output consists of one message per line and should look like timestamp action total
 * timestamp: same as above
 * action: A string "b" if this contains new expense to buy target size shares
 * or an "S" if this contains the new income for selling target size shares
 * total: the total expense if action is "b" or the total income if action is "s"
 */

//Needed Imports
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
public class Pricer {

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws IOException {
        //program variables
        boolean valid = false;
        int targetSize = 0;
        ArrayList b = new ArrayList();
        ArrayList s = new ArrayList();
        //setting up variables and array lists
        BufferedReader br = null;

        //setting up reader for user input
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        System.out.println("Please enter cmd \"For this demo the only cmd is 'target-size'\"");
        while(valid == false){
        if( "target-size".equals(reader.readLine())){
            valid = true;
            break;}
        else{
           System.out.println("that command was not valid, please try again."); }
        } 
        System.out.println("Please enter your target size");
        targetSize = Integer.parseInt(reader.readLine());
        System.out.println("perfect, now printing calculations based on pricer.in.");
        // TODO code application logic here
    }
    
}
