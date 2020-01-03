package tokenizer.tokens;

import tokenizer.InvalidExpressionException;

import java.util.ArrayList;
import java.util.Arrays;

public class ElementToken extends Token {
    public String symbol;
    public String name;
    public int atomicNumber;
    public double electronegativity;
    public ArrayList<Integer> oxidationStates;
    public String metallicCharacter;



    public ElementToken(String symbol, String name, int atomicNumber,
                        double electronegativity, ArrayList<Integer> oxidationStates,
                        String metallicCharacter) {
        this.symbol = symbol;
        this.name = name;
        this.atomicNumber = atomicNumber;
        this.electronegativity = electronegativity;
        this.oxidationStates = oxidationStates;
        this.metallicCharacter = metallicCharacter;
    }

    @Override
    public String toString() {
        return symbol;
    }
    public void fullData() {
        System.out.printf("%s [%s, %d]; electronegativity: %f; %s ",
                name, symbol, atomicNumber, electronegativity, metallicCharacter);
        for (int i : oxidationStates) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
