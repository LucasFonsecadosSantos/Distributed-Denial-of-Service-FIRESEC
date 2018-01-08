package protocols.implementation;

import utilies.AttackPattern;
import view.GUI;
import protocols.interfaces.TransmissionControlProtocolInterface;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class TransmissionControlProtocol extends TransmissionControlProtocolInterface {

    public TransmissionControlProtocol(AttackPattern attackPattern) {
        super(attackPattern);
    }

    @Override
    public void writeMessage(String message) {
        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(getSocket().getOutputStream()))) {
            bw.write(getAttackPattern().getMessage());
            bw.flush();
        } catch (IOException ex) {
            GUI.showExceptionLog(ex.toString());
        } catch (Exception e) {
            GUI.showExceptionLog(e.toString());
        }
    }
}