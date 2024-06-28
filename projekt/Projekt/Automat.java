
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class Automat {
    // Speichert die Produkte und ihre Mengen
    private Map<Produkt, Integer> produkte;
    // Aktueller Kontostand des Automaten
    private double kontostand;

    public Automat() {
        /*
         * beim erzeugen vom Automat Kontostand auf 0 und produkte werden durch die
         * Hashmap eingesetzt
         */
        produkte = new HashMap<>();
        kontostand = 0;

    }
    
    public void nachfuellen(int produktNummer, int menge) {
        Produkt produkt = getProduktByNummer(produktNummer);
        if (produkt != null && menge > 0 && menge <= 10) {
            int aktuelleMenge = produkte.get(produkt);
            produkte.put(produkt, Math.min(aktuelleMenge + menge, 10));
        }
    }
    // Fügt Produkte zum Automaten hinzu oder erhöht deren Menge
    public void produkteHinzufuegen(Produkt produkt, int menge) {
        if (this.produkte.containsKey(produkt)) {
            // Wenn das Produkt bereits existiert, erhöhe die Menge
            this.produkte.put(produkt, this.produkte.get(produkt) + menge);

        } else {
            // Füge ein neues Produkt hinzu
            this.produkte.put(produkt, menge);
        }
    }
    
    public void geldEingeben(double muenzen) {
        // das Geld was man eingibt wird in die aktuellen Kontostand gespeichert
        kontostand += muenzen;
    }

    // Wird aufgerufen um produkt zu kaufen
    public boolean produktKaufen(int produktNummer) {
        Produkt produkt = getProduktByNummer(produktNummer);
        
        if (produkt != null && produkte.get(produkt) > 0 && kontostand >= produkt.getPreis()) {
            // Wenn das Produkt verfügbar ist und genug Geld eingezahlt wurde
            int aktuelleMenge = produkte.get(produkt);
            produkte.put(produkt, aktuelleMenge - 1);
            kontostand -= produkt.getPreis();

            return true;
        }
        return false;
    }
    /********************** Rückgabe ********************/

    // Gibt den aktuellen Kontostand zurück und setzt ihn auf 0
    public double rueckgabe() {
        double rueckgabeKontostand = kontostand;
        kontostand = 0;
        return rueckgabeKontostand;
    }
    /********************** Anzeige********************/
    // preise Anzeigen
    public double preisAnzeigen(int zahl) {
        Produkt produkt = getProduktByNummer(zahl);
        double preis = produkt.getPreis();
        return preis;
    }

    public int getMenge(int zahl){
        for (Entry<Produkt, Integer> p: produkte.entrySet()){
            zahl = p.getValue();
                
            
        }
        return zahl;
    }



   /********************** Findet das Produkt, anhand der Nummer ********************/
    public Produkt getProduktByNummer(int produktNummer) {
        for (Produkt p : produkte.keySet()) {
            if (p.getNummer() == produktNummer) {
                return p;
            }
        }
        return null;
    }

    public Map<Produkt, Integer> getProduktListe() {
        // Alle Produkte mit die Menge werden zurück gegeben
        return produkte;
    }

    // Aktuellen Kontostand zurückgeben
    public double getKontostand() {
        return kontostand;
    }

}
