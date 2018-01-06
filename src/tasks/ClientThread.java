package tasks;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.ObjectOutputStream;
import java.io.NotSerializableException;
import java.io.WriteAbortedException;
import java.io.Serializable;
import utilies.AttackPattern;

public class ClientThread implements Runnable, Serializable {

    private static final long serialVersionUID = 1L;
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
                ObjectOutputStream oos = new ObjectOutputStream(this.client.getOutputStream());
                oos.writeObject(this.attackPattern);
                oos.flush();
                oos.close();
                break;
            }
        }catch(WriteAbortedException wae) {
            System.out.println(wae.toString());    
        }catch(NotSerializableException nse) {
            System.out.println(nse.toString());    
        }catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}