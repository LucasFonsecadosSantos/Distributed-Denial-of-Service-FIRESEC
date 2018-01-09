package tasks;

import view.GUI;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ResponseServer implements Runnable {
    
    private GUI gui;
    private ServerSocket serverSocket;

    public ResponseServer() {
        
    }

    @Override
    public void run() {
        try {
            this.serverSocket = new ServerSocket(2525);
            while(true) {
                Socket clientSocket = serverSocket.accept();
                Thread thread = new Thread(new ZoombieResponseTreatment(clientSocket));
                thread.start();
            }
        } catch(SocketException se) {
            GUI.showExceptionLog(se.toString());
        } catch(Exception e) {
            GUI.showExceptionLog(e.toString());
        }
    } 
}