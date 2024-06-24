
public class Produkt {
    private String name;
    private double preis;
    private int nummer;

    // Konstuktur vom produkt bekommt der Name von produkt mit preis
    public Produkt(String name, double preis, int nummer) {
        this.name = name;
        this.preis = preis;
        this.nummer = nummer;
    }

    // Produktsname zurückgeben
    public String getName() {
        return name;
    }

    // Produktspreis zurückgeben
    public double getPreis() {
        return preis;
    }

    // ProduktNummer zurückgeben
    public int getNummer() {
        return nummer;
    }

}
