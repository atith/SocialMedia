/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datenbank;

import api.abo;
import api.aboImpl;
import api.user;
import api.userImpl;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.Vector;

/**
 *
 * @author Atith
 */
public class aboMapper {

    private static aboMapper aMapper = null;

    protected aboMapper() {
    }

    public static aboMapper aMapper() {
        if (aMapper == null) {
            aMapper = new aboMapper();
        }
        return aMapper;
    }

    public abo createAbo(abo newAbo) {
        Connection con = Datenbankverbindung.connection();
        try {
            Statement stmt2 = con.createStatement();

            ResultSet rs2 = null;
            try {
                rs2 = stmt2.executeQuery("select * from abo where uid='" + newAbo.getUid() + "' and " + "uid2='" + newAbo.getUid2() + "'");
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            if (!rs2.isBeforeFirst()) {
                try {
                    Statement stmt = con.createStatement();

                    ResultSet rs = stmt.executeQuery("select max(aid) as maxid from abo");

                    while (rs.next()) {
                        try {
                            newAbo.setAid(rs.getInt("maxid") + 1);

                            stmt = con.createStatement();

                            stmt.executeUpdate("insert into abo (aid, uid, uid2)" +
                                    "values ('" +
                                    newAbo.getAid() + "','" +
                                    newAbo.getUid() + "','" +
                                    newAbo.getUid2() + "')");
                        } catch (RemoteException rx) {
                            rx.printStackTrace();
                        }
                    }
                } catch (SQLException sx) {
                    sx.printStackTrace();
                }
            } else {
                return null;
            }
        } catch (SQLException sx) {
            sx.printStackTrace();
        }
        return newAbo;
    }

    public Vector<user> getAbonnentenToUser(int uid, int uid2) {
        Connection con = Datenbankverbindung.connection();

        Vector<user> result = new Vector<user>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT u.nickname FROM `user` u " +
                    "Left join abo a on u.uid = a.uid2 " +
                    "WHERE a.uid='" + uid + "'");
            while (rs.next()) {
                try {
                    user u = new userImpl();
                    u.setNickname(rs.getString("nickname"));
                    result.addElement(u);
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

    public int getUid2FromUid(int uid) {
        int as = 0;
        // Verbindung zur Datenbank holen
        Connection con = Datenbankverbindung.connection();
        try {

            // Leeres SQL-Statement erstellen
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT uid2 from `abo` " +
                    "where uid='" + uid + "'");

            while (rs.next()) {

                int uid2 = rs.getInt("uid2");

                return uid2;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return as;

    }

    public void aboLoeschen(int uid, int uid2) {
        Connection con = Datenbankverbindung.connection();
        try {
            Statement stmt = con.createStatement();

            stmt.executeUpdate("delete from `abo` where " +
                    "uid ='" + uid + "' and uid2 ='" + uid2 + "'");
        } catch (SQLException sx) {
            sx.printStackTrace();
        }
    }

    public Vector<abo> getAllUid2FromUid(int uid) {
        Connection con = Datenbankverbindung.connection();

        Vector<abo> result = new Vector<abo>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT a.uid2 FROM `abo` a " +
                    "Left join user u on a.uid = u.uid " +
                    "WHERE a.uid='" + uid + "'");
            while (rs.next()) {
                try {
                    abo a = new aboImpl();
                    a.setUid2(rs.getInt("uid2"));
                    result.addElement(a);
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

    public Vector<abo> getAllAbonnenten(int uid, int uid2) {
        // Verbindung zur Datenbank holen
        Connection con = Datenbankverbindung.connection();

        Vector <abo> result = new Vector<abo>();
        try {
            // Leeres SQL-Statement erstellen
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT uid, uid2 from `abo` " +
                    "where uid ='" + uid + "' and uid2 ='" + uid2 + "'");

            while (rs.next()) {
                try {
                    abo a = new aboImpl();
                    a.setUid(rs.getInt("uid"));
                    a.setUid2(rs.getInt("uid2"));
                    result.addElement(a);
                } catch (RemoteException ex) {
                    ex.printStackTrace();

                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}

