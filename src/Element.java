import java.util.ArrayList;

public final class Element {
    private int num;

    private String symbol;
    private String name;

    private double electronegativity;

    private ArrayList<Integer> oxidationStates;
    private String type;

    private int valenceElectrons;

    public Element(int num, String symbol, String name, double electronegativity, ArrayList<Integer> oxidationStates, String type, int valenceElectrons) {
        this.num = num;
        this.symbol = symbol;
        this.name = name;
        this.electronegativity = electronegativity;
        this.oxidationStates = oxidationStates;
        this.type = type;
        this.valenceElectrons = valenceElectrons;
    }

    public int getNum() {
        return num;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getElectronegativity() {
        return electronegativity;
    }

    public ArrayList<Integer> getOxidationStates() {
        return oxidationStates;
    }

    public String getType() {
        return type;
    }

    public int getValenceElectrons() {
        return valenceElectrons;
    }
}
