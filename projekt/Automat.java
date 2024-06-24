
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

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

    // Wird aufgerufen um produkt zu kaufen
    public boolean produktKaufen(int produktNummer) {
        Produkt produkt = getProduktByNummer(produktNummer);
        if (produkt != null && produkte.get(produkt) > 0 && kontostand >= produkt.getPreis()) {
            produkte.put(produkt, produkte.get(produkt) - 1);
            kontostand -= produkt.getPreis();

            return true;
        }
        return false;
    }

    // Rückgabe von Geld
    public double rueckgabe() {
        double rueckgabeKontostand = kontostand;
        kontostand = 0;
        return rueckgabeKontostand;
    }

    // preise Anzeigen
    public double preisAnzeigen(int zahl) {
        Produkt produkt = getProduktByNummer(zahl);
        double preis = produkt.getPreis();
        return preis;
    }

    

    // hilft um an einen Produkt durch seinen Nummer angreifen zu können
    private Produkt getProduktByNummer(int produktNummer) {
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

    // Aktuelle Kontostand zurück geben
    public double getKontostand() {
        return kontostand;
    }

}
