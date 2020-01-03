import tokenizer.InvalidExpressionException;
import tokenizer.tokens.NumberToken;
import tokenizer.tokens.Token;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InvalidExpressionException, FileNotFoundException, Exception {
        ArrayList<Element> elementList = readElements();

        Scanner in = new Scanner(System.in);
        Tokenizer tokenizer = new Tokenizer(in.nextLine(), elementList);
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

    public static ArrayList<Element> readElements() throws Exception { //Reads chemicalProperties.csv and builds a list of element objects from it
        Scanner in = new Scanner(new File("data/chemicalProperties.csv"));
        in.nextLine();

        ArrayList<Element> elementList = new ArrayList<>();

        ArrayList<String> nonmetals = new ArrayList<String>();
        nonmetals.add("nonmetal");
        nonmetals.add("noble gas");
        nonmetals.add("halogen");

        ArrayList<String> metals = new ArrayList<>();
        metals.add("alkali metal");
        metals.add("alkaline earth metal");
        metals.add("metal");
        metals.add("transition metal");
        metals.add("lanthanoid");
        metals.add("actinoid");
        metals.add("post-transition metal");

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
//            double electronegativity = -1;
//
//            if (!values[location].equals("")) {
//                electronegativity = Double.parseDouble(values[location]);
//            }
            location++;

            //Gets all (positive) oxidation states of element. Ignores - numbers because we don't care about them
            location += 5;
            ArrayList<Integer> oxidationStates = new ArrayList<>();
            if (!values[location].equals("") && values[location].charAt(0) == '\"') {
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
            }
            else if (!values[location].equals("")) {
                oxidationStates.add(Integer.parseInt(values[location++]));
            }

            location+=6;
            String metalicCharacter = "";

            if (metals.contains(values[location])) {
                metalicCharacter += "metal";
            }
            else if (nonmetals.contains(values[location])) {
                metalicCharacter += "nonmetal";
            }

            //TODO: Fix this
            Element element = new Element(
                    symbol, name, num,
                    oxidationStates, metalicCharacter
                    );
            elementList.add(element);
        }

        for (Element e : elementList) {
            e.fullData();
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
