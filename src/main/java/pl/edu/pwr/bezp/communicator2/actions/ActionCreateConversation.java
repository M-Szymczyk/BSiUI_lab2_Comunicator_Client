package pl.edu.pwr.bezp.communicator2.actions;

import org.springframework.stereotype.Component;
import pl.edu.pwr.bezp.communicator2.actions.body.Body;
import pl.edu.pwr.bezp.communicator2.actions.body.RequestData;
import pl.edu.pwr.bezp.communicator2.actions.response.RespAbstract;
import pl.edu.pwr.bezp.communicator2.actions.response.RespCreateConversation;
import pl.edu.pwr.bezp.communicator2.client.CommunicatorClient;
import pl.edu.pwr.bezp.communicator2.client.SocketsConnectionLayer;
import pl.edu.pwr.bezp.communicator2.client.crytoUtilsi.AES;

@Component("createConversation")
public class ActionCreateConversation extends AbstractAction {
    public ActionCreateConversation(SocketsConnectionLayer connectionLayer, AES aes) {
        super(connectionLayer, aes);
    }

    @Override
    public RespAbstract run(CommunicatorClient communicatorClient) {
        return getCreateConversation(bodyCreator.fillConversationCreatorBody(communicatorClient.getConversationNames()));
    }

    @Override
    public RespAbstract run(Body body) {
        return getCreateConversation(body);
    }

    private RespCreateConversation getCreateConversation(Body body) {
        return new RespCreateConversation(
                communication(new RequestData("createConversation", body)));
    }
}
