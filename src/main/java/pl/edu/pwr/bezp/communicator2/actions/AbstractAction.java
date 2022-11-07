package pl.edu.pwr.bezp.communicator2.actions;

import org.json.JSONObject;
import pl.edu.pwr.bezp.communicator2.actions.body.RequestData;
import pl.edu.pwr.bezp.communicator2.actions.response.RespAbstract;

import java.io.IOException;

public abstract class AbstractAction {
    abstract RespAbstract run();

    protected String communication(RequestData data) {
//        var encryptedMsg = /*cipherUtility.encrypt(*/new JSONObject(data).toString()/*, serverPublicKey)*/;
////        out.println(encryptedMsg);
//        //todo dane wysylam i odbieram
//        // SocketsConnectionLayer  connectionLayer= new SocketsConnectionLayer();
//        try {
//            return connectionLayer.sendMessage(encryptedMsg);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        return "response";
    }
}
