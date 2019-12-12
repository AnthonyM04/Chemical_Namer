package tokenizer;

import tokenizer.tokens.*;

import java.util.NoSuchElementException;

public class Tokenizer {
    private char[] tokenStr = null;
    private int pos;

    public Tokenizer(String str) {
        tokenStr = str.toCharArray();
    }


    /** Returns next token in str, throws NoSuchElementException if no tokens remain*/

    public boolean hasMoreTokens() {
        skipSpaces();
        return pos < tokenStr.length;
    }

    public Token nextToken() throws InvalidExpressionException {
        skipSpaces();

        if (pos >= tokenStr.length) {
            throw new NoSuchElementException("No more tokens remaining");
        }

        if (Character.isDigit(tokenStr[pos])) {
            return readNumberToken();
        }
        else {
            return readElementToken();
        }
    }

    private void skipSpaces() {
        while (pos < tokenStr.length && Character.isSpaceChar(tokenStr[pos])) {
            pos++;
        }
    }

    private NumberToken readNumberToken () {
        int val = 0;

        while(pos < tokenStr.length && tokenStr[pos] >= '0' && tokenStr[pos] <= '9') {
            int a = tokenStr[pos] - '0';
            val = val * 10 + a;
            pos++;
        }

        return new NumberToken(val);
    }

    private ElementToken readElementToken() throws InvalidExpressionException {
        StringBuilder s = new StringBuilder();
        if (!Character.isUpperCase(tokenStr[pos])) {
            throw new InvalidExpressionException("Unknown character \"" + tokenStr[pos] + "\" at location " + pos);
        }
        s.append(tokenStr[pos++]);

        while(pos < tokenStr.length && !Character.isSpaceChar(tokenStr[pos]) && Character.isLowerCase(tokenStr[pos])) {
            s.append(tokenStr[pos++]);
        }

        return new ElementToken(s.toString());
    }
}
