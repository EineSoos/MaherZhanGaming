package GUI;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class GUIautomat implements ActionListener {

    private JFrame frame;
    private JButton buttonBenutzer;
    private JButton buttonBetreiber;
    private JLabel start;

    public GUIautomat() {
        frame = new JFrame();

        buttonBenutzer = new JButton("Benutzer");
        buttonBetreiber = new JButton("Betreiber");

        start = new JLabel("Start");

        buttonBetreiber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = "Betreiber";
                String input = javax.swing.JOptionPane.showInputDialog("Bitte geben Sie das Passwort ein:");
                if (input != null && input.equals(password)) {
                    frame.getContentPane().remove(buttonBenutzer);
                    frame.getContentPane().remove(buttonBetreiber);
                    frame.getContentPane().remove(start);
                    frame.revalidate();
                    frame.repaint();

                    // Creating new buttons with smaller size
                    JButton buttonFnata = new JButton("Fnata");
                    JButton buttonCola = new JButton("Cola");
                    JButton buttonHalalBier = new JButton("Halal Bier");
                    JButton buttonWasser = new JButton("Wasser");
                    JButton buttonPepsi = new JButton("Pepsi");
                    JButton buttonEinkaufBestätigen = new JButton("Einkauf bestätigen");

                    // Styling the buttons to make them visually appealing
                    Font buttonFont = new Font("Arial", Font.PLAIN, 14);
                    buttonFnata.setFont(buttonFont);
                    buttonCola.setFont(buttonFont);
                    buttonHalalBier.setFont(buttonFont);
                    buttonWasser.setFont(buttonFont);
                    buttonPepsi.setFont(buttonFont);
                    buttonEinkaufBestätigen.setFont(buttonFont);

                    // Adding the new buttons to the panel
                    JPanel panel = new JPanel();
                    panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
                    panel.setLayout(new GridLayout());
                    panel.add(buttonFnata);
                    panel.add(buttonCola);
                    panel.add(buttonHalalBier);
                    panel.add(buttonWasser);
                    panel.add(buttonPepsi);
                    panel.add(buttonEinkaufBestätigen);

                    // Centering the buttons
                    panel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    panel.setAlignmentY(Component.CENTER_ALIGNMENT);

                    frame.add(panel, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();

                    // Disable the buttons
                    buttonBenutzer.setEnabled(false);
                    buttonBetreiber.setEnabled(false);
                }
            }
        });

        buttonBenutzer.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout());
        panel.add(buttonBenutzer);
        panel.add(buttonBetreiber);
        panel.add(start);

        // Centering the buttons
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GetränkeAutomat");
        frame.pack();
        frame.setSize(800, 900); // Set the size of the frame
        frame.setResizable(false); // Disable resizing
        frame.setLocationRelativeTo(null); // Center the frame on the desktop
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GUIautomat();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}