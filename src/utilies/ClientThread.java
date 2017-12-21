package utilies;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ClientThread implements Runnable {

    private Socket client;

    public ClientThread(Socket client) {
        this.client = client;
        System.out.println("crioou");
    }

    @Override
    public void run() {
        try {
            
            BufferedReader input = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
            String message = input.readLine();
            if(message.equals("online")) {
                PrintWriter output = new PrintWriter(this.client.getOutputStream());
                output.println("OK");
            }
            
        }catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}