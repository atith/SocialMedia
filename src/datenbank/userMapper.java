/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datenbank;

import api.user;
import api.userImpl;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.Vector;

/**
 *
 * @author Atith
 */
public class userMapper {

    private static userMapper uMapper = null;

    protected userMapper() {
    }

    public static userMapper uMapper() {
        if (uMapper == null) {
            uMapper = new userMapper();
        }
        return uMapper;
    }

    public Vector<user> findAllUser() {
        Connection con = Datenbankverbindung.connection();

        Vector<user> result = new Vector<user>();

        try {

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT erstellungszeitpunkt, vorname, name, nickname, passwort FROM user order by nickname");

            while (rs.next()) {
                try {

                    user us = new userImpl();
                    us.setEinrichtungszeitpunkt(rs.getTimestamp("erstellungszeitpunkt"));
                    us.setVorname(rs.getString("vorname"));
                    us.setName(rs.getString("name"));
                    us.setNickname(rs.getString("nickname"));
                    us.setPasswort(rs.getString("passwort"));

                    result.addElement(us);
                } catch (RemoteException rx) {
                    rx.printStackTrace();
                    break;
                }
            }
        } catch (SQLException sq) {
            sq.printStackTrace();
        }
        return result;
    }

    public user anlegenUser(user newUser) {
        Connection con = Datenbankverbindung.connection();

        try {
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select max(uid) as maxid from user");

            while (rs.next()) {
                try {
                    newUser.setUid(rs.getInt("maxid") + 1);

                    stmt = con.createStatement();

                    Timestamp tstamp = new Timestamp(System.currentTimeMillis());

                    stmt.executeUpdate("insert into user (uid, erstellungszeitpunkt, vorname, name, nickname, passwort)" +
                            "values ('" +
                            newUser.getUid() + "','" +
                            tstamp + "','" +
                            newUser.getVorname() + "','" +
                            newUser.getName() + "','" +
                            newUser.getNickname() + "','" +
                            newUser.getPasswort() + "')");
                } catch (RemoteException rx) {
                    rx.printStackTrace();
                }
            }
        } catch (SQLException sx) {
            sx.printStackTrace();
        }
        return newUser;
    }

    public user getUser(String nickname) {
        Connection con = Datenbankverbindung.connection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT uid, vorname, name, nickname, passwort FROM user " +
                    "WHERE nickname='" + nickname + "'");
            while (rs.next()) {
                try {
                    user us = new userImpl();
                    us.setUid(rs.getInt("uid"));
                    us.setName(rs.getString("name"));
                    us.setVorname(rs.getString("vorname"));
                    us.setNickname(rs.getString("nickname"));
                    us.setPasswort(rs.getString("passwort"));
                    return (us);
                } catch (RemoteException ex) {
                    ex.printStackTrace();

                    break;
                }
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
        }

        return null;
    }

    public user userBearbeiten(user newUser) {
        Connection con = Datenbankverbindung.connection();
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE user " +
                    "SET vorname='" + newUser.getVorname() + "', " +
                    "name='" + newUser.getName() + "', " +
                    "nickname='" + newUser.getNickname() + "', " +
                    "passwort='" + newUser.getPasswort() + "' " +
                    "WHERE uid=" + newUser.getUid());

        } catch (SQLException e2) {
            e2.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return newUser;
    }

    public void profilLoeschen(int uid) {
        Connection con = Datenbankverbindung.connection();
        try {
            java.sql.Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM user  " +
                    "WHERE uid= '" + uid + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vector<user> getUserByNickname(String nickname) {
        //Verbindung zur Datenbank holen
        Connection con = Datenbankverbindung.connection();

        Vector<user> result = new Vector<user>();

        try {

            // Leeres SQL-Statement erstellen
            Statement stmt = con.createStatement();

            // Das erstellte Statement mit der Abfrage füllen und an die Datenbank schicken
            ResultSet rs = stmt.executeQuery("SELECT uid, vorname, name, nickname, passwort FROM user " +
                    "WHERE nickname ='" + nickname + "'");

            //Jede Zeile des Ergebnisses aus der Datenbank durchlaufen
            while (rs.next()) {
                try {

                    user us = new userImpl();
                    us.setUid(rs.getInt("uid"));
                    us.setVorname(rs.getString("vorname"));
                    us.setName(rs.getString("name"));
                    us.setNickname(rs.getString("nickname"));
                    us.setPasswort(rs.getString("passwort"));

                    result.addElement(us);

                } catch (RemoteException ex) {
                    ex.printStackTrace();
                    // Bei einem Fehler wird die Schleife abgebrochen

                    break;
                }
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
        return result;
    }
}

