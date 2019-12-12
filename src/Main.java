import tokenizer.InvalidExpressionException;
import tokenizer.Tokenizer;
import tokenizer.tokens.NumberToken;
import tokenizer.tokens.Token;

public class Main {

    public static void main(String[] args) throws InvalidExpressionException {
        String in = "H";
        Tokenizer tokenizer = new Tokenizer(in);
        Token first = tokenizer.nextToken();
        if (first.toString().equals("H")) {
            System.out.println(acidCompound(tokenizer));
        }
    }

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
