package projekt;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Automat {
    private Map<Produkt, Integer> produkt;
    private float kontostand;
    private Produkt gewaehlteProdukt;

    public Automat() {
        /*
         * beim erzeugen vom Automat Kontostand auf 0 und produkte werden durch die
         * Hashmap eingesetzt
         */
        produkt = new HashMap<>();
        kontostand = 0;

    }

    public void produkteHinzufuegen(Produkt produkt, int menge) {
        // fals der produkt schon gegeben ist die neue menge wird addiert mit die alte
        if (this.produkt.containsKey(produkt)) {
            this.produkt.put(produkt, this.produkt.get(produkt) + menge);

        } else {
            // sonst wird der produkt als neue Produkt zum Automat hinzugefügt
            this.produkt.put(produkt, menge);
        }
    }

    public void geldEingeben(float muenzenEingeben) {
        // das Geld was man eingibt wird in die aktuellen Kontostand gespeichert
        kontostand += muenzenEingeben;
    }

    public void produktWaehlen(Produkt produkt) {
        gewaehlteProdukt = produkt;
        // Nachdem man den Produkt gewählt hat wirds geprüft ob das eingegebenes Geld
        // passend ist mit dem Produkts Preis
        if (kontostand >= produkt.getPreis()) {
            // Wenn es passend ist wird die Preis vom Produkt aus kontostand gezogen
            kontostand -= produkt.getPreis();

            
            // produkt wird abgegeben
            produktAbgeben(produkt);
        } else {
            // Sonst kommt ein Nachricht, dass man mehr Geld eingeben muss
            System.out.println("Bitte geben Sie mehr Geld ein");
        }
        // das gewählte Produkt wird dann in gewaehlteProdukt gespeichert für spätere
        // Nutzung
        
    }

    private void produktAbgeben(Produkt produkt) {
        int uebrigeMenge = 0;
        // Wenn der Produkt abegeben wird, wird die alte menge - 1 in die uebrigeMenge
        // gespeichert
        uebrigeMenge = this.produkt.get(gewaehlteProdukt) - 1;
        // dann wird die überige Menge als neue Menge gesetzt für den Produkt
        this.produkt.put(gewaehlteProdukt, uebrigeMenge);
        


    }

    public double rueckGeld() {
        double rueckGeld = kontostand;
        // Nach der Rechnung von Kontostand - der Preis vom Produkt, der rest wird in
        // rueckGeld gespeichert und kontostand wird wieder auf 0 gesetzt
        kontostand = 0;
        // Rückgabe vom überiges Geld
        return rueckGeld;

    }

    public Map<Produkt, Integer> getProduktListe() {
        // Alle Produkte mit die Menge werden zurück gegeben
        return produkt;
    }

    // Aktuelle Kontostand zurück geben
    public double getKontostand() {
        return kontostand;
    }

}
