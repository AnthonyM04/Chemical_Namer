//TODO: Fix Element Token

package tokenizer.tokens;

import tokenizer.InvalidExpressionException;

import java.util.ArrayList;
import java.util.Arrays;

public class ElementToken extends Token {
    public String symbol;




    public ElementToken(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
