import java.io.FileNotFoundException;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.NoSuchElementException;
//import java.util.Scanner;

public class Tokenizer {
    private char[] tokenStr = null;
    private int pos;
    // private HashMap<String, Element> elementHashMap = new HashMap<>();
    private ArrayList<Element> elementList;
    private ArrayList<String> eleSymbolList = new ArrayList<>();

    private Token lastToken;

    public Tokenizer(String str, ArrayList<Element> elementList) throws FileNotFoundException {
        tokenStr = str.toCharArray();
        this.elementList = elementList;
        for (Element e : elementList) {
            eleSymbolList.add(e.getSymbol());
            //elementHashMap.put(e.toString(), e);
        }


        // Scanner in = new Scanner(new File("data/chemicalProperties.csv"));
        // in.nextLine();
        /*
        while (in.hasNextLine()) {
            String[] curLine = in.nextLine().split(",");
            elementHashMap.put(curLine[1].trim() ,
                    new ElementToken(curLine[1], curLine[2], Integer.parseInt(curLine[0]), curLine[3],
                            curLine[5], Double.parseDouble(curLine[6]),
                            Integer.parseInt(curLine[7]), Integer.parseInt(curLine[10]),
                            Integer.parseInt(curLine[11])));





            // This makes the element HashMap (Symbol -> Name)
            // We may need to make (Name -> Symbol) but that's easy
        }
         */
    }


    /** Returns next token in str, throws NoSuchElementException if no tokens remain*/

    public boolean hasMoreTokens() {
        skipSpaces();
        return pos < tokenStr.length;
    }

    public Token nextToken() throws InvalidExpressionException, FileNotFoundException {
        skipSpaces();

        if (pos >= tokenStr.length) {
            throw new NoSuchElementException("No more tokens remaining");
        }

        if (Character.isDigit(tokenStr[pos])) {
            return readNumberToken();
        }
        else if (Character.isLetter(tokenStr[pos])) {
            return readElementToken();
        }
        else {
            throw new InvalidExpressionException("Unknown character " + tokenStr[pos] + " at position " + pos);
        }
    }

    private void skipSpaces() { // This function isn't really useful right now
        while (pos < tokenStr.length && !(Character.isUpperCase(tokenStr[pos]) || Character.isDigit(tokenStr[pos]))) {
            pos++;
        }

    }

    private NumberToken readNumberToken () throws FileNotFoundException {
        int val = 0;

        while(pos < tokenStr.length && tokenStr[pos] >= '0' && tokenStr[pos] <= '9') {
            int a = tokenStr[pos] - '0';
            val = val * 10 + a;
            pos++;
        }

        NumberToken num = new NumberToken(val);
        lastToken = num;
        return num;
    }

    private ElementToken readElementToken() throws InvalidExpressionException {
        StringBuilder element = new StringBuilder(); // initalizes with cap = 16
        element.append(tokenStr[pos]);
        try { // Makes sure loop doesn't run off of String length
            while (pos+1 < tokenStr.length && Character.isLowerCase(tokenStr[pos+1])) {
                pos++;
                element.append(tokenStr[pos]);
            }
        } catch (IndexOutOfBoundsException e1) {
            throw new InvalidExpressionException("Ran to end of the line attempting to identify " +
                    "element " + element.toString());
        }
        if (!eleSymbolList.contains(element.toString())) {
            throw new InvalidExpressionException("Element " + element.toString() + " is not recognized.");
        }
        pos++;

        ElementToken elementToken = new ElementToken(element.toString(), elementList);
        lastToken = elementToken;
        return elementToken;

        //return elementHashMap.get(element.toString());



        /*
        StringBuilder s = new StringBuilder();
        if (!Character.isUpperCase(tokenStr[pos])) {
            throw new InvalidExpressionException("Unknown character \"" + tokenStr[pos] + "\" at location " + pos);
        }
        s.append(tokenStr[pos++]);

        while(pos < tokenStr.length && !Character.isSpaceChar(tokenStr[pos]) && Character.isLowerCase(tokenStr[pos])) {
            s.append(tokenStr[pos++]);
        }

        return new ElementToken(s.toString());

        */
    }

    public Token currentToken() {
        if (pos == 0) {
            throw new IllegalStateException("Tokenizer has not read a token");
        }
        return lastToken;
    }
}
