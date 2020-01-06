import java.awt.font.NumericShaper;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        ArrayList<Element> elementList = readElements();

        Scanner in = new Scanner(System.in);

        while (true) {
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

            //TODO: Work on ionCompound and molecularCompound methods
            if (firstElement.element.getType().equals("metal")) {
                System.out.println(ionCompound(tokenizer, firstElement));
            } else {
                System.out.println(molecularCompound(tokenizer, firstElement));
            }
        }
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

            Element element = new Element(
                    num, symbol, name, oxidationStates,metalicCharacter);

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

    public static String ionCompound(Tokenizer tokenizer, ElementToken firstElement) {
        return "";
    }

    public static String molecularCompound(Tokenizer tokenizer, ElementToken firstElement) throws InvalidExpressionException, FileNotFoundException {
        StringBuilder name = new StringBuilder();

        if (!tokenizer.hasMoreTokens() || !(tokenizer.nextToken() instanceof ElementToken || tokenizer.hasMoreTokens())) {
            return firstElement.element.getName();
        }

        ElementToken secondElement = null;
        if (tokenizer.currentToken() instanceof NumberToken) {
            NumberToken firstAmount = (NumberToken) tokenizer.currentToken();
            name.append(firstAmount.prefix).append(firstElement.element.getName().toLowerCase());

            secondElement = (ElementToken) tokenizer.nextToken();
        }
        else {
            name.append(firstElement.element.getName());
            secondElement = (ElementToken) tokenizer.currentToken();
        }

        if (!tokenizer.hasMoreTokens()) {
            name.append(" ").append(secondElement.element.getIdeName());
        }
        else {
            NumberToken secondAmount = (NumberToken) tokenizer.nextToken();
            name.append(" ").append(secondAmount).append(secondElement.element.getIdeName().toLowerCase());
        }

        return name.toString();
    }
}
