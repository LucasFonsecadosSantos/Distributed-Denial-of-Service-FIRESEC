package app;

import java.net.ServerSocket;
import java.net.Socket;
import view.GUI;
import utilies.Interpreter;
import utilies.ClientThread;

public class TurboFireMaster {
    
    private GUI gui;
    private Interpreter interpreter;
    private int serverPort;

    public TurboFireMaster() {
        this.gui = new GUI();
        this.interpreter = new Interpreter();
        this.serverPort = 2525;
        startMaster();
    }

    public void startMaster() {
        this.gui.welcome("master", this.serverPort);
        String[] command = this.gui.getCommand();
        if(command.length > 0) {
            
            if(command[0].equals("help")) {
                this.gui.help();
            }else if(command[0].equals("quit")) {

            }else if(command[0].equals("list active hosts")) {

            }else if(command[0].equals("port")) {
                this.gui.showMessage("The port "+this.serverPort+" is already to use.");
            }else if(command[0].equals("up")) {
                try {
                    ServerSocket server = new ServerSocket(this.serverPort);
                    while(true) {
                        Socket clientSocket = server.accept();
                        this.gui.newClient(clientSocket.getRemoteSocketAddress().toString());
                        new ClientThread(clientSocket).start();
                    }
                }catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        }else {
            this.gui.errorMessage("please, type again!");
        }
    }
}