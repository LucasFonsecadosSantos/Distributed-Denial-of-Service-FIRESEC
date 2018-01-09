package protocols.implementation;

import protocols.interfaces.UserDatagramProtocolInterface;
import utilies.SlaveResponse;
import utilies.AttackPattern;
import view.GUI;
import java.io.IOException;

public class UserDatagramProtocol extends UserDatagramProtocolInterface {

    public UserDatagramProtocol(AttackPattern attackPattern, String masterAddress, int masterPort) {
        super(attackPattern, masterAddress, masterPort);
    }

    @Override
    public void writeMessage(String message) {
        byte[] data = message.getBytes();
        getPacket().setData(data);
        getPacket().setLength(data.length);
        SlaveResponse.successfullyAttackResponse(getMasterAddress(), getMasterPort(), getAttackPattern());
        try {
            getSocket().send(getPacket());
        } catch (IOException ex) {
            GUI.showExceptionLog(ex.toString());
        } catch (Exception e) {
            GUI.showExceptionLog(e.toString());
        }
    }
}