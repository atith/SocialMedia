/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import api.abo;
import api.beitrag;
import api.kommentar;
import api.like;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Atith
 */
class pinnwand2 {

    private Client cl;
    private String nickname;
    private JPanel gesamt;
    private JTextArea textArea;
    private String nicknameAbo;
    private JTextArea kommentArea;

    public pinnwand2(Client cl, String nickname, String nick) {
        this.cl = cl;
        this.nickname = nickname;
        this.nicknameAbo = nick;

    }

    JPanel createPanelContent() {

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        GridBagConstraints c = new GridBagConstraints();

        JLabel pinnwand = new JLabel("Pinnwand");
        pinnwand.setFont(new Font("Arial", Font.BOLD, 18));
        Insets set = new Insets(3, 3, 3, 3);
        c.insets = set;
        c.ipady = 0;
        c.weightx = 0.0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(pinnwand, c);

        int uid2 = cl.getUidFromNickname(nicknameAbo);

        Vector<beitrag> test = cl.getAllBeiträge(uid2);

        for (int i = 0; i < test.size(); i++) {
            try {
                String beitrag = test.elementAt(i).getText();
                Vector<kommentar> test2 = cl.getAllKommentare(test.elementAt(i).getBid());
                this.textArea = new JTextArea(beitrag);
                textArea.setPreferredSize(null);
                textArea.setFont(new Font("Arial", Font.ITALIC, 14));
                textArea.setEnabled(false);
                c.insets = set;
                c.ipady = 0;
                c.anchor = GridBagConstraints.EAST;
                c.weightx = 0.0;
                c.gridwidth = 1;
                c.gridx = 0;
                c.gridy++;
                JPanel panel2 = new JPanel(new GridBagLayout());
                GridBagConstraints d = new GridBagConstraints();
                d.insets = set;
                d.gridx = 0;
                d.gridy = 0;
                int bid = test.elementAt(i).getBid();
                String tBid = Integer.toString(bid);
                textArea.setName(tBid);
                panel2.add(textArea, d);
                d = new GridBagConstraints();
                d.insets = set;
                d.gridx = 1;
                d.gridy = 0;
                d.gridwidth = GridBagConstraints.REMAINDER;
                String nick = cl.getNickFromBid(bid);
                panel2.add(new JLabel(nick + " " + test.elementAt(i).getTimestamp()), d);
                d = new GridBagConstraints();
                d.insets = set;
                d.gridx = 1;
                d.gridy = 2;

                like likes2 = cl.getAllLikes(bid, uid2);

                JButton likes = new JButton("Gefällt mir");
                if (likes2.getAnzahl() != 0 && likes2.getUid() == uid2) {
                    likes.setText("Gefällt mir nicht mehr");
                }
                likes.setEnabled(false);
                panel2.add(likes, d);

                d = new GridBagConstraints();
                d.insets = set;
                d.gridx = 2;
                d.gridy = 2;
                JButton comment = new JButton("Kommentar");
                comment.setEnabled(false);
                panel2.add(comment, d);
                int j = 0;
                for (int h = 0; h < test2.size(); h++) {
                    JPanel panel3 = new JPanel();
                    panel3.setBorder(BorderFactory.createLineBorder(Color.red));
                    String kommentar = test2.elementAt(h).getText();
                    this.kommentArea = new JTextArea(kommentar);
                    kommentArea.setEnabled(false);
                    panel3.add(kommentArea);
                    d = new GridBagConstraints();
                    d.insets = set;
                    d.gridx = 1;
                    d.gridy = 3 + j;
                    j++;
                    d.gridwidth = GridBagConstraints.REMAINDER;
                    panel2.add(panel3, d);
                }
                d = new GridBagConstraints();
                d.insets = set;
                d.gridx = 1;
                d.gridy = 1;
                d.gridwidth = GridBagConstraints.REMAINDER;

                int anz = cl.getAllLikesAnzahl(bid);
                String lk = Integer.toString(anz);
                panel2.add(new JLabel("Beitrag wurde " + lk + " mal geliked"), d);
                //  textArea.add(new JButton("Fert"), BorderLayout);
                panel2.setBorder(BorderFactory.createLineBorder(Color.black));
                panel.add(panel2, c);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }

        JPanel button = new JPanel(new GridBagLayout());
        JButton zurück = new JButton("zurück");
        zurück.addActionListener(new zurück());

        GridBagConstraints x = new GridBagConstraints();
        x.insets = new Insets(0, 550, 10, 0);
        x.ipady = 0;
        x.weightx = 0.0;
        x.gridwidth = GridBagConstraints.REMAINDER;
        x.gridx = 0;
        x.gridy = 0;
        button.add(zurück, x);

        int uid = cl.getUidFromNickname(nickname);
        JButton abonnieren = new JButton("abonnieren");
        Vector<abo> a = cl.getAllAbonnenten(uid, uid2);
        for (int i = 0; i < a.size(); i++) {
            try {
                if (a.elementAt(i).getUid() == uid && a.elementAt(i).getUid2() == uid2) {
                    abonnieren.setText("abo löschen");
                }
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }





        abonnieren.addActionListener(new abonnieren());


        GridBagConstraints y = new GridBagConstraints();
        y.insets = new Insets(0, 350, 10, 0);
        y.ipady = 0;
        y.weightx = 0.0;
        y.gridwidth = GridBagConstraints.REMAINDER;
        y.gridx = 0;
        y.gridy = 0;
        button.add(abonnieren, y);

        this.gesamt = new JPanel(new BorderLayout());
        JScrollPane scroll = new JScrollPane(panel);
        gesamt.add(scroll, BorderLayout.CENTER);
        gesamt.add(button, BorderLayout.NORTH);

        return gesamt;
    }

    class zurück implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            gesamt.removeAll();
            pinnwand pn = new pinnwand(cl, nickname);
            JPanel newPanel = pn.createPanelContent();

            //neues Panel (ChooseAirline) laden
            gesamt.add(newPanel, BorderLayout.CENTER);
            gesamt.validate();
            gesamt.repaint();
        }
    }

    private class abonnieren implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int uid = cl.getUidFromNickname(nickname);
            int uid2 = cl.getUidFromNickname(nicknameAbo);
            abo a = cl.createAbo(uid, uid2);
            if (a != null) {
                JOptionPane.showMessageDialog(null, "User wurde abonniert", "Meldung", JOptionPane.OK_CANCEL_OPTION);
                gesamt.removeAll();
                pinnwand pin = new pinnwand(cl, nickname);
                JPanel newPanel = pin.createPanelContent();
                gesamt.add(newPanel, BorderLayout.CENTER);
                gesamt.validate();
                gesamt.repaint();
            } else {
                cl.aboLoeschen(uid, uid2);
                JOptionPane.showMessageDialog(null, "Abonnement wurde gelöscht!", "Meldung", JOptionPane.OK_CANCEL_OPTION);
                gesamt.removeAll();
                pinnwand2 pin = new pinnwand2(cl, nickname, nicknameAbo);
                JPanel newPanel = pin.createPanelContent();
                gesamt.add(newPanel, BorderLayout.CENTER);
                gesamt.validate();
                gesamt.repaint();
            }

        }
    }
}


