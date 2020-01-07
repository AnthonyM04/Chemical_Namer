import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NumberToken extends Token {
    public int value = -1;
    public String prefix;

    public NumberToken(int value) throws FileNotFoundException {
        this.value = value;

        //Gets prefix from numPrefixList.csv
        if (value <= 10) { //Only from 0-10
            Scanner in = new Scanner(new File("data/numPrefixList.csv"));
            String[] prefixes = in.nextLine().split(",");
            prefix = prefixes[value - 1];
        }
        else { //Otherwise, just make prefix like "11-"
            prefix = "" + value + "-";
        }
    }

    public String toString() {
        return prefix;
    }
}