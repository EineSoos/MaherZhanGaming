import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        String[] getraenke = { "Cola", "Fanta", "Sprite", "Wasser", "Halal Bier" };
        double[] preise = { 1.30, 1.49, 1.19, 0.99, 1.49 };
        int[] menge = { 10, 10, 10, 10, 10 };
        int wahl;
        String zweiterWahl;
        boolean bestaetigen = false;
        String zeilenAbbruch = "--------------------------------------------";
        
        Automat automat1 = new Automat();// Automat erzeugt
        automat1.setProdukte(getraenke);// Getränke
        automat1.setPreis(preise);// preise
        automat1.setMenge(menge);// menge fest gesetzt

        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);

        System.out.println("Herzlich Willkommen, bitte wählen sie Ihr Produkt: ");
        System.out.println(zeilenAbbruch);
        automat1.displayProdukte(); // Anzeige Produkte
        System.out.print("\n" + "\n" + "Wählen Sie hier ein Zahl: ");
        // Eingabe vom wahl
        wahl = scanner.nextInt();
        System.out.println(zeilenAbbruch);

        // eine schleife die sich immer wiederholt wenn man auf 0 oder zahl >5 drückt
        if (wahl == 0 || wahl > 5) {
            do {
                automat1.produktGewaehlt(wahl); // gibt zurück was man gewählt hat mit der preis davon
                System.out.println(zeilenAbbruch);
                automat1.displayProdukte(); // Anzeige Produkte
                System.out.println("\n");
                System.out.print("Bitte geben Sie ein Zahl zwischen 1 und 5: ");
                wahl = scanner.nextInt();
                System.out.println(zeilenAbbruch);

            } while (wahl == 0 || wahl > 5);
        }

        if (wahl != 0 || wahl < 6) {
            automat1.produktGewaehlt(wahl); // gibt zurück was man gewählt hat mit der preis davon
            System.out.println(zeilenAbbruch);
            System.out.println("Wollen sie etwas weiters bestellen?");
            System.out.print("bitte beantowrten Sie mit 'ja' oder 'nein': ");
            zweiterWahl = scanner1.nextLine();// Eingabe von zweiterwahl
            // fals man noch etwas bestellen will
            if (zweiterWahl.equalsIgnoreCase("ja")) {
                bestaetigen = true;
            }

        }
        // wiederholung von Eingabe
        while (bestaetigen == true) {
            System.out.println(zeilenAbbruch);
            automat1.displayProdukte();
            System.out.print("\n" + "\n" + "Wählen Sie hier ein Zahl: ");
            wahl = scanner.nextInt();
            if (wahl == 0 || wahl > 5) {
                System.out.println(zeilenAbbruch);
                automat1.produktGewaehlt(wahl); // gibt zurück was man gewählt hat mit der preis davon
                System.out.println(zeilenAbbruch);
                automat1.displayProdukte(); // Anzeige Produkte
                System.out.println("\n");
                System.out.print("Bitte geben Sie ein Zahl zwischen 1 und 5: ");
                wahl = scanner.nextInt();
            }
            System.out.println(zeilenAbbruch);
            automat1.produktGewaehlt(wahl);
            System.out.println(zeilenAbbruch);
            System.out.print("Wollen sie etwas weiters bestellen?\nbitte beantowrten Sie mit 'ja' oder 'nein': ");
            zweiterWahl = scanner1.nextLine();

            if (zweiterWahl.equalsIgnoreCase("nein")) {
                bestaetigen = false;

            }

        }

    }
}
