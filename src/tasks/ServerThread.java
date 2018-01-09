package tasks;

import view.GUI;
import utilies.AttackPattern;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ServerSocket;
import java.util.List;
import java.util.ArrayList;

public class ServerThread implements Runnable {

    private GUI gui;
    private List zoombieClients;
    private AttackPattern attackPattern;
    private int serverPort;
    private ServerSocket serverSocket;

    public ServerThread(int serverPort, AttackPattern attackPattern) {
        this.gui = new GUI();
        this.zoombieClients = new ArrayList<Socket>();
        this.attackPattern = attackPattern;
        this.serverPort = serverPort;
    }

    @Override
    public void run() {
        while(this.zoombieClients.size() < 5) {
            try {
                ServerSocket server = new ServerSocket(this.serverPort);
                while(zoombieClients.size() < 5) {
                    Socket clientSocket = server.accept();
                    Thread t = new Thread(new ClientThread(clientSocket, attackPattern));
                    t.start();
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