import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] getraenke = { "Cola", "Fanta", "Sprite", "Wasser", "Halal Bier" };
        double[] preise = { 1.30, 1.49, 1.19, 0.99, 1.49 };
        int[] menge = { 10, 10, 10, 10, 10 };
        int wahl;
        String zweiterWahl;
        boolean bestaetigen = false;

        Automat automat1 = new Automat();// Automat erzeugt
        automat1.setProdukte(getraenke);// Getränke
        automat1.setPreis(preise);// preise
        automat1.setMenge(menge);// menge fest gesetzt

        // Eingabe vom wahl
        Scanner scanner = new Scanner(System.in);
        // Eingabe von zweiterWahl
        Scanner scanner1 = new Scanner(System.in);

        System.out.println("Herzlich Willkommen, bitte wählen sie Ihr Produkt: ");
        System.out.println();
        automat1.displayProdukte(); // Anzeige Produkte
        System.out.print("\n" + "\n" + "Wählen Sie hier ein Zahl: ");

        wahl = scanner.nextInt();
        //eine schleife die sich immer wiederholt wenn man auf 0 oder zahl >5 schreibt
        if (wahl == 0 || wahl > 5) {
            do {
                automat1.produktGewaehlt(wahl); // gibt zurück was man gewählt hat mit der preis davon
                automat1.displayProdukte(); // Anzeige Produkte
                System.out.println();
                System.out.print("Bitte geben Sie ein Zahl zwischen 1 und 5: ");
                wahl = scanner.nextInt();

            } while (wahl == 0 || wahl > 5);
        }

        do {
            automat1.produktGewaehlt(wahl); // gibt zurück was man gewählt hat mit der preis davon
            System.out.println("Wollen sie etwas weiters bestellen?");
            System.out.print("bitte beantowrten Sie mit 'ja' oder 'nein': ");
            zweiterWahl = scanner1.nextLine();
            // fals man noch etwas bestellen will
            if (zweiterWahl.equalsIgnoreCase("ja")) {
                bestaetigen = true;
            } 
            // wiederholung von Eingabe
            while(bestaetigen ==true){
                System.out.println();
                automat1.displayProdukte();
                System.out.print("\n" + "\n" + "Wählen Sie hier ein Zahl: ");
                wahl = scanner.nextInt();
                if (wahl == 0 || wahl > 5) {
                    
                        automat1.produktGewaehlt(wahl); // gibt zurück was man gewählt hat mit der preis davon
                        automat1.displayProdukte(); // Anzeige Produkte
                        System.out.println();
                        System.out.println("Bitte geben Sie ein Zahl zwischen 1 und 5");
                        wahl = scanner.nextInt();

                   
                }
                automat1.produktGewaehlt(wahl);
                System.out.print("Wollen sie etwas weiters bestellen?\nbitte beantowrten Sie mit 'ja' oder 'nein': ");
                zweiterWahl = scanner1.nextLine();

                if (zweiterWahl.equalsIgnoreCase("nein")) {
                    bestaetigen = false;
                    
                }
                System.out.println(bestaetigen);
            }
            
        } while (wahl != 0 || wahl < 6);

    }

}
