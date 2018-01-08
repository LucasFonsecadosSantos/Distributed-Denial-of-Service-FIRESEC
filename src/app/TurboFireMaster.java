package app;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import view.GUI;
import utilies.AttackPattern;
import tasks.ServerThread;
import tasks.ClientThread;
import java.util.List;
import java.util.ArrayList;

public class TurboFireMaster {
    
    private GUI gui;
    private int serverPort;
    private ServerSocket server;
    private Runnable serverThread;
    private ArrayList zoombieList;
    private AttackPattern attackPattern;


    public TurboFireMaster() {
        this.gui = new GUI();
        this.serverPort = 2525;
        this.zoombieList = new ArrayList<Socket>();
        startMaster();
    }

    public void startMaster() {
        while(true) {
            this.gui.welcome("master", this.serverPort);
            String[] command = this.gui.getCommand();
            this.gui.clean();
            
            if(command[0].equals("listen")) {
                // Thread t = new Thread(new ServerThread());
                // t.start();
                // this.gui.pressEnterToContinue();
            }else if(command[0].equals("list")) {
            
            }else if(command[0].equalsIgnoreCase("fire")) {
                int port = 80;
                int threadAmount = 1;
                int connectionTimeOut = 300;
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
                    }else if(command[i].equalsIgnoreCase("--threads") || command[i].equalsIgnoreCase("-t")) {
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
                    }else if(command[i+1].equalsIgnoreCase("--message") || command[i+1].equalsIgnoreCase("-m")) {
                        if(command[i+1 != null]) {
                            message = command[i+1];
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
                this.gui.showMessage("Message: " + message);
                this.gui.pressEnterToContinue();
                this.attackPattern = new AttackPattern(protocol, ipAddress, port, threadAmount, connectionTimeOut, message);
                Thread t = new Thread(new ServerThread(this.attackPattern));
                t.start();
            }
        }
    }

    public int getServerPort() {
        return this.serverPort;
    }

    public InetAddress getServerAddress() {
        return this.server.getInetAddress();
    }
}