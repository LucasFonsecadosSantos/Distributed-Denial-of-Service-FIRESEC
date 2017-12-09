package app;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import view.GUI;
import utilies.Interpreter;

public class TurboFireSlave {

    private GUI gui;

    public TurboFireSlave() {
        try {
            Socket socket = new Socket("127.0.0.1", 2525);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject("online");
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            String s = (String)in.readObject();
            System.out.println(s);
        }catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}