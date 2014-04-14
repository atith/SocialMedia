/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package api;

import java.sql.Timestamp;

/**
 *
 * @author Atith
 */
public interface kommentar extends java.rmi.Remote {

    public int getKid() throws java.rmi.RemoteException;

    public void setKid(int kid) throws java.rmi.RemoteException;

    public Timestamp getTimestamp() throws java.rmi.RemoteException;

    public void setTimestamp(Timestamp tmp) throws java.rmi.RemoteException;

    public String getText() throws java.rmi.RemoteException;

    public void setText(String text) throws java.rmi.RemoteException;

    public int getBid() throws java.rmi.RemoteException;

    public void setBid(int bid) throws java.rmi.RemoteException;

}
