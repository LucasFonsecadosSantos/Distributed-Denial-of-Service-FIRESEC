package tasks;

import view.GUI;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ServerSocket;
import java.util.List;
import java.util.ArrayList;
import utilies.AttackPattern;

public class ServerThread implements Runnable{

    private GUI gui;
    private List zoombieClients;
    private AttackPattern attackPattern;

    public ServerThread(AttackPattern attackPattern) {
        this.gui = new GUI();
        this.zoombieClients = new ArrayList<Socket>();
        this.attackPattern = attackPattern;
    }

    @Override
    public void run() {
        while(this.zoombieClients.size() < 5) {
            try {
                ServerSocket server = new ServerSocket(2525);
                while(zoombieClients.size() < 5) {
                    Socket clientSocket = server.accept();
                    Thread t = new Thread(new ClientThread(clientSocket, attackPattern));
                    t.start();
                    this.zoombieClients.add(clientSocket);
                }
                System.out.println("Terminou");
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