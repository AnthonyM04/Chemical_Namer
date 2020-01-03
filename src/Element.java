import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public final class Element {
    private int num;

    private String symbol;
    private String name;
    private String ideName = "";

    private ArrayList<Integer> oxidationStates;
    private String type;

    private int valenceElectrons;

    public Element(int num, String symbol, String name, ArrayList<Integer> oxidationStates, String type, int valenceElectrons) throws FileNotFoundException {
        this.num = num;
        this.symbol = symbol;
        this.name = name;
        this.oxidationStates = oxidationStates;
        this.type = type;
        this.valenceElectrons = valenceElectrons;

        if (type.equals("nonmetal")) {
            ideName = getIde();
        }
    }

    private String getIde() throws FileNotFoundException {
        Scanner in = new Scanner(new File("data/ideList.csv"));

        String lineOn = in.nextLine();

        while (in.hasNextLine() && !lineOn.split(",")[0].equals(symbol)) {
            in.nextLine();
        }

        return lineOn.split(",")[1];
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


    public ArrayList<Integer> getOxidationStates() {
        return oxidationStates;
    }

    public String getType() {
        return type;
    }

    public String fullData() {
        return String.format("%s - %d: %s %s %s %d %s", symbol, num, name, type, oxidationStates, ideName);
    }
}
