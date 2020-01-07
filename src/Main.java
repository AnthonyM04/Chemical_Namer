import java.awt.font.NumericShaper;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        ArrayList<Element> elementList = readElements(); //Reads elements into Element objects in the array list.

        Scanner in = new Scanner(System.in);
        while (true) { //Runs loop infinitly FOR NOW, so testing can be done without restarting program
            Tokenizer tokenizer = new Tokenizer(in.nextLine(), elementList);

            Token first = tokenizer.nextToken();
            if (first instanceof NumberToken) {
                throw new InvalidExpressionException("Cannot start chemical with number \"" + first.toString() + "\"");
            }
            ElementToken firstElement = (ElementToken) first;

            /* I honestly have no idea how we are going to check for acidic compounds
            if (firstElement.toString().equals("H")) {
                System.out.println(acidCompound(tokenizer));
            }
            */

            /*
            if (firstElement.element.getType().equals("metal")) {
                System.out.println(ionCompound(tokenizer, firstElement));
            } else { */
                System.out.println(molecularCompound(tokenizer, firstElement)); //molecularCompound returns the proper name for molecular and ionic compounds
            //}
        }
    }

    public static ArrayList<Element> readElements() throws Exception { //Reads chemicalProperties.csv and builds a list of element objects from it
        Scanner in = new Scanner(new File("data/chemicalProperties.csv"));
        in.nextLine(); //First line of file is heading, not needed

        ArrayList<Element> elementList = new ArrayList<>(); //Holds all element objects created from file

        //Compared to when checking element type
        ArrayList<String> nonmetals = new ArrayList<>();
            nonmetals.add("nonmetal");
            nonmetals.add("noble gas");
            nonmetals.add("halogen");

        //Compared to when checking element type
        ArrayList<String> metals = new ArrayList<>();
            metals.add("alkali metal");
            metals.add("alkaline earth metal");
            metals.add("metal");
            metals.add("transition metal");
            metals.add("lanthanoid");
            metals.add("actinoid");
            metals.add("post-transition metal");

        // Loops through entire file, reading data from every line (which corresponds to every element)
        while (in.hasNextLine()) {
            // Splits line into a string list based on commas
            int location = 0;
            String[] values = in.nextLine().split(",");

            // Gets atomic number, symbol, and basic name
            int num = Integer.parseInt(values[location++]);
            String symbol = values[location++];
            String name = values[location++];

            //Gets electronegativity of element. Not used right now, but may be implemented
            location += 3;
//            double electronegativity = -1;
//
//            if (!values[location].equals("")) {
//                electronegativity = Double.parseDouble(values[location]);
//            }
            location++;

            //Gets all (positive) oxidation states of element. Ignores negative values because we don't care about them
            //I honestly can't comment this code better because I forget how it works
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


            //Determines whether the element is a metal or not
            location+=6;
            String metalicCharacter = "";
            if (metals.contains(values[location])) {
                metalicCharacter += "metal";
            }
            else if (nonmetals.contains(values[location])) {
                metalicCharacter += "nonmetal";
            }


            //Adds element with all information to array
            Element element = new Element(num, symbol, name, oxidationStates, metalicCharacter);
            elementList.add(element);
        }

        return elementList;
    }

    // TODO: Create functions for different compound types
    public static String acidCompound(Tokenizer tokenizer) throws InvalidExpressionException, FileNotFoundException {
        int hAmount = 1;
        Token t = tokenizer.nextToken();
        if (t instanceof NumberToken) {
            hAmount = Integer.parseInt(t.toString());
            t = tokenizer.nextToken();
        }

        return "";
    }

    /*
    public static String ionCompound(Tokenizer tokenizer, ElementToken firstElement) {
        return "";
    }
    */

    /**
     * Takes in the tokenizer for the chemical and the first Element Token. Needs inputs separated as main method reads the first token as well
     * Returns the name based on the tokenizer and first Element. Currently only works for mono- and di-elemental molecules.
     * @throws InvalidExpressionException
     * @throws FileNotFoundException
     */
    public static String molecularCompound(Tokenizer tokenizer, ElementToken firstElement) throws InvalidExpressionException, FileNotFoundException {
        StringBuilder name = new StringBuilder();

        // Simply returns the first element if it is the only kind in the formula
        if (!tokenizer.hasMoreTokens() || !(tokenizer.nextToken() instanceof ElementToken || tokenizer.hasMoreTokens())) {
            return firstElement.element.getName();
        }

        // Deals with first element and number, and gets second element
        ElementToken secondElement = null;
            //Case for a nonmetal if there is a number of first element
        if (tokenizer.currentToken() instanceof NumberToken && firstElement.element.getType().equals("nonmetal")) {
            NumberToken firstAmount = (NumberToken) tokenizer.currentToken();
            name.append(firstAmount.prefix).append(firstElement.element.getName().toLowerCase());

            secondElement = (ElementToken) tokenizer.nextToken();
        }
            //Case for a metal if there is a number of first element
        else if (tokenizer.currentToken() instanceof NumberToken && firstElement.element.getType().equals("metal")) {
            name.append(firstElement.element.getName());
            secondElement = (ElementToken) tokenizer.nextToken();
        }
            //Case if first element is unnumbered
        else {
            name.append(firstElement.element.getName());
            secondElement = (ElementToken) tokenizer.currentToken();
        }

        //Deals with second element
            //Case if second element is unnumbered
        if (!tokenizer.hasMoreTokens()) {
            name.append(" ").append(secondElement.element.getIdeName());
        }
            //Case if second element is numbered
        else /*if (firstElement.element.getType().equals("nonmetal"))*/ {
            NumberToken secondAmount = (NumberToken) tokenizer.nextToken();
            name.append(" ").append(secondAmount).append(secondElement.element.getIdeName().toLowerCase());
            //TODO: Work on polyatomic ions
        }
        /*else { //The second element of the molecule or ion should NEVER be metal. Redundant if statement.
            name.append(" ").append(secondElement.element.getIdeName());
        }
        */

        return name.toString();
    }
}
