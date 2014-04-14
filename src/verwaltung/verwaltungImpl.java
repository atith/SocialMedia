package verwaltung;

import api.abo;
import api.aboImpl;
import api.beitrag;
import api.beitragImpl;
import api.kommentar;
import api.kommentarImpl;
import api.like;
import api.likeImpl;
import api.user;
import api.userImpl;
import datenbank.aboMapper;
import datenbank.beitragMapper;
import datenbank.kommentarMapper;
import datenbank.likeMapper;
import datenbank.userMapper;
import java.rmi.RemoteException;
import java.util.Vector;

public class verwaltungImpl extends java.rmi.server.UnicastRemoteObject implements verwaltung {

    private userMapper uMapper = null;

    private beitragMapper bMapper = null;

    private likeMapper lMapper = null;

    private kommentarMapper kMapper = null;

    private aboMapper aMapper = null;
    

    public verwaltungImpl(userMapper uM, beitragMapper bM, likeMapper lm, kommentarMapper kM, aboMapper aM) throws RemoteException {
        super();
        this.uMapper = uM;
        this.bMapper = bM;
        this.lMapper = lm;
        this.kMapper = kM;
        this.aMapper = aM;
    }

    public Vector<user> findAllUser() throws RemoteException {
        return this.uMapper.findAllUser();
    }

    public user anlegenUser(String nachname, String vorname, String nickname, String passwort) throws RemoteException {
        user newUser = new userImpl();
        newUser.setName(nachname);
        newUser.setVorname(vorname);
        newUser.setNickname(nickname);
        newUser.setPasswort(passwort);
        return this.uMapper.anlegenUser(newUser);
    }

    public user userBearbeiten(int uid, String vorname, String name, String nick, String pass) throws RemoteException {
        user newUser = new userImpl();
        newUser.setUid(uid);
        newUser.setVorname(vorname);
        newUser.setName(name);
        newUser.setNickname(nick);
        newUser.setPasswort(pass);
        return this.uMapper.userBearbeiten(newUser);
    }

    public user getUserData(String nickname) throws RemoteException {
        return this.uMapper.getUser(nickname);
    }

    public Vector<user> getUserByNickname(String nickname) throws RemoteException {
        return (Vector<user>) this.uMapper.getUserByNickname(nickname);
    }

    public void profilLoeschen(int uid) throws RemoteException {
        this.uMapper.profilLoeschen(uid);
    }

    public beitrag beitragErzeugen(String text, int uid) throws RemoteException {
        beitrag newBeitrag = new beitragImpl();
        newBeitrag.setText(text);
        newBeitrag.setUid(uid);
        return this.bMapper.beitragErzeugen(newBeitrag);
    }

    public int getUidFromNickname(String nickname) throws RemoteException {
        return this.bMapper.getUidFromNickname(nickname);
    }

    public Vector<beitrag>getAllBeiträge(String nickname) throws RemoteException {
        return this.bMapper.getAllBeiträge(nickname);
    }

    public int getBidFromNickname(String nickname) throws RemoteException {
        return this.bMapper.getBidFromNickname(nickname);
    }



    public like createLike(int bid, int uid) throws RemoteException {
        like l = new likeImpl();
        l.setBid(bid);
        l.setUid(uid);
        return this.lMapper.createLike(l);
    }

    public int getAllLikes(int bid) throws RemoteException {
        return this.lMapper.getAllLikes(bid);
    }

    public Vector<kommentar> getAllKommentare(int bid) throws RemoteException {
        return this.kMapper.getAllKommentare(bid);
    }

    public kommentar kommentarErzeugen(String text, int bid) throws RemoteException {
        kommentar newKommentar = new kommentarImpl();
        newKommentar.setText(text);
        newKommentar.setBid(bid);
        return this.kMapper.kommentarErzeugen(newKommentar);
    }

    public void likeLoeschen(int uid, int bid) throws RemoteException {
        this.lMapper.likeLoeschen(uid, bid);
    }

    public abo createAbo(int uid, int uid2) throws RemoteException {
        abo newAbo = new aboImpl();
        newAbo.setUid(uid);
        newAbo.setUid2(uid2);
        return this.aMapper.createAbo(newAbo);
    }

    public Vector<user> getAbonenntenToUser(int uid, int uid2) throws RemoteException {
        return this.aMapper.getAbonnentenToUser(uid, uid2);
    }

    public int getUid2FromUid(int uid) throws RemoteException {
        return this.aMapper.getUid2FromUid(uid);
    }

    public void aboLoeschen(int uid, int uid2) throws RemoteException {
        this.aMapper.aboLoeschen(uid, uid2);
    }
}




