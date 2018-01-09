package utilies;

import utilies.AttackPattern;
import view.GUI;
import java.net.Socket;
import java.net.InetAddress;
import java.net.SocketException;
import java.io.ObjectOutputStream;
import java.io.WriteAbortedException;
import java.io.NotSerializableException;
import java.io.IOException;


public class SlaveResponse {

    public SlaveResponse() {

    }

    public static void successfullyAttackResponse(String masterAddress, int masterPort, AttackPattern attackPattern) {
        try {
            Socket socket = new Socket(masterAddress, masterPort);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(attackPattern);
            oos.close();
            socket.close();
        } catch(SocketException se) {
            GUI.showExceptionLog(se.toString());
        } catch(WriteAbortedException wae) {
            GUI.showExceptionLog(wae.toString());
        } catch(NotSerializableException nse) {
            GUI.showExceptionLog(nse.toString());
        } catch(IOException ioe) {
            GUI.showExceptionLog(ioe.toString());
        } catch(Exception e) {
            GUI.showExceptionLog(e.toString());
        }
    }
}