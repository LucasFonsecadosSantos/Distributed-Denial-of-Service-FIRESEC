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
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Lucas Fonseca dos Santos
 * @author Otavio Andrade
 * @version 1.0
 * 
 * This class is responsible for sends to all zombie clients a stop flag
 * when the user wants to stop the DDOS attack operation.
 * It implements the Runnable task because it's executes into a JVM thread,
 * connecting with all zombie clients and send the following string:
 * "Please, stop you attack?"
 * Isn't educated?
 */
public class StopCommandMasterThread implements Runnable {

    /**
     * The list with all zombie clients IP address.
     */
    private ArrayList<String> zombieHosts;

    /**
     * The port for connection.
     */
    private int port;

    /**
     * The stop command master thread object constructor.
     * It receives a zombie host clients IP address list into
     * java ArrayList and a port integer value for connection.
     * 
     * @param zombieHosts A java array list object with all zombie hosts IP address.
     * @param port A integer port value for connection.
     */
    public StopCommandMasterThread(ArrayList<String> zombieHosts, int port) {
        this.zombieHosts = zombieHosts;
        this.port = port;
    }

    /**
     * This is an overwrited method by Java thread object.
     * It is responsible for connects in all of zombie clients
     * and sends to them the following command:
     * "Please, stop your attack!".
     */
    @Override
    public void run() {
        try {
            for(String s : this.zombieHosts) {
                System.out.println("COMMAND SEND TO "+s);
                Socket socket = new Socket(s, this.port);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                bw.write("Please, stop you attack!");
                bw.close();
                socket.close();
            }
        } catch (SocketException se) {
            GUI.showExceptionLog(se.toString());
        } catch (IOException ioe) {
            GUI.showExceptionLog(ioe.toString());
        } catch (Exception e) {
            GUI.showExceptionLog(e.toString());
        }
    }
}