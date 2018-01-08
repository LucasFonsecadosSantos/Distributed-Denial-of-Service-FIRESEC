/**
 * FIRESEC TURBO FIRE
 * A stress network analysis tool.
 * ------------------------------------
 * Written by Lucas Fonseca dos Santos.
 * Copyleft 2018 - Rights not reserved.
 * ------------------------------------
 * 
 * Pratical project for discipline Distributed
 * Systems of Federal Univeristy of Lavras - MG,
 * Brazil.
 * 
 * CONTACT:
 * lucas@lcfcompany.com.br
 * github.com/LucasFonsecaDosSantos
 */
package app;

import view.GUI;
import utilies.AttackPattern;
import utilies.AttackFactory;
import protocols.interfaces.Protocol;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.NotSerializableException;
import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;

/**
 * @author Lucas Fonseca dos Santos
 * @author Otavio Andrade
 */
public class TurboFireSlave implements Serializable {

    private static final long serialVersionUID = 1L;
    private GUI gui;
    private AttackPattern attackPattern;

    public TurboFireSlave() {
        this.gui = new GUI();
        startSlave();
    }

    public void startSlave() {
        while(true) {
            try {
                System.out.println("I'M ONLINE");
                Socket socket = new Socket("127.0.0.1", 2525);
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                this.attackPattern = (AttackPattern) ois.readObject();
                System.out.println(this.attackPattern.getProtocol() + " - " + this.attackPattern.getIP() + " - " + this.attackPattern.getPort() + " - " + this.attackPattern.getThreadAmount());
                ois.close();

                for(int i = 0 ; i < this.attackPattern.getThreadAmount() ; i++) {
                    Thread t = new Thread(AttackFactory.createAttack(this.attackPattern));
                    t.start();
                    System.out.println("ok");
                }
                break;
            }catch(NotSerializableException nse) {
                System.out.println(nse.toString());
            }catch (Exception e) {
                System.out.println(e.toString());
            }
        }

        for(int i = 0 ; i < this.attackPattern.getThreadAmount() ; i++) {

        }
    }
}