package datenbank;
import java.sql.*;
// <editor-fold defaultstate="collapsed" desc=" UML Marker ">
// #[regen=yes,id=DCE.28A8CBD3-777A-94E5-F24B-0EB32086DF08]
// </editor-fold>
public class Datenbankverbindung {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker ">
    // #[regen=yes,id=DCE.90039A1D-DFBD-9268-FD0C-51D24EE9F37C]
    // </editor-fold>
    private static Connection con = null;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker ">
    // #[regen=yes,id=DCE.1C6C5AAC-7381-BC53-E224-EB24D7AD6C32]
    // </editor-fold>
            private static String url = "jdbc:mysql://mars.iuk.hdm-stuttgart.de/u-as190?user=as190&password=yee3ouH2ve";

    public static Connection connection() {
        if (con == null) {
            try {
                // Laden des Datenbanktreibers
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                /*
                 * Der DriverManager baut mit der in der Variablen url gespeicherten
                 * Adresse eine Verbindung auf. Die Verbindung wird schließlich in der
                 * Variable con gesichert. Erst nach dem Laden des Datenbanktreibers
                 * kann der DriverManager die Verbindung aufbauen
                 */
                con = DriverManager.getConnection(url);
            } catch (SQLException e1) {
                con = null;
                e1.printStackTrace();
            } catch (InstantiationException e) {
                con = null;
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                con = null;
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                con = null;
                e.printStackTrace();
            }
        }
    return con;

    }

}
