package projekt;

public class Produkt {
    private String name;
    private double preis;

    // Konstuktur vom produkt bekommt der Name von produkt mit preis
    public Produkt(String name, double preis) {
        this.name = name;
        this.preis = preis;
    }

    // Produktsname zurückgeben
    public String getName() {
        return name;
    }

    // Produktspreis zurückgeben
    public double getPreis() {
        return preis;
    }

}
