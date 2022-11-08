package pl.edu.pwr.bezp.communicator2.actions;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pwr.bezp.communicator2.actions.body.BodyCreator;
import pl.edu.pwr.bezp.communicator2.actions.body.RequestData;
import pl.edu.pwr.bezp.communicator2.actions.response.RespAbstract;
import pl.edu.pwr.bezp.communicator2.client.CommunicatorClient;
import pl.edu.pwr.bezp.communicator2.client.SocketsConnectionLayer;
import pl.edu.pwr.bezp.communicator2.client.crytoUtilsi.AES;

public abstract class AbstractAction {
    @Autowired
    private final SocketsConnectionLayer connectionLayer;

    @Autowired
    private final AES aes;

    @Autowired
    protected final BodyCreator bodyCreator;

    protected AbstractAction(SocketsConnectionLayer connectionLayer, AES aes, BodyCreator bodyCreator) {
        this.connectionLayer = connectionLayer;
        this.aes = aes;
        this.bodyCreator = bodyCreator;
    }

    public abstract RespAbstract run(CommunicatorClient communicatorClient);

    protected String communication(RequestData data) {
        try {
            var encryptedMsg = aes.encrypt(new JSONObject(data).toString());
            return aes.decrypt(connectionLayer.sendMessage(encryptedMsg));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
