package app;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import view.GUI;
import utilies.Interpreter;
import utilies.TurnOnServer;
import utilies.ClientThread;
import utilies.TurnOnServer;

public class TurboFireMaster {
    
    private GUI gui;
    private Interpreter interpreter;
    private int serverPort;
    private ServerSocket server;

    public TurboFireMaster() {
        this.gui = new GUI();
        this.interpreter = new Interpreter();
        this.serverPort = 2525;
        startMaster();
    }

    public void startMaster() {
        while(true) {
            this.gui.welcome("master", this.serverPort);
            String[] command = this.gui.getCommand();
            if(command.length > 0) {
                if(command[0].equals("ip")) {
                    this.gui.showMessage("Current machine network IP: " + getServerAddress());
                }else if(command[0].equals("help")) {
                    this.gui.help();
                }else if(command[0].equals("quit")) {

                }else if(command[0].equals("list active hosts")) {

                }else if(command[0].equals("port")) {
                    this.gui.showMessage("The port "+this.serverPort+" is already to use.");
                }else if(command[0].equals("up")) {
                    Runnable turnOnServer = new TurnOnServer();
                    Thread t = new Thread(turnOnServer);
                    t.start();
                }
            }else {
                this.gui.errorMessage("please, type again!");
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