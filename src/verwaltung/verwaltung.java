package verwaltung;

import api.abo;
import api.beitrag;
import api.kommentar;
import api.like;
import api.user;
import java.util.Vector;


public interface verwaltung extends java.rmi.Remote {

    public Vector<user> findAllUser()throws java.rmi.RemoteException;

    public user anlegenUser(String nachname, String vorname, String nickname, String passwort) throws java.rmi.RemoteException;

    public user userBearbeiten(int uid, String vorname, String name, String nick, String pass) throws java.rmi.RemoteException;

    public user getUserData(String nickname) throws java.rmi.RemoteException;

    public Vector<user> getUserByNickname(String nickname) throws java.rmi.RemoteException;

    public void profilLoeschen(int uid) throws java.rmi.RemoteException;

    public beitrag beitragErzeugen(String text, int uid) throws java.rmi.RemoteException;

    public int getUidFromNickname(String nickname) throws java.rmi.RemoteException;

    public Vector<beitrag> getAllBeiträge(String nickname) throws java.rmi.RemoteException;

    public int getBidFromNickname(String nickname) throws java.rmi.RemoteException;

    public like createLike(int bid, int uid) throws java.rmi.RemoteException;

    public int getAllLikes(int bid) throws java.rmi.RemoteException;

    public Vector<kommentar> getAllKommentare(int bid) throws java.rmi.RemoteException;

    public kommentar kommentarErzeugen(String text, int bid) throws java.rmi.RemoteException;

    public void likeLoeschen(int uid, int bid) throws java.rmi.RemoteException;

    public abo createAbo(int uid, int uid2) throws java.rmi.RemoteException;

    public Vector<user> getAbonenntenToUser(int uid, int uid2) throws java.rmi.RemoteException;

    public int getUid2FromUid(int uid) throws java.rmi.RemoteException;

    public void aboLoeschen(int uid, int uid2) throws java.rmi.RemoteException;

 }
