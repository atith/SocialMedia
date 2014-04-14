/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.rmi.RemoteException;
import java.sql.Timestamp;

/**
 *
 * @author Atith
 */
public class userImpl extends java.rmi.server.UnicastRemoteObject implements user{

    private int uid;
    private String vorname;
    private String name;
    private String nickname;
    private Timestamp einrichtungszeitpunkt;
    private String passwort;

    public userImpl() throws RemoteException {
        super();
    }

    public int getUid() throws RemoteException {
        return this.uid;
    }

    public void setUid(int uid) throws RemoteException {
        this.uid = uid;
    }

    public String getVorname() throws RemoteException {
        return this.vorname;
    }

    public void setVorname(String vorname) throws RemoteException {
        this.vorname = vorname;
    }

    public String getName() throws RemoteException {
        return this.name;
    }

    public void setName(String name) throws RemoteException {
        this.name = name;
    }

    public String getNickname() throws RemoteException {
        return this.nickname;
    }

    public void setNickname(String nickname) throws RemoteException {
        this.nickname = nickname;
    }

    public Timestamp getEinrichtungszeitpunkt() throws RemoteException{
        return this.einrichtungszeitpunkt;
    }

    public void setEinrichtungszeitpunkt(Timestamp einrichtungszeitpunkt) throws RemoteException{
        this.einrichtungszeitpunkt = einrichtungszeitpunkt;
    }

    public String getPasswort()throws RemoteException{
        return this.passwort;
    }

    public void setPasswort(String passwort) throws RemoteException{
        this.passwort = passwort;
    }
}
