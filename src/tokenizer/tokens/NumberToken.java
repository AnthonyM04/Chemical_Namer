package tokenizer.tokens;

public class NumberToken extends Token {
    public double value = -1;

    public NumberToken(double value) {
        this.value = value;
    }

    public String toString() {
        return "" + value;
    }
}