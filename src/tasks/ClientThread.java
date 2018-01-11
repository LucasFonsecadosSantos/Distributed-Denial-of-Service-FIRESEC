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

import view.GUI;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.ObjectOutputStream;
import java.io.NotSerializableException;
import java.io.WriteAbortedException;
import java.io.Serializable;
import utilies.AttackPattern;

/**
 * @author Lucas Fonseca dos Santos
 * @author Otavio Andrade
 * @version 1.0
 * 
 * This class respresents a Runnable task for client
 * process at master server. It is running into a JVM
 * thread and send to zombie client the attack pattern
 * or attack command.
 */
public class ClientThread implements Runnable, Serializable {

    /**
     * The object stream serial constant.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * The client socket object.
     */
    private Socket client;

    /**
     * The attack pattern object or attack command.
     * It contains all informations about the operation.
     */
    private AttackPattern attackPattern;

    /**
     * The client thread task object constructor. It receives
     * a client socket object and a attack pattern object command
     * and sets them at attributes.
     * 
     * @param client The client socket object.
     * @param attackPattern The attack pattern object or command attack.
     */
    public ClientThread(Socket client, AttackPattern attackPattern) {
        this.client = client;
        this.attackPattern = attackPattern;
    }

    /**
     * This is an overwrited method. It executes the
     * thread processing and in this case,
     * send to zoombie client the attack pattern object
     * serializated or attack command.
     */
    @Override
    public void run() {
        try {
            while(true) {
                ObjectOutputStream oos = new ObjectOutputStream(this.client.getOutputStream());
                oos.writeObject(this.attackPattern);
                oos.flush();
                oos.close();
                break;
            }
        }catch(WriteAbortedException wae) {
            GUI.showExceptionLog(wae.toString());
        }catch(NotSerializableException nse) {
            GUI.showExceptionLog(nse.toString());
        }catch (Exception e) {
            GUI.showExceptionLog(e.toString());
        }
    }
}