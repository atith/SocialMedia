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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;
import java.util.Vector;
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
class eintragAnzeigen {

    private Client cl;
    private String nickname;
    private JTextArea textArea;
    private JPanel gesamt;
    private JTextArea kommentArea;

    public eintragAnzeigen(Client cl, String nickname) {
        this.cl = cl;
        this.nickname = nickname;
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

        int uid1 = 0;
        uid1 = cl.getUidFromNickname(nickname);

        Vector<beitrag> test = cl.getAllBeiträge(uid1);

        for (int i = 0; i < test.size(); i++) {
            try {
                String beitrag = test.elementAt(i).getText();

                Vector<kommentar> test2 = cl.getAllKommentare(test.elementAt(i).getBid());
                this.textArea = new JTextArea(beitrag);
                textArea.setPreferredSize(null);
                textArea.setFont(new Font("Arial", Font.ITALIC, 14));

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
                textArea.addKeyListener(new BeitragEdit(textArea));
                //d.gridheight = GridBagConstraints.REMAINDER;
                panel2.add(textArea, d);

                d = new GridBagConstraints();
                d.insets = set;
                d.gridx = 1;
                d.gridy = 0;
                d.gridwidth = GridBagConstraints.REMAINDER;
                panel2.add(new JLabel(nickname + " " + test.elementAt(i).getTimestamp()), d);
                d = new GridBagConstraints();
                d.insets = set;
                d.gridx = 1;
                d.gridy = 2;

                like likes2 = cl.getAllLikes(bid, uid1);

                JButton likes = new JButton("Gefällt mir");
                if (likes2.getAnzahl() != 0 && likes2.getUid() == uid1) {
                    likes.setText("Gefällt mir nicht mehr");
                }

                String bid0 = Integer.toString(bid);
                likes.setActionCommand(bid0);
                likes.addActionListener(new likes());

                panel2.add(likes, d);
                d = new GridBagConstraints();
                d.insets = set;
                d.gridx = 2;
                d.gridy = 2;
                JButton comment = new JButton("Kommentar");
                String bid1 = Integer.toString(bid);
                comment.setActionCommand(bid1);
                comment.addActionListener(new Kommentar());
                panel2.add(comment, d);

                int j = 0;


                for (int h = 0; h < test2.size(); h++) {
                    JPanel panel3 = new JPanel();
                    panel3.setBorder(BorderFactory.createLineBorder(Color.red));
                    String kommentar = test2.elementAt(h).getText();
                    this.kommentArea = new JTextArea(kommentar);
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

                String lk = Integer.toString(likes2.getAnzahl());
                panel2.add(new JLabel("Beitrag wurde " + lk + " mal geliked"), d);
                //  textArea.add(new JButton("Fert"), BorderLayout);
                panel2.setBorder(BorderFactory.createLineBorder(Color.black));
                panel.add(panel2, c);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
        Vector<abo> blah = cl.getUid2FromUid(uid1);

        for (int x = 0; x < blah.size(); x++) {
            try {
                int ohje = 0;
                ohje = blah.elementAt(x).getUid2();
                Vector<beitrag> test3 = cl.getAllBeiträge(ohje);
                for (int i = 0; i < test3.size(); i++) {
                    try {
                        String beitrag = test3.elementAt(i).getText();
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
                        int bid = test3.elementAt(i).getBid();
                        String tBid = Integer.toString(bid);
                        textArea.setName(tBid);
                        textArea.addKeyListener(new BeitragEdit(textArea));
                        //d.gridheight = GridBagConstraints.REMAINDER;
                        panel2.add(textArea, d);
                        d = new GridBagConstraints();
                        d.insets = set;
                        d.gridx = 1;
                        d.gridy = 0;
                        d.gridwidth = GridBagConstraints.REMAINDER;
                        String nick = cl.getNickFromBid(bid);
                        panel2.add(new JLabel(nick + " " + test3.elementAt(i).getTimestamp()), d);
                        d = new GridBagConstraints();
                        d.insets = set;
                        d.gridx = 1;
                        d.gridy = 2;

                        like likes2 = cl.getAllLikes(bid, uid1);

                        JButton likes = new JButton("Gefällt mir");
                        if (likes2.getAnzahl() != 0 && likes2.getUid() == uid1) {
                            likes.setText("Gefällt mir nicht mehr");
                        }
                        String bid0 = Integer.toString(bid);
                        likes.setActionCommand(bid0);
                        likes.addActionListener(new likes());
                        panel2.add(likes, d);

                        d = new GridBagConstraints();
                        d.insets = set;
                        d.gridx = 2;
                        d.gridy = 2;
                        JButton comment = new JButton("Kommentar");
                        String bid1 = Integer.toString(bid);
                        comment.setActionCommand(bid1);
                        comment.addActionListener(new Kommentar());
                        panel2.add(comment, d);
                        Vector<kommentar> test2 = cl.getAllKommentare(test3.elementAt(i).getBid());
                        int j = 0;
                        for (int h = 0; h < test2.size(); h++) {
                            JPanel panel3 = new JPanel();
                            panel3.setBorder(BorderFactory.createLineBorder(Color.red));
                            String kommentar = test2.elementAt(h).getText();
                            this.kommentArea = new JTextArea(kommentar);
                            int uid = test2.elementAt(h).getUid();
                                if (uid == ohje) {
                                    this.kommentArea.setEnabled(false);
                                }                           
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
            } catch (RemoteException ex2) {
                ex2.printStackTrace();
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

    class likes implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String bid1 = e.getActionCommand();
            int bid = Integer.parseInt(bid1);
            int uid = cl.getUidFromNickname(nickname);
            like l = cl.createLike(bid, uid);

            if (l != null) {
                JOptionPane.showMessageDialog(null, "Beitrag wurde geliked", "Meldung", JOptionPane.OK_CANCEL_OPTION);
                gesamt.removeAll();
                eintragAnzeigen anz = new eintragAnzeigen(cl, nickname);
                JPanel newPanel = anz.createPanelContent();
                gesamt.add(newPanel, BorderLayout.CENTER);
                gesamt.validate();
                gesamt.repaint();
            } else {
                cl.deleteLike(bid, uid);
                JOptionPane.showMessageDialog(null, "Like wurde entfernt!", "Meldung", JOptionPane.OK_CANCEL_OPTION);

                gesamt.removeAll();
                eintragAnzeigen anz = new eintragAnzeigen(cl, nickname);
                JPanel newPanel = anz.createPanelContent();
                gesamt.add(newPanel, BorderLayout.CENTER);

                gesamt.validate();
                gesamt.repaint();

            }
        }
    }

    private class Kommentar implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String bidB = e.getActionCommand();
            int bid = Integer.parseInt(bidB);
            gesamt.removeAll();
            kommentarErzeugen create = new kommentarErzeugen(cl, nickname, bid);
            JPanel newPanel = create.createPanelContent();
            gesamt.add(newPanel, BorderLayout.CENTER);
            gesamt.validate();
            gesamt.repaint();
        }
    }

    private class BeitragEdit implements KeyListener {

        JTextArea textEdit;

        public BeitragEdit(JTextArea text) {
            this.textEdit = text;
        }

        public void keyTyped(KeyEvent arg0) {
        }

        public void keyPressed(KeyEvent e) {

            int bid3 = Integer.parseInt(textEdit.getName());

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                String beitragEdit = textEdit.getText();
                beitrag be = cl.BeitragEditieren(bid3, beitragEdit);

                if (be == null) {
                    JOptionPane.showMessageDialog(null, "Beitrag konnnte nicht bearbeitet werden!", "Meldung", JOptionPane.OK_CANCEL_OPTION);
                    gesamt.removeAll();
                    eintragAnzeigen anz = new eintragAnzeigen(cl, nickname);
                    JPanel newPanel = anz.createPanelContent();
                    gesamt.add(newPanel, BorderLayout.CENTER);
                    gesamt.validate();
                    gesamt.repaint();
                } else {
                    gesamt.removeAll();
                    eintragAnzeigen anz = new eintragAnzeigen(cl, nickname);
                    JPanel newPanel = anz.createPanelContent();
                    gesamt.add(newPanel, BorderLayout.CENTER);
                    gesamt.validate();
                    gesamt.repaint();
                }
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

                int bid4 = Integer.parseInt(textEdit.getName());

                cl.beitragLoeschen(bid4);
                gesamt.removeAll();
                eintragAnzeigen anz = new eintragAnzeigen(cl, nickname);
                JPanel newPanel = anz.createPanelContent();
                gesamt.add(newPanel, BorderLayout.CENTER);
                gesamt.validate();
                gesamt.repaint();
            }
        }

        public void keyReleased(KeyEvent arg0) {
        }
    }
}

