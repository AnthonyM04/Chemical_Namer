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


    public Element(int num, String symbol, String name,
                   ArrayList<Integer> oxidationStates, String type
                   ) throws FileNotFoundException {
        this.num = num;
        this.symbol = symbol;
        this.name = name;
        this.oxidationStates = oxidationStates;
        this.type = type;

        if (type.equals("nonmetal") && num < 54 && num != 36
                                                && num != 10
                                                && num != 18) {
            // Eliminates everything above Iodine (>54) and Kyrpton (36) and Neon (10) and Argon (18)
            ideName = getIde();
        }
    }

    private String getIde() throws FileNotFoundException {
        Scanner in = new Scanner(new File("data/ideList.csv"));

        String lineOn = in.nextLine();

        while (in.hasNextLine() && !lineOn.split(",")[0].equals(symbol)) {
            lineOn = in.nextLine();
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
        StringBuilder returnString = new StringBuilder();
        returnString.append(String.format("%s - %d: %s %s ", symbol, num, name, type));
        for (int i : oxidationStates)
            returnString.append(String.format("%d ", i));
        returnString.append(ideName);
        return returnString.toString();
    }
}
