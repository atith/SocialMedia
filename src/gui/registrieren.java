/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import api.user;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author ob020
 */
class registrieren {

    private JTextField nachname;
    private JTextField vorname;
    private JTextField nickname;
    private JTextField passwort;
    private JPanel Gesamt;
    private Client cl;
    private login menu;

    public registrieren(Client ci) {
        this.cl = ci;
    }

    JPanel createPanelContent() {

        JPanel r = new JPanel(new BorderLayout());
        JLabel r1 = new JLabel("Füllen Sie bitte das unten stehende Formular aus");
        r.add(r1, BorderLayout.PAGE_START);

        //Einrückungen
        r.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        //Formular
        JPanel Jp = new JPanel();
        Jp.setLayout(new BoxLayout(Jp, BoxLayout.Y_AXIS));
        
        JLabel Nn = new JLabel("Nachname", JLabel.HORIZONTAL);
        Jp.add(Nn);
        Dimension minSize = new Dimension(5, 10);
        Dimension prefSize = new Dimension(5, 10);
        Dimension maxSize = new Dimension(Short.MAX_VALUE, 10);
        Jp.add(new Box.Filler(minSize, prefSize, maxSize));
        this.nachname = new JTextField();
        Jp.add(nachname);
        Nn.setLabelFor(nachname);
        Jp.add(new Box.Filler(minSize, prefSize, maxSize));

        JLabel Vn = new JLabel("Vorname", JLabel.HORIZONTAL);
        Jp.add(Vn);
        this.vorname = new JTextField();
        Jp.add(new Box.Filler(minSize, prefSize, maxSize));
        Vn.setLabelFor(vorname);
        Jp.add(new Box.Filler(minSize, prefSize, maxSize));
        Jp.add(vorname);
        Jp.add(new Box.Filler(minSize, prefSize, maxSize));
        JLabel nn = new JLabel("Nickname", JLabel.HORIZONTAL);
        Jp.add(nn);
        this.nickname = new JTextField();
        nn.setLabelFor(nickname);
        Jp.add(new Box.Filler(minSize, prefSize, maxSize));
        Jp.add(nickname);
        JLabel Pw = new JLabel("Password", JLabel.HORIZONTAL);
        Jp.add(Pw);
        this.passwort = new JPasswordField();
        Pw.setLabelFor(passwort);
        Jp.add(new Box.Filler(minSize, prefSize, maxSize));
        Jp.add(passwort);
        Jp.setBorder(BorderFactory.createEmptyBorder(40, 250, 40, 250));

        //Buttons
        JPanel Button = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton an = new JButton("Speichern");
        an.addActionListener(new an());
        JButton ab = new JButton("Zurück");
        ab.addActionListener(new ab());


        Button.add(an);
        Button.add(new Box.Filler(minSize, prefSize, maxSize));
        Button.add(ab);

        //Einrückungen
        Button.setBorder(BorderFactory.createEmptyBorder(40, 250, 40, 250));

        this.Gesamt = new JPanel(new BorderLayout());

        Gesamt.add(r, BorderLayout.NORTH);
        Gesamt.add(Button, BorderLayout.PAGE_END);
        Gesamt.add(Jp, BorderLayout.CENTER);


        return Gesamt;

    }

    private class an implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            user anlegenUser = cl.anlegenUser(nachname.getText().trim(), vorname.getText().trim(), nickname.getText().trim(), passwort.getText().trim());
            if (anlegenUser != null) {
                //Meldung erzeugen
                JOptionPane.showMessageDialog(null, "User wurde angelegt!", "Meldung", JOptionPane.OK_CANCEL_OPTION);

                // Leere die GUI und wechsle Sicht
                Gesamt.removeAll();
                login log = new login(cl);
                JPanel panel = log.createPanelContent();
                Gesamt.add(panel, BorderLayout.CENTER);
                //Aufrufen des neuen Layout und des neu zu zeichnennden Panels
                Gesamt.validate();
                Gesamt.repaint();
            } //Fehlermeldung
            else {
                JOptionPane.showMessageDialog(null, String.format("Teilnehmer wurde nicht angelegt", e.getActionCommand()));
            }

        }
    }

    private class ab implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            Gesamt.removeAll();
            login log = new login(cl);
            JPanel newPanel = log.createPanelContent();
            Gesamt.add(newPanel, BorderLayout.CENTER);
            Gesamt.validate();
            Gesamt.repaint();
        }
    }
}

