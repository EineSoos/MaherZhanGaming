import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GUI extends JFrame implements ActionListener {
    private Automat automat;
    private JTextArea produktListTextArea;
    private JLabel kontostandLabel;
    private JButton geldEingebeButton;
    private JButton produktwaehlenButton;
    private JButton geldZurueckgebenButton;
    private JButton getraenkeAusfuellenButton;

    public GUI(Automat automat) {
        this.automat = automat;
        // Aufbau vom GUI

        setTitle("Getränke Automat");// Titel schreiben
        setSize(1200, 900); // größe ändern
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Wenn man das Fenster abschließt soll der Program brechen
        setLayout(new BorderLayout()); // Erstellung von einem Border
        produktListTextArea = new JTextArea(); // Erstellung von einem Bereich wo die produkt liste rein kommen soll
        produktListTextArea.setEditable(false); // als nicht änderbar setzen
        add(new JScrollPane(produktListTextArea), BorderLayout.CENTER); // zu GUI hinzufügen
        kontostandLabel = new JLabel("Aktuelle Kontostand: €" + automat.getKontostand()); // ein Bereich für Kontostand
                                                                                          // erstellen
        add(kontostandLabel, BorderLayout.NORTH); // zu GUI hinzufügen mit einem bestimmten layout
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3)); // ein Bereich für die Tasten erstellen

        // Erstellung vom Tasten mit die funktionalität wenn man darauf druckt
        geldEingebeButton = new JButton("Münzen Eingeben");
        geldEingebeButton.addActionListener(this);
        buttonPanel.add(geldEingebeButton);
        produktwaehlenButton = new JButton("Produkt wählen");
        produktwaehlenButton.addActionListener(this);
        buttonPanel.add(produktwaehlenButton);
        geldZurueckgebenButton = new JButton("Rest zurückgeben");
        geldZurueckgebenButton.addActionListener(this);
        buttonPanel.add(geldZurueckgebenButton);
        getraenkeAusfuellenButton = new JButton("Getränke Ausfüllen");
        getraenkeAusfuellenButton.addActionListener(this);
        buttonPanel.add(getraenkeAusfuellenButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Schrift Änderung
        Font font = new Font("KG Happy", Font.LAYOUT_LEFT_TO_RIGHT, 20);
        produktListTextArea.setFont(font);

        produktListeAnzeigen();
        kontostandAnzeigen();
        setVisible(true);
    }

    // !!!!! Unvollständiger Arbeit
    @Override
    public void actionPerformed(ActionEvent e) {
        // fals man auf geldEingebenButton druckt wird +1 zum kontostand hinzugefügt
        if (e.getSource() == geldEingebeButton) {
            automat.geldEingeben(1);
            kontostandAnzeigen();
        }
        // fals man auf produkt wählen druckt wird folgendes geprüft
        else if (e.getSource() == produktwaehlenButton) {
            Map<Produkt, Integer> produktListe = automat.getProduktListe();
            // wenn es leer ist wird das ausgegeben
            if (produktListe.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Es gibt gerade keine Getränke");

            }
            // sonst wird geprüft wan man eingegeben hat
            else {
                String wahl;
                wahl = JOptionPane.showInputDialog(this, "Bitte wählen Sie ihr Produkt");
                for (Map.Entry<Produkt, Integer> entry : produktListe.entrySet()) {
                    Produkt produkt = entry.getKey();
                    if (wahl.equalsIgnoreCase(produkt.getName())) {
                        JOptionPane.showConfirmDialog(this,
                                produkt.getName() + " kostet " + produkt.getPreis() + "\nwollen Sie das bestellen? ");

                    }

                }

            }

        }
        // sonst wenn man auf geldZurueckgebenButton druckt dann kommt ein Nachricht mit
        // der restlichen geld
        else if (e.getSource() == geldZurueckgebenButton) {
            double rest = automat.rueckGeld();
            JOptionPane.showMessageDialog(this, "Rückgabe Geld ist: " + rest + "€");
            kontostandAnzeigen();
        }

        else if (e.getSource() == getraenkeAusfuellenButton) {

        }
    }

    // Produkte anzeigen
    private void produktListeAnzeigen() {
        Map<Produkt, Integer> produktListe = automat.getProduktListe();
        StringBuilder sb = new StringBuilder();
        sb.append("Produkt Liste: \n");
        for (Map.Entry<Produkt, Integer> entry : produktListe.entrySet()) {
            Produkt produkt = entry.getKey();
            int menge = entry.getValue();
            sb.append(produkt.getName()).append(": €").append(produkt.getPreis()).append(" (").append(menge)
                    .append(")\n");
        }
        produktListTextArea.setText(sb.toString());

    }

    // liefert den aktuellen Kontostand zurück
    private void kontostandAnzeigen() {
        kontostandLabel.setText("Aktuelle Kontostand: €" + automat.getKontostand());

    }

    public static void main(String[] args) {
        Automat automat = new Automat();
        automat.produkteHinzufuegen(new Produkt("Cola", 1.29), 10);
        automat.produkteHinzufuegen(new Produkt("Fanta", 1.39), 10);
        automat.produkteHinzufuegen(new Produkt("Redbull", 1.49), 10);
        automat.produkteHinzufuegen(new Produkt("Sprite", 1.49), 10);
        automat.produkteHinzufuegen(new Produkt("Pepsi", 1.79), 10);

        GUI gui = new GUI(automat);
    }

}
