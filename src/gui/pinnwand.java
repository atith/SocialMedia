/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import api.abo;
import api.user;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Atith
 */
public class pinnwand {

    public JPanel gesamt;
    private Client cl;
    private String nickname;
    private JTextArea kommentar;
    private JTextField searchField;
    private JList abonennten;
    private JTextField text;

    public pinnwand(Client cl, String nickname) {
        this.cl = cl;
        this.nickname = nickname;
    }

    JPanel createPanelContent() {

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        GridBagConstraints c = new GridBagConstraints();


        JLabel pinnwand = new JLabel("Pinnwand");
        pinnwand.setFont(new Font("Arial", Font.BOLD, 18));
        c.ipady = 0;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 0;
        panel.add(pinnwand, c);

        JPanel search = new JPanel(new GridBagLayout());
        search.setBorder(BorderFactory.createLineBorder(Color.black));
        GridBagConstraints d = new GridBagConstraints();
        JLabel searchText = new JLabel("Suche");
        searchText.setFont(new Font("Arial", Font.BOLD, 18));
        d.ipady = 0;
        d.weightx = 0.0;
        d.gridwidth = 1;
        d.gridx = 0;
        d.gridy = 0;
        d.insets = new Insets(2, 0, 0, 500);
        search.add(searchText, d);

        this.searchField = new JTextField();
        searchText.setLabelFor(searchField);
        this.searchField.setPreferredSize(new Dimension(200, 25));
        d.fill = GridBagConstraints.HORIZONTAL;
        d.ipady = 0;
        d.weightx = 0.0;
        d.gridwidth = 1;
        d.gridx = 0;
        d.gridy = 1;
        d.insets = new Insets(0, 3, 0, 300);
        search.add(searchField, d);


        JPanel abo2 = new JPanel(new GridBagLayout());
        abo2.setBorder(BorderFactory.createLineBorder(Color.black));
        GridBagConstraints e = new GridBagConstraints();
        JLabel aboText = new JLabel("Abonennten");
        aboText.setFont(new Font("Arial", Font.BOLD, 18));
        Insets set = new Insets(3, 3, 3, 3);
        e.ipady = 0;
        e.weightx = 0.0;
        e.gridwidth = GridBagConstraints.REMAINDER;
        e.gridx = 0;
        e.gridy = 0;
        e.insets = new Insets(0, 50, 220, 50);
        abo2.add(aboText, e);

        int uid = cl.getUidFromNickname(nickname);

        Vector<abo> blah = cl.getUid2FromUid(uid);
        Vector<user> users = null;
        for (int h = 0; h < blah.size(); h++) {
            try {
                int ohje = 0;
                ohje = blah.elementAt(h).getUid2();
                users = cl.getAbonenntenToUser(uid, ohje);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
<<<<<<< HEAD
        if (users != null) {
            for (int i = 0; i < users.size(); i++) {
                try {
                    String abonennt = users.elementAt(i).getNickname();
                    this.text = new JTextField(abonennt);
                    text.setEnabled(false);
                    text.addMouseListener(new aboAnzeigen());
                    e.insets = set;
                    e.ipady = 0;
                    //e.anchor = GridBagConstraints.WEST;
                    e.weightx = 0.0;
                    e.gridwidth = 1;
                    e.gridx = 0;
                    e.gridy++;

                    JPanel pane = new JPanel(new GridBagLayout());
                    GridBagConstraints f = new GridBagConstraints();
                    f.insets = set;
                    f.gridx = 0;
                    //f.gridy++;
                    f.gridwidth = GridBagConstraints.REMAINDER;
                    f.gridheight = GridBagConstraints.REMAINDER;
                    pane.add(text, e);
                    abo2.add(pane, f);

                } catch (RemoteException rx) {
                    rx.printStackTrace();
                }
=======
        for (int i = 0; i < users.size(); i++) {
            try {
                String abonennt = users.elementAt(i).getNickname();
                this.text = new JTextField(abonennt);
                text.setEnabled(false);
                text.addMouseListener(new aboAnzeigen());
                e.insets = set;
                e.ipady = 0;
                //e.anchor = GridBagConstraints.WEST;
                e.weightx = 0.0;
                e.gridwidth = 1;
                e.gridx = 0;
                e.gridy++;

                JPanel pane = new JPanel(new GridBagLayout());
                GridBagConstraints f = new GridBagConstraints();
                f.insets = set;
                f.gridx = 0;
                //f.gridy++;
                f.gridwidth = GridBagConstraints.REMAINDER;
                f.gridheight = GridBagConstraints.REMAINDER;
                pane.add(text, e);
                abo2.add(pane, f);
            } catch (RemoteException rx) {
                rx.printStackTrace();
>>>>>>> e056f15dff419f743b07bac8b7b6db20f2125f94
            }
        }


        GridBagConstraints f = new GridBagConstraints();
        JButton suche = new JButton();
        try {
            Image img = ImageIO.read(getClass().getResource("/resources/search-icon.jpg"));
            suche.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            System.out.println(ex);
        }

        suche.setToolTipText("Suchen");
        suche.addActionListener(new suche());
        suche.setPreferredSize(new Dimension(25, 25));
        f.ipady = 0;
        f.weightx = 0.0;
        f.gridwidth = 1;
        f.gridx = 0;
        f.gridy = 1;
        search.add(suche, f);
        JButton profil = new JButton();
        try {
            Image img = ImageIO.read(getClass().getResource("/resources/user_edit.png"));
            profil.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            System.out.println(ex);
        }

        profil.setToolTipText("Profil bearbeiten");
        profil.addActionListener(new profil());
        profil.setPreferredSize(new Dimension(25, 25));
        f.fill = GridBagConstraints.HORIZONTAL;
        f.ipady = 0;
        f.weightx = 0.0;
        f.gridwidth = 1;
        f.gridx = 2;
        f.gridy = 1;
        search.add(profil, f);
        JButton loeschen = new JButton();
        try {
            Image img = ImageIO.read(getClass().getResource("/resources/Remove_user_delete.png"));
            loeschen.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            System.out.println(ex);
        }

        loeschen.addActionListener(new loeschen());
        loeschen.setToolTipText("Profil löschen");
        loeschen.setPreferredSize(new Dimension(25, 25));
        f.fill = GridBagConstraints.HORIZONTAL;
        f.ipady = 0;
        f.weightx = 0.0;
        f.gridwidth = 1;
        f.gridx = 3;
        f.gridy = 1;
        f.insets = new Insets(0, 10, 0, 0);
        search.add(loeschen, f);
//        JButton comment = new JButton();
//        GridBagConstraints t = new GridBagConstraints();
//        try {
//            Image img = ImageIO.read(getClass().getResource("/resources/comment.png"));
//            comment.setIcon(new ImageIcon(img));
//        } catch (IOException ex) {
//            System.out.println(ex);
//        }
//        comment.setToolTipText("kommentieren");
//        comment.addActionListener(new profil());
//        comment.setPreferredSize(new Dimension(25, 25));
//        t.ipady = 0;
//        t.weightx = 0.0;
//        t.gridwidth = 1;
//        t.gridx = 1;
//        t.gridy = 1;
//        t.insets = new Insets(0, 320, 340, 0);
//        panel.add(comment, t);
//        JButton like = new JButton();
        GridBagConstraints s = new GridBagConstraints();
//        try {
//            Image img = ImageIO.read(getClass().getResource("/resources/like.jpg"));
//            like.setIcon(new ImageIcon(img));
//        } catch (IOException ex) {
//            System.out.println(ex);
//        }
//        like.setToolTipText("like");
//        like.addActionListener(new profil());
//        like.setPreferredSize(new Dimension(40, 25));
//        s.ipady = 0;
//        s.weightx = 0.0;
//        s.gridwidth = 1;
//        s.gridx = 2;
//        s.gridy = 1;
//        s.insets = new Insets(0, 0, 340, 0);
//        panel.add(like, s);
        JButton beitrag = new JButton("Neuen Beitrag erstellen");
        s.ipady = 0;
        s.weightx = 0.0;
        s.gridwidth = 1;
        s.gridx = 1;
        s.gridy = 1;
        s.insets = new Insets(0, 0, 250, 0);
        panel.add(beitrag, s);
        beitrag.addActionListener(new beitrag());
        JButton anzeigen = new JButton("Beiträge anzeigen");
        s.ipady = 0;
        s.weightx = 0.0;
        s.gridwidth = 1;
        s.gridx = 1;
        s.gridy = 1;
        s.insets = new Insets(10, 0, 340, 0);
        panel.add(anzeigen, s);
        anzeigen.addActionListener(new anzeigen());

        this.gesamt = new JPanel(new BorderLayout());
        gesamt.add(panel, BorderLayout.CENTER);
        gesamt.add(search, BorderLayout.NORTH);
        gesamt.add(abo2, BorderLayout.WEST);
        return gesamt;
    }

    class profil implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            gesamt.removeAll();
            profilBearbeiten edit = new profilBearbeiten(cl, nickname);
            JPanel newPanel = edit.createPanelContent();
            gesamt.add(newPanel, BorderLayout.CENTER);
            gesamt.validate();
            gesamt.repaint();
        }
    }

    class loeschen implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            gesamt.removeAll();
            pinnwand.this.cl.profilLoeschen(cl, nickname);
            //Meldung erzeugen
            JOptionPane.showMessageDialog(null, "User wurde entfernt!!", "Meldung", JOptionPane.OK_CANCEL_OPTION);
            //Leeren und sicht wechseln
            login log = new login(cl);
            JPanel newPanel = log.createPanelContent();
            gesamt.add(newPanel, BorderLayout.CENTER);
            gesamt.validate();
            gesamt.repaint();
        }
    }

