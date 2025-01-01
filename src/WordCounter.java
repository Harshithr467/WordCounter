import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

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

    /**
     * a String of all types of separators.
     */
    @SuppressWarnings("unused")
    private static final String SEPARATORS = " \t\n\r,-.!?[]';:/()" + "@#$%^&*_=+"
            + "1234567890";

    

    public static Set<Character> getSeparator(String str){
        Set<Character> separators = new HashSet<>();

        for (int i = 0; i < str.length(); i++) {
            separators.add(str.charAt(i));
        }
        return separators;
    }

    public static void generateHTMLPage (PrintWriter out, BufferedReader in, String inputFile, HashMap<String,Integer> wordsAndCounter, Queue<String> words){
        assert out != null : "Violation of: out is not null";
        
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Words Counted in " + inputFile + "</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Words Counted in " + inputFile + "</h2>");
        out.println("<hr />");
        out.println("<table border=\"1\">");
        out.println("<tr>");
        out.println("<th>Words</th>");
        out.println("<th>Counts</th>");
        out.println("</tr>");

        // iterate through the words in sorted queue and print them in table.
        for (String s : words) {
            out.println("<tr>");
            out.println("<td>" + s + "</td>");
            out.println("<td>" + wordsAndCounter.get(s) + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }


    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}. Note: This method is taken from my CSE 2221
     * class + improved upon.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    public static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        char firstC = text.charAt(position);
        /**
         * Used string builder because eclipse gave me a bug notification saying
         * it was better to use stringbuilder rather than string.
         */
        StringBuilder nextWordOrSeparator = new StringBuilder();
        nextWordOrSeparator.append(firstC);
        int i = position + 1;

        if (!separators.contains(firstC)) {
            while (i < text.length() && !separators.contains(text.charAt(i))) {
                nextWordOrSeparator.append(text.charAt(i));
                i++;
            }
        } else {
            while (i < text.length() && separators.contains(text.charAt(i))) {
                nextWordOrSeparator.append(text.charAt(i));
                i++;
            }
        }
        return nextWordOrSeparator.toString();
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