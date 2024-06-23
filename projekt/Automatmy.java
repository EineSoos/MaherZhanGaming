package project;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Automat {
    private Map<Produkt, Integer> produkte;
    private double kontostand;

    public Automat() {
        /*
         * beim erzeugen vom Automat Kontostand auf 0 und produkte werden durch die
         * Hashmap eingesetzt
         */
        produkte = new HashMap<>();
        kontostand = 0;

    }

    public void produkteHinzufuegen(Produkt produkt, int menge) {
        // fals der produkt schon gegeben ist die neue menge wird addiert mit der alte
        if (this.produkte.containsKey(produkt)) {
            this.produkte.put(produkt, this.produkte.get(produkt) + menge);

        } else {
            // sonst wird der produkt als neue Produkt zum Automat hinzugefügt
            this.produkte.put(produkt, menge);
        }
    }

    public void geldEingeben(double muenzen) {
        // das Geld was man eingibt wird in die aktuellen Kontostand gespeichert
        kontostand += muenzen;
    }

    public boolean produktKaufen(int produktNummer) {
            // Ruft das Produkt anhand der Produktnummer ab
        Produkt produkt = getProduktByNummer(produktNummer);
            
        // Überprüft, ob das Produkt existiert, ob es noch vorrätig ist und ob genügend Guthaben vorhanden ist
        if (produkt != null && produkte.get(produkt) > 0 && kontostand >= produkt.getPreis()) {
            
            // Reduziert den Lagerbestand des Produkts um eins
            produkte.put(produkt, produkte.get(produkt) - 1);
                    
            // Verringert den Kontostand um den Preis des Produkts
            kontostand -= produkt.getPreis();
                    
            // Gibt true zurück, um anzuzeigen, dass der Kauf erfolgreich war
            return true;
        }
            
        // Gibt false zurück, wenn der Kauf nicht erfolgreich war
        return false;
    }

    private Produkt getProduktByNummer(int produktNummer) {
            
        // Durchläuft die Menge der Produkte im Automaten
        for (Produkt p : produkte.keySet()) {
                    
            // Überprüft, ob die Produktnummer mit der gesuchten Nummer übereinstimmt
            if (p.getNummer() == produktNummer) {
                            
                // Gibt das gefundene Produkt zurück
                return p;
            }
        }
            
        // Gibt null zurück, wenn kein Produkt mit der angegebenen Nummer gefunden wurde
        return null;
    }

    public Produkt getProdukt(int produktNummer){
        for (Produkt p : produkte.keySet()) {
            if (p.getNummer() == produktNummer) {
            p.getPreis();
                
        }
        return p;
    }
        return null;
   
    }
    public Map<Produkt, Integer> getProduktListe() {
        // Alle Produkte mit die Menge werden zurück gegeben
        return produkte;
    }

    // Aktuelle Kontostand zurück geben
    public double getKontostand() {
        return kontostand;
    }

}
