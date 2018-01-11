/**
 * FIRESEC TURBO FIRE
 * A stress network analysis tool.
 * ------------------------------------
 * Written by Lucas Fonseca dos Santos.
 * Copyleft 2018 - Rights not reserved.
 * ------------------------------------
 * 
 * Pratical project for discipline Distributed
 * Systems of Federal Univeristy of Lavras - MG,
 * Brazil.
 * 
 * CONTACT:
 * lucas@lcfcompany.com.br
 * github.com/LucasFonsecaDosSantos
 */
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

/**
 * @author Lucas Fonseca dos Santos
 * @version 1.0
 * 
 * This class represents a turbo fire slave version server response.
 * This object is responsible for updating the master version about
 * the DDOS operation with data.
 */
public class SlaveResponse {

    /**
     * This static method is responsible for notify the master server
     * about a successfully DDOS request to target.
     * 
     * @param masterAddress The master server machine IP address.
     * @param masterPort The master server machine port.
     * @param attackPattern The attack pattern object with all information about DDOS attack operation.
     */
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
    
    public static void closeResponse(String masterAddress, int masterPort, AttackPattern attackPattern) {
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