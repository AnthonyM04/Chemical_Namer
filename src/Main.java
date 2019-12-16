import tokenizer.InvalidExpressionException;
import tokenizer.Tokenizer;
import tokenizer.tokens.NumberToken;
import tokenizer.tokens.Token;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InvalidExpressionException, FileNotFoundException {
        Scanner in = new Scanner(System.in);
        Tokenizer tokenizer = new Tokenizer(in.nextLine());
        while (tokenizer.hasMoreTokens()) {
            System.out.println(tokenizer.nextToken());
        }
        /*
        Token first = tokenizer.nextToken();
        if (first.toString().equals("H")) {
            System.out.println(acidCompound(tokenizer));
        }
         */
    }

    public static ArrayList<Element> readElements(String file) throws FileNotFoundException { //Reads chemicalProperties.csv and builds a list of element objects
        Scanner in = new Scanner(new File("data/elementlist.csv"));
        in.nextLine();

        ArrayList<Element> elementList = new ArrayList<>();

        while (in.hasNextLine()) {

            // Splits line into a string list based on commas
            int location = 0;
            String[] values = in.nextLine().split(",");

            // Gets atomic number, symbol, and basic name
            int num = Integer.parseInt(values[location++]);
            String symbol = values[location++];
            String name = values[location++];

            //Gets electronegativity of element
            location += 3;
            double electronegativity = Double.parseDouble(values[location++]);

            //Gets all (positive) oxidation states of element
            location += 5;
            ArrayList<Integer> oxidationStates = new ArrayList<>();
            oxidationStates.add(Integer.parseInt(values[location++].substring(1)));
            while (values[location].charAt(values[location].length() -1) != '"') {
                oxidationStates.add(Integer.parseInt(values[location++]));
            }
            oxidationStates.add(Integer.parseInt(values[location].substring(0, values[location].length() -1)));
            for (int i = 0; i < oxidationStates.size(); i++) {
                if (oxidationStates.get(i) < 0) {
                    oxidationStates.remove(i--);
                }
            }

            location+=5;
            // TODO: Implement getting type of element (IE nonmetal or metal)

            // TODO: Finish building element object and add it to elements
        }

        return elementList;
    }

    // TODO: Create functions for different compound types
    public static String acidCompound(Tokenizer tokenizer) throws InvalidExpressionException {
        int hAmount = 1;
        Token t = tokenizer.nextToken();
        if (t instanceof NumberToken) {
            hAmount = Integer.parseInt(t.toString());
            t = tokenizer.nextToken();
        }

        return "";
    }
}
