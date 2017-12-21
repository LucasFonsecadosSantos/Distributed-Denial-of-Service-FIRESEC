package app;

import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.ObjectInputStream;
import java.net.Socket;
import view.GUI;
import utilies.Interpreter;

public class TurboFireSlave {

    private GUI gui;

    public TurboFireSlave() {
        this.gui = new GUI();
        startSlave();
    }

    public void startSlave() {
        while(true) {
            try {
                System.out.println("I'M ONLINE");
                Socket socket = new Socket("127.0.0.1", 2525);
                PrintWriter output = new PrintWriter(socket.getOutputStream());
                output.println("online");
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                String s = (String)in.readObject();
                System.out.println(s);
            }catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}