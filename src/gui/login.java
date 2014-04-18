package gui;

import api.user;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Atith
 */
public class login {
    //setze Textfeld für Nickname

    private JTextField nickname;
    //setze Textfeld für Passwort
    private JTextField passwort;
    //setze gesamtes Panel
    public JPanel gesamt;
    //setze Frame
    private JFrame frame;
    //setze Client
    private Client cl;

    public login(Client cl) {
        this.cl = cl;
    }

    JPanel createPanelContent() {
        JPanel wilk = new JPanel(new BorderLayout());
        JLabel willkommen = new JLabel(
                "<html><h3>Melden Sie sich an, oder registrieren Sie sich: </h3></html>");

        wilk.add(willkommen, BorderLayout.CENTER);


        //Einrückungen
        wilk.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));


        JPanel anmeldung = new JPanel();
        anmeldung.setLayout(new BoxLayout(anmeldung, BoxLayout.Y_AXIS));
        anmeldung.setBorder(BorderFactory.createEmptyBorder(40, 250, 40, 250));

        //Hinzufügen eines Labels mit dem Text "Platz:"
        JLabel LabelEmail = new JLabel("Nickname:", JLabel.LEFT);

        //Label zum oben erstellten Panel hinzufügen
        anmeldung.add(LabelEmail);


        //Erstellen eines Textfeldes für die Anmeldung des Usesrs
        this.nickname = new JTextField();
        Font newTextFieldFont = new Font(nickname.getFont().getName(), Font.ITALIC, nickname.getFont().getSize());


        //Set JTextField font using new created font
        nickname.setFont(newTextFieldFont);
        //Label für für das Textfeld hinzufügen
        LabelEmail.setLabelFor(this.nickname);

        anmeldung.add(nickname);
        Dimension minSize = new Dimension(5, 70);
        Dimension prefSize = new Dimension(5, 70);
        Dimension maxSize = new Dimension(Short.MAX_VALUE, 70);
        anmeldung.add(new Box.Filler(minSize, prefSize, maxSize));

        JLabel LabelPasswort = new JLabel("Passwort:", JLabel.LEFT);
        anmeldung.add(LabelPasswort);


        this.passwort = new JPasswordField();

        LabelPasswort.setLabelFor(this.passwort);
        anmeldung.add(passwort);



        JPanel Button = new JPanel();
        Button.setLayout(new BoxLayout(Button, BoxLayout.LINE_AXIS));
        JButton an = new JButton("Anmelden");
        JButton reg = new JButton("Registrieren");

        an.addActionListener(new an());
        reg.addActionListener(new reg());

        Button.add(an);
        Button.add(new Box.Filler(minSize, prefSize, maxSize));
        Button.add(reg);

        //Einrückungen
        Button.setBorder(BorderFactory.createEmptyBorder(40, 250, 80, 250));

        this.gesamt = new JPanel(new BorderLayout());
        gesamt.add(wilk, BorderLayout.PAGE_START);
        gesamt.add(anmeldung, BorderLayout.CENTER);
        gesamt.add(Button, BorderLayout.PAGE_END);

        return gesamt;
    }

    private class reg implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            gesamt.removeAll();
            registrieren reg = new registrieren(cl);
            JPanel newPanel = reg.createPanelContent();
            gesamt.add(newPanel, BorderLayout.CENTER);
            gesamt.validate();
            gesamt.repaint();
        }
    }

    private class an implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            user us = cl.ueberpruefungAnmeldung(nickname.getText().trim(), passwort.getText().trim());

            if (us != null) {
                gesamt.removeAll();
                pinnwand pin = new pinnwand(cl, nickname.getText().trim());
                JPanel newPanel = pin.createPanelContent();
                gesamt.add(newPanel, BorderLayout.CENTER);
                gesamt.validate();
                gesamt.repaint();

            } else {
                JOptionPane.showMessageDialog(null, String.format("falsches Passwort!", e.getActionCommand()));
                login log = new login(cl);
                JPanel newPanel = log.createPanelContent();
                gesamt.add(newPanel, BorderLayout.CENTER);
                gesamt.validate();
                gesamt.repaint();
            }

        }
    }
}
