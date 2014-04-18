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
public interface user extends java.rmi.Remote{

    public int getUid() throws java.rmi.RemoteException;

    public void setUid(int uid) throws java.rmi.RemoteException;

    public String getVorname() throws java.rmi.RemoteException;

    public void setVorname(String vorname) throws java.rmi.RemoteException;

    public String getName() throws java.rmi.RemoteException;

    public void setName(String name) throws java.rmi.RemoteException;

    public String getNickname() throws java.rmi.RemoteException;

    public void setNickname(String nickname) throws java.rmi.RemoteException;

    public Timestamp getEinrichtungszeitpunkt() throws java.rmi.RemoteException;

    public void setEinrichtungszeitpunkt(Timestamp einrichtungszeitpunkt) throws java.rmi.RemoteException;

    public String getPasswort() throws java.rmi.RemoteException;

    public void setPasswort(String passwort) throws java.rmi.RemoteException;
}
