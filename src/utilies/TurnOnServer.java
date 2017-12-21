package utilies;

import view.GUI;
import java.net.Socket;
import java.net.ServerSocket;
public class TurnOnServer implements Runnable{

    private GUI gui;

    public TurnOnServer() {
        this.gui = new GUI();
    }
    @Override
    public void run() {
        while(true) {
            try {
                ServerSocket server = new ServerSocket(2525);
                while(true) {
                    Socket clientSocket = server.accept();
                    this.gui.newClient(clientSocket.getRemoteSocketAddress().toString());
                    Runnable clientTask = new ClientThread(clientSocket);
                    Thread t = new Thread(clientTask);
                    t.start();
                }
            }catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}