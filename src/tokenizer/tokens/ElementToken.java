package tokenizer.tokens;

import tokenizer.InvalidExpressionException;

import java.util.ArrayList;
import java.util.Arrays;

public class ElementToken extends Token {
    public String value = "";
    /*
    public static String[] elementList = {"H", "He",
            "Li", "Be", "B", "C", "N", "O", "F", "Ne",
    "Na", "Mg", "Al", "Si", "P", "S", "Cl", "Ar",
            "K", "Ca", "Sc", "Ti", "V", "Cr", "Mn", "Fe", "Co", "Ni", "Cu", "Zn", "Ga", "Ge", "As", "Se", "Br", "Kr",
    "Rb", "Sr", "Y", "Zr", "Nb", "Mo", "Tc", "Ru", "Rh", "Pd", "Ag", "Cd", "In", "Sn", "Sb", "Te", "I", "Xe",
            "Cs", "Ba", "La", "Ce", "Pr", "Nd", "Pm", "Sm", "Eu", "Gd", "Tb", "Dy", "Ho", "Er", "Tm", "Yb", "Lu", "Hf", "Ta", "W", "Re", "Os", "Ir", "Pt", "Au", "Hg", "Tl", "Pb", "Bi", "Po", "At", "Rn"};
    */
    public ElementToken(String element) { // Removed the check; its handled in Tokenizer instead
        /*
        boolean bad = true;
        for (String s : elementList) {
            if (element.equals(s)) {
                bad = false;
                break;
            }
        }
        if (bad) {
            throw new InvalidExpressionException("Illegal element " + element);
        }
         */
        value = element;
    }

    @Override
    public String toString() {
        return value;
    }
}
