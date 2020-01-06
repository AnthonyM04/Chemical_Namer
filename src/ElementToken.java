//TODO: Fix Element Token

import java.util.ArrayList;

public class ElementToken extends Token {
    public String symbol;
    public Element element;

    public ElementToken(String symbol, ArrayList<Element> elementList) {
        this.symbol = symbol;

        for (Element e : elementList) {
            if (e.getSymbol().equals(symbol)) {
                this.element = e;
                break;
            }
        }
    }

    @Override
    public String toString() {
        return symbol;
    }

    public boolean equals(Object o) {
        if (o instanceof ElementToken) {
            return this.symbol.equals(((ElementToken) o).symbol);
        }
        else if (o instanceof Element) {
            return this.symbol.equals(((Element) o).getSymbol());
        }
        else {
            return false;
        }
    }
}
