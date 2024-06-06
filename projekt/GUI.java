package projekt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.OverlayLayout;
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
    private JPanel getraenkePanel;
    private JButton fuenfzigButton;
    private JLabel kontoStandLabel;
    private JLabel textLabel;

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
        nullButton.addActionListener(this);// Actionlistener hinzufügen

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

        kontoStandLabel = new JLabel("", SwingConstants.CENTER);// Erstellung von dem "Kontostand-Text" und die
                                                                // Zentrierung
        kontoStandLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonsPanel.add(kontoStandLabel);

        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        textLabel = new JLabel("Wählen Sie Ihr Produkt aus", SwingConstants.CENTER);
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonsPanel.add(textLabel);

        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel numPadPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(Integer.toString(i));
            numPadPanel.add(button);
        }

        numPadPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonsPanel.add(numPadPanel);

        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel actionPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        actionPanel.add(okButton);
        actionPanel.add(nullButton);
        actionPanel.add(cancelButton);
        actionPanel.add(fuenfzigButton);
        actionPanel.add(einButton);
        actionPanel.add(zweiButton);
        actionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonsPanel.add(actionPanel);

        for (int i = 1; i <= automat.getProduktListe().size(); i++) {
            JPanel produktPanel = new JPanel(new BorderLayout());
            JLabel bilderLabel = new JLabel(new ImageIcon("H:\\IT SW 12\\test\\src\\projekt\\bilder\\" + i + ".png"));
            JLabel nummerLabel = new JLabel("" + i + (int) (Math.random() * 9) + "\n", SwingConstants.CENTER);

            produktPanel.add(bilderLabel, BorderLayout.CENTER);
            produktPanel.add(nummerLabel, BorderLayout.SOUTH);
            getraenkePanel.add(produktPanel);

        }

        mainPanel.add(getraenkePanel, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.EAST);

        add(mainPanel);
        kontostandAktualisieren();

        setVisible(true);
    }

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
            updateTextLabel();
            

        } else if(e.getSource()==okButton){
            updateTextLabelOk();
            //setEnabled(false) wenn produkte fertig
            

        }

    }

    private void kontostandAktualisieren() {
        kontoStandLabel.setText("Aktueller Kontostand " + automat.getKontostand() + " €");
    }

    private void updateTextLabel() {
        new Thread(() -> {
            try {
                textLabel.setText("Danke für die Nutzung");
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
                Thread.sleep(3000); // Verzögerung von 3 Sekunden
                textLabel.setText("Das Getränk wird  ausgeben");
                Thread.sleep(5000); 
                textLabel.setText("Wählen Sie Ihr Produkt aus");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }



    public static void main(String[] args) {
        Automat automat = new Automat();
        automat.produkteHinzufuegen(new Produkt("Redbull", 1.49), 10);
        automat.produkteHinzufuegen(new Produkt("Limonadensaft", 1.19), 10);
        automat.produkteHinzufuegen(new Produkt("Apfelsaft", 1.29), 10);
        automat.produkteHinzufuegen(new Produkt("Wasser", 0.99), 10);
        automat.produkteHinzufuegen(new Produkt("Cola", 1.29), 10);
        automat.produkteHinzufuegen(new Produkt("Fanta", 1.39), 10);
        automat.produkteHinzufuegen(new Produkt("Sprite", 1.49), 10);
        automat.produkteHinzufuegen(new Produkt("Pepsi", 1.79), 10);
        automat.produkteHinzufuegen(new Produkt("Seven up", 1.29), 10);

        GUI gui = new GUI(automat);
    }

}