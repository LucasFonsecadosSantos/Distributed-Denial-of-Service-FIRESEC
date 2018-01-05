package app;

import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.Socket;
import view.GUI;
import utilies.AttackPattern;;


public class TurboFireSlave {

    private GUI gui;
    private AttackPattern AttackPattern;

    public TurboFireSlave() {
        this.gui = new GUI();
        startSlave();
    }

    public void startSlave() {
        while(true) {
            try {
                System.out.println("I'M ONLINE");
                Socket socket = new Socket("127.0.0.1", 2525);
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message = input.readLine();
                String[] tokens = message.split("-");
                int port = Integer.valueOf(tokens[1]);
                int threadAmount = Integer.valueOf(tokens[2].trim());
                String ipAddress = tokens[0].trim();
                this.AttackPattern = new AttackPattern(ipAddress, port, threadAmount);
                System.out.println(port);
                break;
            }catch (Exception e) {
                System.out.println(e.toString());
            }
        }

        for(int i = 0 ; i < this.AttackPattern.getThreadAmount() ; i++) {
            
        }
    }
}