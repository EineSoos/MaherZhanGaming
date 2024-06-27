
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUI extends JFrame implements ActionListener {
    private Automat automat;
    private JPanel mainPanel;
    private JPanel buttonsPanel;
    private JButton nullButton;
    private JButton okButton;
    private JButton cancelButton;
    private JButton einButton;
    private JButton zweiButton;
    private JButton nachfuellenButton;
    private JPanel getraenkePanel;
    private JButton fuenfzigButton;
    private JLabel kontoStandLabel;
    private JLabel textLabel;
    private JTextField produktNummerField;
    private boolean isNachfuellModus = false;
    private Produkt aktuelleNachfuellProdukt;
    private static final String nachfuelPasswort = "Betreiber";
    private boolean isProduktNummerEingegeben = false;
    private int aktuelleProduktNummer;

    public GUI(Automat automat) {
        this.automat = automat;
        // Aufbau vom GUI

        setTitle("Getränke Automat");// Titel schreiben
        ImageIcon imageIcon = new ImageIcon("H:\\IT SW 12\\test\\src\\projekt\\bilder\\Getränkeautomat02.jpg");
        this.setIconImage(imageIcon.getImage());
        setSize(1200, 900); // größe ändern
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Wenn man das Fenster abschließt soll der Program brechen
        setResizable(false);
        // das gesammte fenster :D
        mainPanel = new JPanel(new BorderLayout());
        // ein panel, wo die zahlen stehen.
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));// ordnet die panels untereinander.
        getraenkePanel = new JPanel(new GridLayout(3, 3, 10, 10));
        // Festlegung des Abstands(Ausgehend von dem gesamten Panel)
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getraenkePanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        nullButton = new JButton("0");// einen Button erstellen
        nullButton.addActionListener(new NumPadButtonListener());// Actionlistener hinzufügen

        okButton = new JButton("OK");
        okButton.addActionListener(this);

        cancelButton = new JButton("Abbrechen");
        cancelButton.addActionListener(this);

        fuenfzigButton = new JButton("0.50€");
        fuenfzigButton.addActionListener(this);

        einButton = new JButton("1.00€");
        einButton.addActionListener(this);

        zweiButton = new JButton("2.00€");
        zweiButton.addActionListener(this);

        nachfuellenButton = new JButton("Getränke nachfüllen");
        nachfuellenButton.addActionListener(this);

        kontoStandLabel = new JLabel("", SwingConstants.CENTER);// Erstellung von dem "Kontostand-Text" und die
                                                                // Zentrierung
        kontoStandLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonsPanel.add(kontoStandLabel);

        /***********************
         * Erstellung von die buttons mit zahlen neben und untereinander
         *****************/

        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        textLabel = new JLabel("Wählen Sie Ihr Produkt aus", SwingConstants.CENTER);
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonsPanel.add(textLabel);

        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // TextField to display the entered product number
        produktNummerField = new JTextField();
        produktNummerField.setHorizontalAlignment(JTextField.CENTER);
        produktNummerField.setEditable(false);
        produktNummerField.setMaximumSize(new Dimension(100, 30));
        buttonsPanel.add(produktNummerField);

        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel numPadPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(Integer.toString(i));
            button.addActionListener(new NumPadButtonListener());

            numPadPanel.add(button);
        }

        numPadPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonsPanel.add(numPadPanel);

        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel actionPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JPanel nachfuellenPanel = new JPanel(new GridLayout(1, 1, 10, 10));
        nachfuellenPanel.add(nachfuellenButton);
        actionPanel.add(okButton);
        actionPanel.add(nullButton);
        actionPanel.add(cancelButton);
        actionPanel.add(fuenfzigButton);
        actionPanel.add(einButton);
        actionPanel.add(zweiButton);
        actionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonsPanel.add(actionPanel);

        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        buttonsPanel.add(nachfuellenPanel);

        // hier wird ein section erstellt für die produkte je nach menge die produkte
        // wirds größer

        aktuallisiereGetraenkePanel();
        mainPanel.add(getraenkePanel, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.EAST);

        add(mainPanel);
        kontostandAktualisieren();

        setVisible(true);
    }

    /********************** die funktionalität vom buttons ********************/
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fuenfzigButton) {
            automat.geldEingeben(0.50);
            kontostandAktualisieren();
        } else if (e.getSource() == einButton) {
            automat.geldEingeben(1);
            kontostandAktualisieren();
        } else if (e.getSource() == zweiButton) {
            automat.geldEingeben(2);
            kontostandAktualisieren();
        } else if (e.getSource() == cancelButton) {
            if (isNachfuellModus) {
                nachfuellModusDeaktivieren();
                aktuallisiereGetraenkePanel();

            } else {
                updateTextLabel();
                produktNummerField.setText("");
            }

        } else if (e.getSource() == okButton) {
            if (isNachfuellModus) {
                handleNachfuellModus();

            } else {
                try {
                    int produktNummer = Integer.parseInt(produktNummerField.getText());
                    if (automat.produktKaufen(produktNummer)) {
                        automat.getMenge(produktNummer);
                        kontoStandLabel.setText("Kontostand: " + String.format("%.2f", automat.getKontostand()) + "€");
                        updateTextLabelOk();
                        aktuallisiereGetraenkePanel();
                    } else {
                        JOptionPane.showMessageDialog(GUI.this,
                                "Nicht genügend Guthaben oder Produkt nicht verfügbar.");
                    }
                } catch (NumberFormatException ex) {

                }
                produktNummerField.setText("");
            }
        }

        else if (e.getSource() == nachfuellenButton) {
            String passwort = JOptionPane.showInputDialog("Bitte geben Sie das Passwort ein");
            if (passwort != null && passwort.equalsIgnoreCase(nachfuelPasswort)) {
                nachfuellModusAktivieren();
            } else {
                JOptionPane.showMessageDialog(this, "Falsches Passwort!");
            }
        } else if (e.getSource() == okButton && isNachfuellModus == true) {
            try {
                String[] eingaben = produktNummerField.getText().split(" ");
                int produktNummer = Integer.parseInt(eingaben[0]);
                int menge = Integer.parseInt(eingaben[1]);
                automat.nachfuellen(produktNummer, menge);
                aktuallisiereGetraenkePanel();
                nachfuellModusDeaktivieren();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ungültige Eingabe. Bitte Produktnummer und Menge eingeben.");
            }
        }
    }

    private void handleNachfuellModus() {
        if (!isProduktNummerEingegeben) {
            try {
                aktuelleProduktNummer = Integer.parseInt(produktNummerField.getText());
                if (automat.getProduktByNummer(aktuelleProduktNummer) != null) {
                    isProduktNummerEingegeben = true;
                    produktNummerField.setText("");
                    textLabel.setText("Geben Sie die Menge ein (max. 10)");
                } else {
                    JOptionPane.showMessageDialog(this, "Ungültige Produktnummer.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Bitte geben Sie eine gültige Nummer ein.");
            }
        } else {
            try {
                int menge = Integer.parseInt(produktNummerField.getText());
                if (menge > 0 && menge <= 10) {
                    automat.nachfuellen(aktuelleProduktNummer, menge);
                    aktuallisiereGetraenkePanel();
                    JOptionPane.showMessageDialog(this, "Produkt wurde nachgefüllt.");
                    nachfuellModusDeaktivieren();
                } else {
                    JOptionPane.showMessageDialog(this, "Bitte geben Sie eine Menge zwischen 1 und 10 ein.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Bitte geben Sie eine gültige Zahl ein.");
            }
        }
    }

    private void kontostandAktualisieren() {
        kontoStandLabel.setText("Aktueller Kontostand " + automat.getKontostand() + " €");

    }

    private void updateTextLabel() {
        new Thread(() -> {
            try {
                textLabel.setText("Danke für die Nutzung");
                Thread.sleep(1500);
                textLabel.setText("Das Rückgabegeld beträgt: " + String.format("%.2f", automat.rueckgabe()) + "€");
                kontostandAktualisieren();
                setEnabled(false);
                Thread.sleep(3000); // Verzögerung von 3 Sekunden
                textLabel.setText("Wählen Sie Ihr Produkt aus");
                setEnabled(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void updateTextLabelOk() {
        new Thread(() -> {
            try {

                textLabel.setText("Danke für die Nutzung");
                Thread.sleep(1500);
                textLabel.setText("Das Rückgabegeld beträgt: " + String.format("%.2f", automat.rueckgabe()) + "€");
                kontostandAktualisieren();
                Thread.sleep(3000); // Verzögerung von 3 Sekunden
                textLabel.setText("Das Getränk wird  ausgeben");
                Thread.sleep(5000);
                textLabel.setText("Wählen Sie Ihr Produkt aus");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void nachfuellModusAktivieren() {
        isNachfuellModus = true;
        isProduktNummerEingegeben = false;
        textLabel.setText("Nachfüll-Modus aktiviert. Geben Sie die Produktnummer ein");
        produktNummerField.setText("");
    }

    private void nachfuellModusDeaktivieren() {
        isNachfuellModus = false;
        isProduktNummerEingegeben = false;
        textLabel.setText("Wählen Sie Ihr Produkt aus");
        produktNummerField.setText("");
    }

    private void aktuallisiereGetraenkePanel() {
        getraenkePanel.removeAll();

        // Erstelle eine TreeMap, die automatisch nach den Schlüsseln (Produktnummern)
        // sortiert
        Map<Integer, Map.Entry<Produkt, Integer>> sortedProducts = new TreeMap<>();

        // Fülle die sortierte Map
        for (Map.Entry<Produkt, Integer> entry : automat.getProduktListe().entrySet()) {
            Produkt produkt = entry.getKey();
            sortedProducts.put(produkt.getNummer(), entry);
        }

        // Iteriere über die sortierte Map
        for (Map.Entry<Integer, Map.Entry<Produkt, Integer>> sortedEntry : sortedProducts.entrySet()) {
            Produkt produkt = sortedEntry.getValue().getKey();
            int menge = sortedEntry.getValue().getValue();

            JPanel produktPanel = new JPanel(new BorderLayout());
            JLabel bilderLabel = new JLabel(
                    new ImageIcon("H:\\IT SW 12\\test\\src\\projekt\\bilder\\" + produkt.getNummer() + ".png"));
            JLabel nummerLabel = new JLabel(
                    "Nummer: " + produkt.getNummer() + " | Preis: " + produkt.getPreis() + "€",
                    SwingConstants.CENTER);
            JLabel mengeLabel = new JLabel("Menge Übrig: " + menge, SwingConstants.CENTER);

            produktPanel.add(bilderLabel, BorderLayout.CENTER);
            produktPanel.add(nummerLabel, BorderLayout.SOUTH);
            produktPanel.add(mengeLabel, BorderLayout.NORTH);

            getraenkePanel.add(produktPanel);
        }

        getraenkePanel.revalidate();
        getraenkePanel.repaint();
    }

    // ActionListener class for numeric buttons
    private class NumPadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String buttonText = ((JButton) e.getSource()).getText();
            produktNummerField.setText(produktNummerField.getText() + buttonText);
        }
    }

    public static void main(String[] args) {
        Automat automat = new Automat();
        automat.produkteHinzufuegen(new Produkt("Redbull", 1.49, 1), 10);
        automat.produkteHinzufuegen(new Produkt("Limonadensaft", 1.19, 2), 10);
        automat.produkteHinzufuegen(new Produkt("Apfelsaft", 1.29, 3), 10);
        automat.produkteHinzufuegen(new Produkt("Wasser", 0.99, 4), 10);
        automat.produkteHinzufuegen(new Produkt("Cola", 1.29, 5), 10);
        automat.produkteHinzufuegen(new Produkt("Fanta", 1.39, 6), 10);
        automat.produkteHinzufuegen(new Produkt("Sprite", 1.49, 7), 10);
        automat.produkteHinzufuegen(new Produkt("Pepsi", 1.79, 8), 10);
        automat.produkteHinzufuegen(new Produkt("Seven up", 1.29, 9), 10);

        GUI gui = new GUI(automat);
    }

}