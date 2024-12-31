import java.util.Comparator;

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
        
    }
}