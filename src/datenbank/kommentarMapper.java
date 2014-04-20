/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datenbank;

import api.kommentar;
import api.kommentarImpl;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.Vector;

/**
 *
 * @author Atith
 */
public class kommentarMapper {

    private static kommentarMapper kMapper = null;

    protected kommentarMapper() {
    }

    public static kommentarMapper kMapper() {
        if (kMapper == null) {
            kMapper = new kommentarMapper();
        }
        return kMapper;
    }

    public kommentar kommentarErzeugen(kommentar newKommentar) {
        Connection con = Datenbankverbindung.connection();

        try {
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select max(kid) as maxid from kommentar");

            while (rs.next()) {
                try {
                    newKommentar.setKid(rs.getInt("maxid") + 1);

                    stmt = con.createStatement();

                    Timestamp tstamp = new Timestamp(System.currentTimeMillis());

                    stmt.executeUpdate("INSERT INTO kommentar (kid, erstellungszeitpunkt, text, bid, uid)" +
                            "values ('" +
                            newKommentar.getKid() + "','" +
                            tstamp + "','" +
                            newKommentar.getText() + "','" +
                            newKommentar.getBid() + "','" +
                            newKommentar.getUid() + "')");
                } catch (RemoteException rx) {
                    rx.printStackTrace();
                }
            }
        } catch (SQLException sx) {
            sx.printStackTrace();
        }
        return newKommentar;
    }

    public Vector<kommentar> getAllKommentare(int bid) {
        Connection con = Datenbankverbindung.connection();

        Vector<kommentar> result = new Vector<kommentar>();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM kommentar " +
                    "WHERE kommentar.bid='" + bid + "'");

            while (rs.next()) {
                try {
                    kommentar be = new kommentarImpl();
                    be.setTimestamp(rs.getTimestamp("erstellungszeitpunkt"));
                    be.setText(rs.getString("text"));
                    be.setKid(rs.getInt("kid"));
                    be.setUid(rs.getInt("uid"));
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
}
