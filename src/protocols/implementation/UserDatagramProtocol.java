package protocols.implementation;

import protocols.interfaces.UserDatagramProtocolInterface;
import utilies.AttackPattern;
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
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}