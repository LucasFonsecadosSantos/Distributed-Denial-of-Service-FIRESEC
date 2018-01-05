package tasks;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import utilies.AttackPattern;

public class ClientThread implements Runnable {

    private Socket client;
    private AttackPattern attackPattern;

    public ClientThread(Socket client, AttackPattern attackPattern) {
        this.client = client;
        this.attackPattern = attackPattern;
    }

    @Override
    public void run() {
        try {
            while(true) {
                // BufferedReader input = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
                // String message = input.readLine();
                // if(message.equals("online")) {
                PrintWriter output = new PrintWriter(this.client.getOutputStream(), true);
                output.println(
                    attackPattern.getIP() + "-" +
                    attackPattern.getPort() + "-" +
                    attackPattern.getThreadAmount() + "-"
                );
                //}
                break;
            }
            
        }catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}