/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

/**
 *
 * @author Atith
 */
public interface abo extends java.rmi.Remote {

    public int getAid() throws java.rmi.RemoteException;

    public void setAid(int aid) throws java.rmi.RemoteException;

    public int getUid() throws java.rmi.RemoteException;

    public void setUid(int uid) throws java.rmi.RemoteException;

    public int getUid2() throws java.rmi.RemoteException;

    public void setUid2(int uid2) throws java.rmi.RemoteException;
}
