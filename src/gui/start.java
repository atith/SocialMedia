/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Atith
 */
public class start {

    private JFrame frame;
    private JPanel buttons;
    public JPanel mainPanel;
    private Client cl = new Client();
    private JPanel top;

    //Mainmethode
    public static void main(String[] args) {
        start m1 = new start();
        m1.createKomponents();
    }

    start() {
    }

    public void createKomponents() {
        //Erzeuge Frame
        frame = new JFrame("Social-Media-Pinnwand");
        //Setze Exit-Funktion
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel test = new JPanel (new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel gruß = new JLabel("Willkommen! Klicken Sie auf 'Start' um die Anwendung zu starten.");
        c.ipady = 0;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        test.add(gruß, c);


        JButton an = new JButton("Start");
        c.ipady = 0;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        an.addActionListener(new an());
        test.add(an, c);


        this.mainPanel = new JPanel();
        this.mainPanel.add(test, c);
        
        frame.getContentPane().add(mainPanel);
        //Setze Fenster-Größe
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private class an implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            mainPanel.removeAll();
            login log = new login(cl);
            JPanel newPanel = log.createPanelContent();
            mainPanel.add(newPanel, BorderLayout.CENTER);
            mainPanel.validate();
            mainPanel.repaint();

        }
    }
}

