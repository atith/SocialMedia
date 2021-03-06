/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datenbank;

import api.beitrag;
import api.beitragImpl;
import api.kommentar;
import api.kommentarImpl;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.Vector;

/**
 *
 * @author Atith
 */
public class beitragMapper {

    private static beitragMapper bMapper = null;

    protected beitragMapper() {
    }

    public static beitragMapper bMapper() {
        if (bMapper == null) {
            bMapper = new beitragMapper();
        }
        return bMapper;
    }

    public beitrag beitragErzeugen(beitrag newBeitrag) {
        Connection con = Datenbankverbindung.connection();

        try {
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select max(bid) as maxid from beitrag");

            while (rs.next()) {
                try {
                    newBeitrag.setBid(rs.getInt("maxid") + 1);

                    stmt = con.createStatement();

                    Timestamp tstamp = new Timestamp(System.currentTimeMillis());

                    stmt.executeUpdate("insert into beitrag (bid, erstellungszeitpunkt, text, uid)" +
                            "values ('" +
                            newBeitrag.getBid() + "','" +
                            tstamp + "','" +
                            newBeitrag.getText() + "','" +
                            newBeitrag.getUid() + "')");
                } catch (RemoteException rx) {
                    rx.printStackTrace();
                }
            }
        } catch (SQLException sx) {
            sx.printStackTrace();
        }
        return newBeitrag;
    }

    public int getUidFromNickname(String nickname) {
        int as = 0;
        // Verbindung zur Datenbank holen
        Connection con = Datenbankverbindung.connection();
        try {

            // Leeres SQL-Statement erstellen
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT uid from user " +
                    "where nickname='" + nickname + "'");
            rs.next();

            int uid = rs.getInt("uid");

            return uid;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return as;
    }

    public Vector<beitrag> getAllBeiträge(int uid) {
        Connection con = Datenbankverbindung.connection();

        Vector<beitrag> result = new Vector<beitrag>();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT beitrag.text, beitrag.erstellungszeitpunkt, beitrag.bid FROM beitrag " +
                    "Left join user on beitrag.uid = user.uid " +
                    "WHERE user.uid='" + uid + "'");
            while (rs.next()) {
                try {
                    beitrag be = new beitragImpl();
                    be.setTimestamp(rs.getTimestamp("erstellungszeitpunkt"));
                    be.setText(rs.getString("text"));
                    be.setBid(rs.getInt("bid"));
                    result.addElement(be);
                } catch (RemoteException ex) {
                    ex.printStackTrace();

                    break;
                }
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
        }

        return result;
    }

    public int getBidFromNickname(String nickname) {
        int as = 0;
        // Verbindung zur Datenbank holen
        Connection con = Datenbankverbindung.connection();
        try {

            // Leeres SQL-Statement erstellen
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT b.bid from user u left join beitrag b on u.uid = b.uid " +
                    "where nickname='" + nickname + "'");

            rs.next();

            int bid = rs.getInt("bid");

            return bid;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return as;
    }

    public Vector<kommentar> getAllKommentare(int bid) {
        Connection con = Datenbankverbindung.connection();

        Vector<kommentar> result = new Vector<kommentar>();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT kommentar.kid, kommentar.erstellungszeitpunkt, kommentar.text  FROM kommentar " +
                    "WHERE kommentar.bid='" + bid + "'");

            while (rs.next()) {
                try {
                    kommentar be = new kommentarImpl();
                    be.setBid(rs.getInt("kid"));
                    be.setText(rs.getString("text"));
                    be.setTimestamp(rs.getTimestamp("erstellungszeitpunkt"));
                    result.addElement(be);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                    break;
                }
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
        return result;
    }

    public beitrag beitragEditieren(beitrag newEditBeitrag) {
        Connection con = Datenbankverbindung.connection();
        try {
            Statement stmt = con.createStatement();
            Timestamp tstamp = new Timestamp(System.currentTimeMillis());

            stmt.executeUpdate("UPDATE beitrag " +
                    "SET text='" + newEditBeitrag.getText() + "', " +
                    "erstellungszeitpunkt='" + tstamp + "' " +
                    "WHERE bid=" + newEditBeitrag.getBid());

        } catch (SQLException e2) {
            e2.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return newEditBeitrag;
    }

    public void beitragLoeschen(int bid) {
        Connection con = Datenbankverbindung.connection();
        try {
            Statement stmt = con.createStatement();

            stmt.executeUpdate("delete from `beitrag` where " +
                    "bid ='" + bid + "'");
        } catch (SQLException sx) {
            sx.printStackTrace();
        }
    }

    public int getUidFromBid(int bid2) {
        int as = 0;
        // Verbindung zur Datenbank holen
        Connection con = Datenbankverbindung.connection();
        try {

            // Leeres SQL-Statement erstellen
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT uid from beitrag " +
                    "where bid='" + bid2 + "'");

            rs.next();

            int uid = rs.getInt("uid");

            return uid;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return as;
    }

    public String getNickFromBid(int bid) {
        String nick2 = "";
        // Verbindung zur Datenbank holen
        Connection con = Datenbankverbindung.connection();
        try {

            // Leeres SQL-Statement erstellen
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT u.nickname from beitrag b " +
                    "left join user u on b.uid = u.uid " +
                    "where bid='" + bid + "'");

            rs.next();

            String nick = rs.getString("nickname");

            return nick;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nick2;
    }
}
