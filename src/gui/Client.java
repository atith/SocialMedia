/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import api.abo;
import api.beitrag;
import api.kommentar;
import api.like;
import api.user;
import api.userImpl;
import java.rmi.Naming;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Vector;
import verwaltung.verwaltung;

/**
 *
 * @author Atith
 */
public class Client {

    /**
     * Holt das verwaltungs-objekt aus der RMI Registry
     */
    verwaltung verwaltung = null;

    private void initServerConnection() {

        try {
            //Hole das Objekt Gruppenkalenderverwaltung aus der RMI Registry
            this.verwaltung = (verwaltung) Naming.lookup("rmi://localhost:1099/server");
            System.out.println("Verbindung steht...");
        } catch (MalformedURLException murle) {
            System.out.println("MalformedURLException");
            System.out.println(murle);
        } catch (RemoteException re) {
            System.out.println("RemoteException");
            System.out.println(re);
        } catch (NotBoundException e) {
            System.out.println("NotBoundException");
            System.out.println(e);
        }
    }

    user ueberpruefungAnmeldung(String nickname, String passwort) {
        if (this.verwaltung == null) {
            this.initServerConnection();
        }
        try {

            //Hole die Teilnehmer
            Vector<user> u = verwaltung.findAllUser();

            //Der Ergebnisvector wird durchlaufen
            for (user us : u) {
                if (us.getNickname().equals(nickname) && us.getPasswort().equals(passwort)) {
                    return us;
                }
            }

        } catch (RemoteException re) {
            System.out.println("RemoteException");
            System.out.println(re);

        }

        return null;
    }

    public user anlegenUser(String nachname, String vorname, String nickname, String passwort) {
        user responseUser = null;

        if (this.verwaltung == null) {
            this.initServerConnection();
        }

        try {
            responseUser = verwaltung.anlegenUser(nachname, vorname, nickname, passwort);

            if (responseUser != null) {
                System.out.println("Neuer User wurde angelegt!");
            } else {
                System.out.println("User konnte nicht angelegt werden!");
            }
        } catch (RemoteException rx) {
            rx.printStackTrace();
        }
        return responseUser;
    }

    Hashtable getUserData(String nickname) {
        if (this.verwaltung == null) {
            this.initServerConnection();
        }

        Hashtable hashtable = new Hashtable();

        try {
            user myUser = this.verwaltung.getUserData(nickname);

            hashtable.put("uid", myUser.getUid());
            hashtable.put("vorname", myUser.getVorname());
            hashtable.put("nachname", myUser.getName());
            hashtable.put("nick", myUser.getNickname());
            hashtable.put("passwort", myUser.getPasswort());
        } catch (RemoteException rx) {
            System.out.println(rx);
        }
        return hashtable;
    }

    user profilBearbeiten(int uid, String vorname, String name, String nick, String pass) {
        user responseUser = null;

        if (this.verwaltung == null) {
            this.initServerConnection();
        }

        try {
            user us = new userImpl();
            us.setUid(uid);
            us.setVorname(vorname);
            us.setName(name);
            us.setNickname(nick);
            us.setPasswort(pass);

            responseUser = verwaltung.userBearbeiten(uid, vorname, name, nick, pass);

        } catch (RemoteException rx) {
            if (responseUser != null) {
                System.out.println("User wurde bearbeitet");
            } else {
                System.out.println("User konnte nicht bearbeitet werden, weil:" + rx);
            }
        }
        return responseUser;
    }

    void profilLoeschen(Client cl, String nickname) {
        //Wenn bisher keine Verbindung hergstellt wurde, wird eine aufgebaut
        if (this.verwaltung == null) {
            this.initServerConnection();
        }

        try {

            Vector<user> us = verwaltung.getUserByNickname(nickname);

            for (user u : us) {

                int uid = u.getUid();

                verwaltung.profilLoeschen(uid);

            }
        } catch (RemoteException rx) {
            System.out.println(rx);
        }
    }

    beitrag beitragErzeugen(String text, int uid) {
        beitrag responseBeitrag = null;

        if (this.verwaltung == null) {
            this.initServerConnection();
        }

        try {
            responseBeitrag = verwaltung.beitragErzeugen(text, uid);

            if (responseBeitrag != null) {
                System.out.println("Neuer Beitrag wurde angelegt!");
            } else {
                System.out.println("Beitrag konnte nicht angelegt werden!");
            }
        } catch (RemoteException rx) {
            rx.printStackTrace();
        }
        return responseBeitrag;
    }

    int getUidFromNickname(String nickname) {
        int uid = 0;

        if (this.verwaltung == null) {
            this.initServerConnection();
        }

        try {
            uid = verwaltung.getUidFromNickname(nickname);
            return uid;

        } catch (RemoteException ex) {
            System.out.println(ex);
        }
        return uid;
    }

