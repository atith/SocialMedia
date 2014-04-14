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
public interface like extends java.rmi.Remote{

    public int getLid() throws java.rmi.RemoteException;

    public void setLid(int lid) throws java.rmi.RemoteException;

    public Timestamp getTime() throws java.rmi.RemoteException;

    public void setTime(Timestamp time) throws java.rmi.RemoteException;

    public int getBid() throws java.rmi.RemoteException;

    public void setBid(int bid) throws java.rmi.RemoteException;

    public int getAnzahl() throws java.rmi.RemoteException;

    public void setAnzahl(int anzahl) throws java.rmi.RemoteException;

    public int getUid() throws java.rmi.RemoteException;

    public void setUid(int uid) throws java.rmi.RemoteException;
}
