package tokenizer.tokens;

import tokenizer.InvalidExpressionException;
import tokenizer.Tokenizer;

public class TokenizerTester {
    public static void main(String[] args) throws InvalidExpressionException {
        String input = "Al3O";

        Tokenizer t = new Tokenizer(input);
        while (t.hasMoreTokens()) {
            System.out.println("|" + t.nextToken() + "|");
        }
    }
}