    class suche implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            user us = cl.sucheUserByNickname(searchField.getText().trim());

            String nicknameAbo = null;

            if (us != null) {
                try {
                    nicknameAbo = us.getNickname();
                } catch (RemoteException rx) {
                    rx.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, "Gesuchter Nickname befindet sich in der Datenbank", "Meldung", JOptionPane.OK_CANCEL_OPTION);
                gesamt.removeAll();
                pinnwand2 pin = new pinnwand2(cl, nickname, nicknameAbo);
                JPanel newPanel = pin.createPanelContent();
                gesamt.add(newPanel, BorderLayout.CENTER);
                gesamt.validate();
                gesamt.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Gesuchter Nickname befindet sich nicht in der Datenbank", "Meldung", JOptionPane.OK_CANCEL_OPTION);
                gesamt.removeAll();
                pinnwand pin = new pinnwand(cl, nickname);
                JPanel newPanel = pin.createPanelContent();
                gesamt.add(newPanel, BorderLayout.CENTER);
                gesamt.validate();
                gesamt.repaint();
            }
        }
    }

    class beitrag implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            gesamt.removeAll();
            eintragErzeugen create = new eintragErzeugen(cl, nickname);
            JPanel newPanel = create.createPanelContent();
            gesamt.add(newPanel, BorderLayout.CENTER);
            gesamt.validate();
            gesamt.repaint();
        }
    }

    class anzeigen implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            gesamt.removeAll();
            eintragAnzeigen create = new eintragAnzeigen(cl, nickname);
            JPanel newPanel = create.createPanelContent();
            gesamt.add(newPanel, BorderLayout.CENTER);
            gesamt.validate();
            gesamt.repaint();
        }
    }

    class aboAnzeigen implements MouseListener {

        String nick = text.getText().trim();

//
//        public void actionPerformed(ActionEvent e) {
//
//            String nicknameAbo = e.getActionCommand();
//
//            gesamt.removeAll();
//            pinnwand2 pin = new pinnwand2(cl, nickname, nicknameAbo);
//            JPanel newPanel = pin.createPanelContent();
//            gesamt.add(newPanel, BorderLayout.CENTER);
//            gesamt.validate();
//            gesamt.repaint();
//        }
        public void mouseClicked(MouseEvent e) {
            gesamt.removeAll();
            pinnwand2 pin = new pinnwand2(cl, nickname, nick);
            JPanel newPanel = pin.createPanelContent();
            gesamt.add(newPanel, BorderLayout.CENTER);
            gesamt.validate();
            gesamt.repaint();
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }
}


