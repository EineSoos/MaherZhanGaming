package projekt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.OverlayLayout;

public class GUI extends JFrame implements ActionListener {
    private Automat automat;
    JPanel buttonsPanel;
    


    public GUI(Automat automat) {
        this.automat = automat;
        // Aufbau vom GUI

        JPanel mainPanel = new JPanel(new BorderLayout());
        
        JPanel buttonsPanel = new JPanel(new GridLayout(7, 3, 10, 10));
        JButton nullButton = new JButton("0");
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Abbrechen");
        JButton fuenfzigButton = new JButton("0.50€");
        JButton einButton = new JButton("1.00€");
        JButton zweiButton = new JButton("2.00€");

        setTitle("Getränke Automat");// Titel schreiben
        ImageIcon imageIcon = new ImageIcon("H:\\IT SW 12\\test\\src\\projekt\\bilder\\Getränkeautomat02.jpg");
        this.setIconImage(imageIcon.getImage());
        setSize(1200, 900); // größe ändern
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Wenn man das Fenster abschließt soll der Program brechen
        

        buttonsPanel.setBorder(BorderFactory.createEmptyBorder( 0, 20, 30, 20));
        JLabel kontoStandLabel = new JLabel("Aktueller Kontostand " + automat.getKontostand() + " €");
        JLabel leerLabel1 = new JLabel(" ");
        JLabel leerLabel2 = new JLabel(" ");

        
        
        buttonsPanel.add(leerLabel1);
        buttonsPanel.add(kontoStandLabel);
        buttonsPanel.add(leerLabel2);

        for(int i= 1; i<=9; i++){
            JButton button = new JButton(Integer.toString(i));
            buttonsPanel.add(button);
        }
        
        
        buttonsPanel.add(okButton);
        buttonsPanel.add(nullButton);
        buttonsPanel.add(cancelButton);
        buttonsPanel.add(fuenfzigButton);
        buttonsPanel.add(einButton);
        buttonsPanel.add(zweiButton);
        mainPanel.add(buttonsPanel, BorderLayout.EAST);
        
        
        add(mainPanel);



        // Schrift Änderung
        Font font = new Font("KG Happy", Font.LAYOUT_LEFT_TO_RIGHT, 20);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       

    }





    public static void main(String[] args) {
        Automat automat = new Automat();
        automat.produkteHinzufuegen(new Produkt("Cola", 1.29), 10);
        automat.produkteHinzufuegen(new Produkt("Fanta", 1.39), 10);
        automat.produkteHinzufuegen(new Produkt("Redbull", 1.49), 10);
        automat.produkteHinzufuegen(new Produkt("Sprite", 1.49), 10);
        automat.produkteHinzufuegen(new Produkt("Pepsi", 1.79), 10);
        automat.produkteHinzufuegen(new Produkt("Seven up", 1.29), 10);

        GUI gui = new GUI(automat);
    }

}
