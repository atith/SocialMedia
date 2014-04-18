/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package api;

import java.rmi.RemoteException;

/**
 *
 * @author Atith
 */
public class aboImpl extends java.rmi.server.UnicastRemoteObject implements abo{

    private int aid;
    private int uid;
    private int uid2;

    public aboImpl() throws RemoteException{
        super();
    }

    public int getAid() throws RemoteException {
        return this.aid;
    }

    public void setAid(int aid) throws RemoteException {
        this.aid = aid;
    }

    public int getUid() throws RemoteException {
        return this.uid;
    }

    public void setUid(int uid) throws RemoteException {
        this.uid = uid;
    }

    public int getUid2() throws RemoteException {
        return this.uid2;
    }

    public void setUid2(int uid2) throws RemoteException {
        this.uid2 = uid2;
    }

}
