/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datenbank;

import api.like;
import java.rmi.RemoteException;
import java.sql.*;

/**
 *
 * @author Atith
 */
public class likeMapper {

    private static likeMapper lMapper = null;

    protected likeMapper() {
    }

    public static likeMapper lMapper() {
        if (lMapper == null) {
            lMapper = new likeMapper();
        }
        return lMapper;
    }

    public like createLike(like l) {
        Connection con = Datenbankverbindung.connection();
        try {
            Statement stmt2 = con.createStatement();
            ResultSet rs2 = stmt2.executeQuery("SELECT * FROM `like` where bid='" + l.getBid() + "' and uid='" + l.getUid() + "'");
            if (!rs2.isBeforeFirst()) {
                try {
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT max(lid) AS maxid FROM `like`");

                    while (rs.next()) {
                        try {
                            l.setLid(rs.getInt("maxid") + 1);

                            int anzahl = 1;

                            Timestamp tstamp = new Timestamp(System.currentTimeMillis());

                            stmt2.executeUpdate("insert into `like` (lid, erstellungszeitpunkt, bid, anzahl, uid)" +
                                    "values ('" +
                                    l.getLid() + "','" +
                                    tstamp + "','" +
                                    l.getBid() + "','" +
                                    anzahl + "','" +
                                    l.getUid() + "')");

                        } catch (RemoteException rx) {
                            rx.printStackTrace();
                        }
                    }
                } catch (SQLException sx2) {
                    sx2.printStackTrace();
                }
            } else {
                return null;
            }
        } catch (RemoteException ex2) {
            ex2.printStackTrace();

        } catch (SQLException sx) {
            sx.printStackTrace();
        }
        return l;
    }

    public int getAllLikes(int bid) {
        int as = 0;
        // Verbindung zur Datenbank holen
        Connection con = Datenbankverbindung.connection();
        try {

            // Leeres SQL-Statement erstellen
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT sum(anzahl) as anzahl from `like` " +
                    "where bid='" + bid + "'");

            while (rs.next()) {

                int anzahl = rs.getInt("anzahl");

                return anzahl;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return as;
    }

    public void likeLoeschen(int uid, int bid) {
        Connection con = Datenbankverbindung.connection();
        try {
            Statement stmt = con.createStatement();

            stmt.executeUpdate("delete from `like` where " +
                    "uid ='" + uid + "' and bid ='" + bid + "'");
        } catch (SQLException sx) {
            sx.printStackTrace();
        }
    }
}
