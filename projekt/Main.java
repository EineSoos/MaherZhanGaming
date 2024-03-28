import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] getraenke = { "Cola", "Fanta", "Sprite", "Wasser", "Halal Bier" };
        double[] preise = { 1.30, 1.49, 1.19, 0.99, 1.49 };
        int[] menge = { 10, 10, 10, 10, 10 };
        int wahl;
        String zweiterWahl;
        boolean bestaetigen = false;

        Automat automat1 = new Automat();
        automat1.setProdukte(getraenke);
        automat1.setPreis(preise);
        automat1.setMenge(menge);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Herzlich Willkommen, bitte wählen sie Ihr Produkt: ");
        System.out.println();
        automat1.displayProdukte();
        System.out.print("\n" + "\n" + "Wählen Sie hier ein Zahl: ");
        wahl = scanner.nextInt();
        automat1.produktGewaehlt(wahl);
        System.out.println("Wollen sie etwas weiters bestellen?");
        System.out.print("bitte beantowrten Sie mit 'ja' oder 'nein': ");
        zweiterWahl = scanner.nextLine();
        
        //fals man noch etwas bestellen will
        if (zweiterWahl == "ja") {
            bestaetigen = true;
        }

        while (bestaetigen == true) {
            System.out.println();
            automat1.displayProdukte();
            System.out.print("\n" + "\n" + "Wählen Sie hier ein Zahl: ");
            wahl = scanner.nextInt();
            automat1.produktGewaehlt(wahl);
            System.out.print("Wollen sie etwas weiters bestellen?\nbitte beantowrten Sie mit 'ja' oder 'nein': ");
            zweiterWahl = scanner.nextLine();

            if (zweiterWahl == "nein") {
                bestaetigen = false;
                
            }

        }

    }

}
