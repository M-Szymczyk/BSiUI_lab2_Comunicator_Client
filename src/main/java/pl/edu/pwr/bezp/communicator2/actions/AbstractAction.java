package pl.edu.pwr.bezp.communicator2.actions;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pwr.bezp.communicator2.actions.body.Body;
import pl.edu.pwr.bezp.communicator2.actions.body.BodyCreator;
import pl.edu.pwr.bezp.communicator2.actions.body.RequestData;
import pl.edu.pwr.bezp.communicator2.actions.response.RespAbstract;
import pl.edu.pwr.bezp.communicator2.client.CommunicatorClient;
import pl.edu.pwr.bezp.communicator2.client.SocketsConnectionLayer;
import pl.edu.pwr.bezp.communicator2.client.crytoUtilsi.AES;


/**
 * Abstract class for actions with server
 */
public abstract class AbstractAction {
    @Autowired
    private final SocketsConnectionLayer connectionLayer;

    @Autowired
    private final AES aes;

    public static BodyCreator bodyCreator ;

    @Autowired
    public void setCommunicatorOptions(BodyCreator bodyCreator) {
        AbstractAction.bodyCreator = bodyCreator;
    }

    protected AbstractAction(SocketsConnectionLayer connectionLayer, AES aes) {
        this.connectionLayer = connectionLayer;
        this.aes = aes;
    }

    /**
     * Abstract method which will create JSON Objects of action using @see Body Creator
     * @param communicatorClient reference to communicator to get some useful data
     * @return readied response from server
     */
    public abstract RespAbstract run(CommunicatorClient communicatorClient);

    /**
     * Abstract method which will create JSON Objects of action using given body
     * @param body reference to body
     * @return readied response from server
     */
    public abstract RespAbstract run(Body body);

    /**
     * Method which sends action to server and read response.
     * @param data which will be sent
     * @return response from server
     */
    protected String communication(RequestData data) {
        try {
            var encryptedMsg = aes.encrypt(new JSONObject(data).toString());
            return aes.decrypt(connectionLayer.sendMessage(encryptedMsg));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
