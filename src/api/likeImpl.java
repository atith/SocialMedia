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
public class likeImpl extends java.rmi.server.UnicastRemoteObject implements like {

    private int lid;
    private Timestamp time;
    private int bid;
    private int anzahl;
    private int uid;
    private String text;

    public likeImpl() throws RemoteException{
        super();
    }

    public int getLid() throws RemoteException {
        return this.lid;
    }

    public void setLid(int lid) throws RemoteException {
        this.lid = lid;
    }

    public Timestamp getTime() throws RemoteException {
        return this.time;
    }

    public void setTime(Timestamp time) throws RemoteException {
        this.time = time;
    }

    public int getBid() throws RemoteException {
        return this.bid;
    }

    public void setBid(int bid) throws RemoteException {
        this.bid = bid;
    }

    public int getAnzahl() throws RemoteException {
        return this.anzahl;
    }

    public void setAnzahl(int anzahl) throws RemoteException {
        this.anzahl = anzahl;
    }

    public int getUid() throws RemoteException {
        return this.uid;
    }

    public void setUid(int uid) throws RemoteException {
        this.uid = uid;
    }
}
