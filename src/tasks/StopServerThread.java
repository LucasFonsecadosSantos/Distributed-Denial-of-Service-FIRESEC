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

import app.TurboFireSlave;
import view.GUI;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.SocketException;

/**
 * @author Lucas Fonseca dos Santos
 * @author Otavio Andrade
 * @version 1.0
 * 
 * This class respresents a stop server thread task USED BY SLAVE TURBO FIRE VERSION
 * for to kwnos when the master orders to stop DDOS attack operation.
 * It listening the connection in a differente port and when it listen
 * the phrase "Please, stop your attack!" from the master, so this slave
 * machine stops the DDOS attack operation.
 */
public class StopServerThread implements Runnable {

    /**
     * The master IP address.
     */
    private String masterAddress;

    /**
     * The Java server socket object.
     */
    private ServerSocket server;

    /**
     * This is the Stop server thread object constructor.
     * It receveis only the master ip address and sets
     * this at masterAddress attribute.
     * 
     * @param masterAddress The master machine IP address.
     */
    public StopServerThread(String masterAddress) {
        this.masterAddress = masterAddress;
        this.server = null;
    }

    /**
     * This is an overwrited method by Java thread class.
     * It initializes the server and wait a command for
     * stop the attack from master machine.
     */
    @Override
    public void run() {
        try {
            while(true) {
                this.server = new ServerSocket(4848);
                Socket socket = this.server.accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String msg = br.readLine();
                if(msg.equalsIgnoreCase("Please, stop you attack!")) {
                    TurboFireSlave.setAttackFlag(true);
                }
                break;
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