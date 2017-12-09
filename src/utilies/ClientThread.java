package utilies;

import java.net.Socket;

public class ClientThread extends Thread {

    private Socket client;

    public ClientThread(Socket client) {
        this.client = client;
        System.out.println("crioou");
    }

    @Override
    public void run() {
        try {
            /*
            ObjectInputStream input = new ObjectInputStream(this.client.getInputStream());
            String message = (String)input.readObject();
            if(message.equals("online")) {
                ObjectOutputStream output = new ObjectOutputStream(this.client.getOutputStream("OK"));
            }*/
        }catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}