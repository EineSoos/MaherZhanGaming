package projekt;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
        add(buttonPanel, BorderLayout.SOUTH);
        produktListeAktualisieren();
        kontostandAktualisieren();
        setVisible(true);
    }

    // Unvollständiger Arbeit
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == geldEingebeButton) {
            automat.geldEingeben(1);
            kontostandAktualisieren();
        } else if (e.getSource() == produktwaehlenButton) {

        }
    }

    private void produktListeAktualisieren() {

    }

    private void kontostandAktualisieren() {

    }

    public static void main(String[] args) {
        Automat automat = new Automat();
        GUI gui = new GUI(automat);
    }

}
