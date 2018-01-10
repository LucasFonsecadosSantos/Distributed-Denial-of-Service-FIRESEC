package tasks;

import javax.swing.*;
import java.awt.*;
import app.TurboFireSlave;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.SocketException;

public class StopServerThread implements Runnable {

    private String masterAddress;
    private ServerSocket server;

    public StopServerThread(String masterAddress) {
        this.masterAddress = masterAddress;
        this.server = null;
    }

    @Override
    public void run() {
        try {
            while(true) {
                this.server = new ServerSocket(4848);
                Socket socket = this.server.accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String msg = br.readLine();
                if(msg.equalsIgnoreCase("Please, stop you attack!")) {
                    TurboFireSlave.setAttackFlag(true);
                }
                break;
                
            }

        } catch (SocketException se) {

        } catch (IOException ioe) {

        } catch (Exception e) {

        }
    }

}