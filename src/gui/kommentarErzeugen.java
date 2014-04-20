/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import api.kommentar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author berack
 */
class kommentarErzeugen {

    private JTextArea kommentar;
    private JPanel gesamt;
    private Client cl;
    private String nickname;
    private int bid;

    public kommentarErzeugen(Client cl, String nickname, int bid) {
        this.cl = cl;
        this.nickname = nickname;
        this.bid = bid;
    }

    JPanel createPanelContent() {

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        GridBagConstraints c = new GridBagConstraints();

        JLabel pinnwand = new JLabel("Kommentar erzeugen");
        pinnwand.setFont(new Font("Arial", Font.BOLD, 18));
        c.ipady = 0;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(pinnwand, c);

        this.kommentar = new JTextArea();
        //kommentar.setPreferredSize(null);
        kommentar.setLineWrap(true);
        kommentar.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(kommentar);
        pinnwand.setLabelFor(kommentar);
        scroll.setPreferredSize(null);

        c.insets = new Insets(3, 3, 3, 3);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10;
        c.weightx = 0.0;
        c.gridwidth = 0;
        c.gridx = 1;
        c.gridy = 1;
        //c.insets = new Insets(3, 160, 410, 0);
        panel.add(scroll, c);

        JButton comment = new JButton();
        GridBagConstraints t = new GridBagConstraints();
        try {
            Image img = ImageIO.read(getClass().getResource("/resources/comment.png"));
            comment.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            System.out.println(ex);
        }
        comment.setToolTipText("Kommentar erzeugen");
        comment.addActionListener(new commentK());
        comment.setPreferredSize(new Dimension(25, 25));
        t.ipady = 0;
        t.weightx = 0.0;
        t.gridwidth = 1;
        t.gridx = 1;
        t.gridy = 2;
        t.insets = new Insets(0, 320, 0, 0);
        panel.add(comment, t);

        JPanel button = new JPanel(new GridBagLayout());
        GridBagConstraints x = new GridBagConstraints();
        JButton zurück = new JButton("zurück");
        zurück.addActionListener(new zurück());
        x.ipady = 0;
        x.weightx = 0.0;
        x.gridwidth = GridBagConstraints.REMAINDER;
        x.gridx = 1;
        x.gridy = 1;
        x.insets = new Insets(0, 500, 0, 0);
        button.add(zurück, x);

        this.gesamt = new JPanel(new BorderLayout());
        gesamt.add(panel, BorderLayout.CENTER);
        gesamt.add(button, BorderLayout.NORTH);

        return gesamt;
    }

    class commentK implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int uid = cl.getUidFromNickname(nickname);
            kommentar kommentarErzeugen = cl.kommentarErzeugen(kommentar.getText().trim(), bid, uid);

            if (kommentarErzeugen != null) {
                JOptionPane.showMessageDialog(null, "Kommentar wurde erzeugt", "Meldung", JOptionPane.OK_CANCEL_OPTION);
                gesamt.removeAll();
                eintragAnzeigen anz = new eintragAnzeigen(cl, nickname);
                JPanel newPanel = anz.createPanelContent();
                gesamt.add(newPanel, BorderLayout.CENTER);
                gesamt.validate();
                gesamt.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Kommentar konnte nicht erzeugt werden", "Meldung", JOptionPane.OK_CANCEL_OPTION);
            }
        }
    }

    class zurück implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            gesamt.removeAll();
            eintragAnzeigen anz = new eintragAnzeigen(cl, nickname);
            JPanel newPanel = anz.createPanelContent();
            gesamt.add(newPanel, BorderLayout.CENTER);
            gesamt.validate();
            gesamt.repaint();
        }
    }
}

