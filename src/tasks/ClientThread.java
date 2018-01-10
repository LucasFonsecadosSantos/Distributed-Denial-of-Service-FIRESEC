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
 * 
 */
public class ClientThread implements Runnable, Serializable {

    private static final long serialVersionUID = 1L;
    private Socket client;
    private AttackPattern attackPattern;

    public ClientThread(Socket client, AttackPattern attackPattern) {
        this.client = client;
        this.attackPattern = attackPattern;
    }

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