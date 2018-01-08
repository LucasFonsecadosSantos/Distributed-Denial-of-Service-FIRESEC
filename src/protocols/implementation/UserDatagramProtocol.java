package protocols.implementation;

import protocols.interfaces.UserDatagramProtocolInterface;
import utilies.AttackPattern;
import view.GUI;
import java.io.IOException;

public class UserDatagramProtocol extends UserDatagramProtocolInterface {

    public UserDatagramProtocol(AttackPattern attackPattern) {
        super(attackPattern);
    }

    @Override
    public void writeMessage(String message) {
        byte[] data = message.getBytes();
        getPacket().setData(data);
        getPacket().setLength(data.length);
        try {
            getSocket().send(getPacket());
        } catch (IOException ex) {
            GUI.showExceptionLog(ex.toString());
        } catch (Exception e) {
            GUI.showExceptionLog(e.toString());
        }
    }
}