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
public class beitragImpl extends java.rmi.server.UnicastRemoteObject implements beitrag {

    private int bid;
    private Timestamp tmp;
    private String text;
    private int uid;

    public beitragImpl() throws RemoteException{
        super();
    }

    public int getBid() throws RemoteException {
        return this.bid;
    }

    public void setBid(int bid) throws RemoteException {
        this.bid = bid;
    }

    public Timestamp getTimestamp() throws RemoteException {
        return this.tmp;
    }

    public void setTimestamp(Timestamp tmp) throws RemoteException {
        this.tmp = tmp;
    }

    public String getText() throws RemoteException {
        return this.text;
    }

    public void setText(String text) throws RemoteException {
        this.text = text;
    }

    public int getUid() throws RemoteException {
        return this.uid;
    }

    public void setUid(int uid) throws RemoteException {
        this.uid = uid;
    }
}
