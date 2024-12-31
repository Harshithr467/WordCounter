import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Scanner;

/**
 * This program asks the user for a text file and makes a
 * HTML file that contains a table which lists all the words(in
 * alphabetical Order) that occurred and its count(the number of time that word has
 * occurred in the text file.
 * 
 * @author Harshith Revuru
 */
public class WordCounter {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private WordCounter() {
    }

    /**
     * Sorts in alphabetical Order.
     */
    @SuppressWarnings("unused")
    private static final class AlphabeticalOrder implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s1.toLowerCase().compareTo(s2.toLowerCase());
        }
    }

    

    public static void main(String[] args) {
        Scanner snc = new Scanner(System.in);

        System.out.print("Enter the name of the Text file(with location): ");
        String txtFile = snc.nextLine();

        System.out.print("Enter the name of the HTML file(with location folder): ");
        String htmlFile = snc.nextLine();

        BufferedReader input = null;
        PrintWriter output = null;

        try {
            // Open input file for reading.
            input = new BufferedReader(new FileReader(txtFile));
        } catch (IOException e) {
            System.err.println("Error reading from file: " + txtFile);
            e.printStackTrace();
            snc.close();
            return;
        } finally { // Close input stream
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    System.err.println("Error closing input stream.");
                    e.printStackTrace();
                }
            }
        }

        try {
            // Open output file for writing.
            output = new PrintWriter(new BufferedWriter(new FileWriter(htmlFile)));
        } catch (IOException e) {
            System.err.println("Error writing to file: " + htmlFile);
            e.printStackTrace();
            //e.getMessage();
        } finally { // Close output stream
            if (output != null) {
                output.close();
            }
        }
        
        snc.close();
    }
}