    Vector<beitrag> getAllBeiträge(String nickname) {
        if (this.verwaltung == null) {
            this.initServerConnection();
        }

        Vector<beitrag> rowData = new Vector<beitrag>();
        try {
            //Hole die Teilnehmer
            Vector<beitrag> beitragListe = verwaltung.getAllBeiträge(nickname);

            for (beitrag b : beitragListe) {
                rowData.addElement(b);
            }

        } catch (RemoteException re) {
            System.out.println("RemoteException");
            System.out.println(re);

        }

        return rowData;
    }

    public user sucheUserByNickname(String nickname) {
        if (this.verwaltung == null) {
            this.initServerConnection();
        }

        try {
            //Hole die Teilnehmer
            Vector<user> u = verwaltung.findAllUser();

            //Der Ergebnisvector wird durchlaufen
            for (user us : u) {
                if (us.getNickname().equals(nickname)) {
                    return us;
                }
            }

        } catch (RemoteException re) {
            System.out.println("RemoteException");
            System.out.println(re);
        }

        return null;
    }

    public int getBidFromNickname(String nickname) {
        int bid = 0;
        if (this.verwaltung == null) {
            this.initServerConnection();
        }
        try {
            bid = verwaltung.getBidFromNickname(nickname);
            return bid;

        } catch (RemoteException rx) {
            rx.printStackTrace();
        }
        return bid;
    }

    like createLike(int bid, int uid) {
        like responseLike = null;

        if (this.verwaltung == null) {
            this.initServerConnection();
        }

        try {
            responseLike = verwaltung.createLike(bid, uid);

            if (responseLike != null) {
                System.out.println("Beitrag wurde geliked");
            } else {
                System.out.println("Beitrag konnte nicht geliked werden!");
            }
        } catch (RemoteException rx) {
            rx.printStackTrace();
        }
        return responseLike;
    }

    int getAllLikes(int bid) {
        int anzahl = 0;
        if (this.verwaltung == null) {
            this.initServerConnection();
        }
        try {
            anzahl = verwaltung.getAllLikes(bid);
            return anzahl;

        } catch (RemoteException rx) {
            rx.printStackTrace();
        }
        return anzahl;
    }

    Vector<kommentar> getAllKommentare(int uid) {
        if (this.verwaltung == null) {
            this.initServerConnection();
        }

        Vector<kommentar> rowDataK = new Vector<kommentar>();
        try {
            Vector<kommentar> kommentarListe = verwaltung.getAllKommentare(uid);

            for (kommentar c : kommentarListe) {
                rowDataK.addElement(c);
            }
        } catch (RemoteException r) {
            System.out.println("RemoteException");
            System.out.println(r);
        }
        return rowDataK;
    }

    kommentar kommentarErzeugen(String text, int bid) {
        kommentar responseKommentar = null;

        if (this.verwaltung == null) {
            this.initServerConnection();
        }

        try {
            responseKommentar = verwaltung.kommentarErzeugen(text, bid);

            if (responseKommentar != null) {
                System.out.println("Neuer Kommentar wurde angelegt!");
            } else {
                System.out.println("Kommentar konnte nicht angelegt werden!");
            }
        } catch (RemoteException rx) {
            rx.printStackTrace();
        }
        return responseKommentar;
    }

    void deleteLike(int bid, int uid) {

        //Wenn bisher keine Verbindung hergstellt wurde, wird eine aufgebaut
        if (this.verwaltung == null) {
            this.initServerConnection();
        }
        try {
            verwaltung.likeLoeschen(uid, bid);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    abo createAbo(int uid, int uid2) {
        abo responseAbo = null;

        if (this.verwaltung == null) {
            this.initServerConnection();
        }

        try {
            responseAbo = verwaltung.createAbo(uid, uid2);

            if (responseAbo != null) {
                System.out.println("User wurde abonniert");
            } else {
                System.out.println("User konnte nicht abonniert werden!");
            }
        } catch (RemoteException rx) {
            rx.printStackTrace();
        }
        return responseAbo;
    }

    Vector<user> getAbonenntenToUser(int uid, int uid2) {
        if (this.verwaltung == null) {
            this.initServerConnection();
        }

        Vector<user> rowData = new Vector<user>();
        try {
            //Hole die Teilnehmer
            Vector<user> userListe = verwaltung.getAbonenntenToUser(uid, uid2);

            for (user u : userListe) {
                rowData.addElement(u);
            }

        } catch (RemoteException re) {
            System.out.println("RemoteException");
            System.out.println(re);

        }

        return rowData;
    }

    int getUid2FromUid(int uid) {
        int uid2 = 0;
        if (this.verwaltung == null) {
            this.initServerConnection();
        }
        try {
            uid2 = verwaltung.getUid2FromUid(uid);
            return uid2;

        } catch (RemoteException rx) {
            rx.printStackTrace();
        }
        return uid2;
    }

    void aboLoeschen(int uid, int uid2) {

        //Wenn bisher keine Verbindung hergstellt wurde, wird eine aufgebaut
        if (this.verwaltung == null) {
            this.initServerConnection();
        }
        try {
            verwaltung.aboLoeschen(uid, uid2);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}


    






