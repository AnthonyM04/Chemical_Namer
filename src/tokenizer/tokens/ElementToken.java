package tokenizer.tokens;

import tokenizer.InvalidExpressionException;

import java.util.ArrayList;
import java.util.Arrays;

public class ElementToken extends Token {
    public String symbol;
    public String name;
    public int atomicNumber;
    public double atomicMass;
    public String electronConfig;
    public double electonegativity;
    public int atomicRadius;
    public int ionizationEnergy;
    public int electronAffinity;


    public ElementToken(String symbol, String name, int atomicNumber, String atomicMass, String electronConfig, double electonegativity, int atomicRadius, int ionizationEnergy, int electronAffinity) {
        this.symbol = symbol;
        this.name = name;
        this.atomicNumber = atomicNumber;

        char[] massParse = atomicMass.toCharArray();
        StringBuilder mass = new StringBuilder();
        for (char n : massParse) {
            if (Character.isDigit(n) || n == '.')
                mass.append(n);
        }
        this.atomicMass = Double.parseDouble(mass.toString());

        this.electronConfig = electronConfig;
        this.electonegativity = electonegativity;
        this.atomicRadius = atomicRadius;
        this.ionizationEnergy = ionizationEnergy;
        this.electronAffinity = electronAffinity;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
