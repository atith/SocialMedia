/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verwaltung;

import datenbank.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class server {

    private verwaltung verwaltung = null;

    public server() {
        try {
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            Registry registry = LocateRegistry.getRegistry();

            verwaltung = new verwaltungImpl(userMapper.uMapper(), beitragMapper.bMapper(), likeMapper.lMapper(), kommentarMapper.kMapper(), aboMapper.aMapper());
            //Regisitry durch Java Programm starten lassen

            Naming.rebind("rmi://localhost:1099/server", verwaltung);
            System.out.println("Server in Registry eingetragen....");


        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String args[]) {
        server server = new server();
        System.out.println("Server gestartet.....");
    }
}
			



