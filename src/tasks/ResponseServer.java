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
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * @author Lucas Fonseca dos Santos
 * @author Otavio Andrade
 * @version 1.0
 * 
 * This class implements a response master server task that will
 * be executed into a JVM thread.
 * This server is responsible for receveis all zombie clients
 * requests that bring the current informations about the attack,
 * which are passed on to the master.
 */
public class ResponseServer implements Runnable {
    
    /**
     * The server socket connection object.
     */
    private ServerSocket serverSocket;

    /**
     * The response server object constructor.
     */
    public ResponseServer() {
       this.serverSocket = null;
    }

    /**
     * This is an overwrited mehtod by Java Threads object.
     * It initializes the server socket and receives the client
     * sockets connections and after that, answers the client in other
     * Java thread.
     */
    @Override
    public void run() {
        try {
            this.serverSocket = new ServerSocket(2525);
            while(true) {
                Socket clientSocket = serverSocket.accept();
                Thread thread = new Thread(new ZoombieResponseTreatment(clientSocket));
                thread.start();
            }
        } catch(SocketException se) {
            GUI.showExceptionLog(se.toString());
        } catch(Exception e) {
            GUI.showExceptionLog(e.toString());
        }
    } 
}