package tasks;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.ArrayList;

public class StopCommandMasterThread implements Runnable {

    private ArrayList<String> zombieHosts;
    private int port;

    public StopCommandMasterThread(ArrayList<String> zombieHosts, int port) {
        this.zombieHosts = zombieHosts;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.zombieHosts.size() + " sizeee");
            for(String s : this.zombieHosts) {
                System.out.println("COMMAND SEND TO "+s);
                Socket socket = new Socket(s, this.port);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                bw.write("Please, stop you attack!");
                bw.close();
                socket.close();
            }
        } catch (SocketException se) {

        } catch (IOException ioe) {
            
        } catch (Exception e) {
            
        }
    }
}