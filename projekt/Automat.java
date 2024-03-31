public class Automat {
    
    private String[] produkte = new String[5];
    private double[] preis = new double[5];
    private int[] menge = new int[5];

    public void setProdukte(String[] produkte) {
        this.produkte = produkte;
    }

    public String[] getProdukte() {
        int zahl = 1;
        for (int i = 0; i < produkte.length; i++) {
            System.out.print(zahl + ". " + produkte[i] + "    ");
            zahl++;
        }
        return produkte;
    }

    public double[] getPreis() {
        return preis;
    }

    public void setPreis(double[] preis) {
        this.preis = preis;
    }

    public int[] getMenge() {
        return menge;
    }

    public void setMenge(int[] menge) {
        this.menge = menge;
    }

    public void displayProdukte() {
        getProdukte();
    }

    // funktion die der produkt mit der preis zeigt wenn man wählt
    public void produktGewaehlt(int wahl) {
        switch (wahl) {
            case 1:
                System.out.println(
                        "Sie haben " + produkte[0] + " gewählt \n" + produkte[0] + " kostet: " + preis[0] + "0 EUR");

                break;

            case 2:
                System.out.println(
                        "Sie haben " + produkte[1] + " gewählt \n" + produkte[1] + " kostet: " + preis[1] + " EUR");
                break;

            case 3:
                System.out.println(
                        "Sie haben " + produkte[2] + " gewählt \n" + produkte[2] + " kostet: " + preis[2] + " EUR");
                break;

            case 4:
                System.out.println(
                        "Sie haben " + produkte[3] + " gewählt \n" + produkte[3] + " kostet: " + preis[3] + " EUR");
                break;

            case 5:
                System.out.println(
                        "Sie haben " + produkte[4] + " gewählt \n" + produkte[4] + " kostet: " + preis[4] + " EUR");
                break;

            default:
                System.out.println("Falsche Eingabe");
                break;
        }
    }

}
