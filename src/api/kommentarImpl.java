/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package api;

import java.sql.Timestamp;
/**
 *
 * @author berack
 */
public class kommentarImpl extends java.rmi.server.RemoteObject implements kommentar {
    
    private int kid;
    private Timestamp tmp;
    private String text;
    private int bid;

    public kommentarImpl() throws java.rmi.RemoteException{
        super();
    }

    public int getKid() throws java.rmi.RemoteException{
        return kid;
    }

    public void setKid(int kid) throws java.rmi.RemoteException{
        this.kid = kid;
    }

    public Timestamp getTimestamp() throws java.rmi.RemoteException{
        return tmp;
    }

    public void setTimestamp(Timestamp tmp) throws java.rmi.RemoteException{
        this.tmp = tmp;
    }

    public String getText() throws java.rmi.RemoteException{
        return text;
    }

    public void setText(String text) throws java.rmi.RemoteException{
        this.text = text;
    }

    public int getBid() throws java.rmi.RemoteException{
        return bid;
    }

    public void setBid(int bid)  throws java.rmi.RemoteException{
        this.bid = bid;
    }
}
