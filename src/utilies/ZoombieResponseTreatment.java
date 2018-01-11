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
package tasks;

import app.TurboFireMaster;
import view.GUI;
import utilies.AttackPattern;
import java.net.Socket;
import java.net.SocketException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.io.IOException;
import java.io.NotSerializableException;

/**
 * @author Lucas Fonseca dos Santos
 * @author Otavio Andrade
 * @version 1.0
 * 
 * This class describes a zombie response treatment, task of master response
 * server. It receveis from all zombie clients, updates about the DDOS
 * attack operation.
 */
public class ZoombieResponseTreatment implements Runnable {

    /**
     * The java client socket object.
     */
    private Socket clientSocket;

    /**
     * The zombie response treatment object constructor.
     * It just receives a client socket and sets it at client socket attribute.
     * 
     * @param clientSocket A java client socket object.
     */
    public ZoombieResponseTreatment(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    /**
     * This class is a runnable task, executed into a Java Thread object.
     * So, this is a overwrited method from thread class. It just receives
     * the data of zombie and stores this at a a static list on TurboFireMaster class.
     */
    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(this.clientSocket.getInputStream());
            Object streamData = ois.readObject();
            AttackPattern attackPattern = null;
            if(streamData instanceof AttackPattern) {
                attackPattern = (AttackPattern) streamData;
            }
            ois.close();
            this.clientSocket.close();
            TurboFireMaster.addAttackLog((AttackPattern) streamData);
        } catch(SocketException se) {
            GUI.showExceptionLog(se.toString());
        } catch(ObjectStreamException ose) {
            GUI.showExceptionLog(ose.toString());
        } catch(IOException ioe) {
            GUI.showExceptionLog(ioe.toString());
        } catch(Exception e) {
            GUI.showExceptionLog(e.toString());
        }
    }
}