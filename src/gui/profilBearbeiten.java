/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import api.user;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Atith
 */
class profilBearbeiten {

    private String nickname;
    private Client cl;
    private Hashtable abonennten;
    private JPanel main;
    private JTextField vorname;
    private JTextField name;
    private JTextField nick;
    private JTextField pass;

    public profilBearbeiten(Client cl, String nickname) {
        this.nickname = nickname;
        this.cl = cl;
        this.abonennten = cl.getUserData(nickname);
    }

    JPanel createPanelContent() {
        JPanel middle = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel vn = new JLabel("Vorname:", JLabel.TRAILING);
        vn.setFont(new Font("Arial", Font.BOLD, 18));
        middle.add(vn);
        this.vorname = new JTextField();
        this.vorname.setText((String) this.abonennten.get("vorname").toString());
        this.vorname.setEditable(true);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10;
        c.weightx = 0.0;
        c.gridwidth = 0;
        c.gridx = 1;
        c.gridy = 0;
        middle.add(vorname, c);

        JLabel nn = new JLabel("Name:", JLabel.TRAILING);
        nn.setFont(new Font("Arial", Font.BOLD, 18));
        middle.add(nn);
        this.name = new JTextField();
        this.name.setText((String) this.abonennten.get("nachname").toString());
        this.name.setEditable(true);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10;
        c.weightx = 0.0;
        c.gridwidth = 0;
        c.gridx = 1;
        c.gridy = 1;
        middle.add(name, c);

        JLabel em = new JLabel("Nickname:", JLabel.TRAILING);
        em.setFont(new Font("Arial", Font.BOLD, 18));
        middle.add(em);
        this.nick = new JTextField();
        this.nick.setText((String) this.abonennten.get("nick").toString());
        this.nick.setEditable(true);
        this.nick.setPreferredSize(new Dimension(150, 20));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10;
        c.weightx = 0.0;
        c.gridwidth = 0;
        c.gridx = 1;
        c.gridy = 2;
        middle.add(nick, c);

        JLabel pw = new JLabel("Passwort:", JLabel.TRAILING);
        pw.setFont(new Font("Arial", Font.BOLD, 18));
        middle.add(pw);
        this.pass = new JPasswordField();
        this.pass.setText((String) this.abonennten.get("passwort").toString());
        this.pass.setEditable(true);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10;
        c.weightx = 0.0;
        c.gridwidth = 0;
        c.gridx = 1;
        c.gridy = 3;
        middle.add(pass, c);


        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Erzeuge Button zum löschen eines Kalendereintrags
        JButton save = new JButton("Speichern");

        //Erzeuge Button um Kalendereintrag anzulegen
        JButton back = new JButton("Zurück");

        save.addActionListener(new save());
        buttons.add(save);

        back.addActionListener(new back());
        buttons.add(back);

        main = new JPanel(new BorderLayout());
        //Setzen der Position auf NORTH
        this.main.add(buttons, BorderLayout.SOUTH);
        this.main.add(middle, BorderLayout.CENTER);

        return main;

    }

    public class save implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            String id = (String) profilBearbeiten.this.abonennten.get("uid").toString();

            int uid = Integer.parseInt(id);

            user us = cl.profilBearbeiten(uid, vorname.getText().trim(), name.getText().trim(), nick.getText().trim(), pass.getText().trim());
            if (us != null) {

                // GUI "leeren" und Sicht wechseln
                JOptionPane.showMessageDialog(null, "Profil wurde bearbeitet!", "Meldung", JOptionPane.OK_CANCEL_OPTION);
                main.removeAll();
                pinnwand pn = new pinnwand(cl, nickname);
                JPanel newPanel = pn.createPanelContent();
                main.add(newPanel, BorderLayout.CENTER);
                main.validate();
                main.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Profil konnte nicht bearbeitet werden!", "Meldung", JOptionPane.OK_CANCEL_OPTION);

            }
        }
    }

    public class back implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            // GUI "leeren" und Sicht wechseln

            main.removeAll();

            pinnwand pn = new pinnwand(cl, nickname);
            JPanel newPanel = pn.createPanelContent();

            //neues Panel (ChooseAirline) laden
            main.add(newPanel, BorderLayout.CENTER);
            main.validate();
            main.repaint();
        }
    }
}

