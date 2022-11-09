package pl.edu.pwr.bezp.communicator2.actions;

import org.springframework.stereotype.Component;
import pl.edu.pwr.bezp.communicator2.actions.body.Body;
import pl.edu.pwr.bezp.communicator2.actions.body.RequestData;
import pl.edu.pwr.bezp.communicator2.actions.response.RespAbstract;
import pl.edu.pwr.bezp.communicator2.actions.response.RespGetConversationMessages;
import pl.edu.pwr.bezp.communicator2.client.CommunicatorClient;
import pl.edu.pwr.bezp.communicator2.client.SocketsConnectionLayer;
import pl.edu.pwr.bezp.communicator2.client.crytoUtilsi.AES;

@Component("getConversationMessages")
public class ActionGetConversationMessages extends AbstractAction {
    public ActionGetConversationMessages(SocketsConnectionLayer connectionLayer, AES aes) {
        super(connectionLayer, aes);
    }

    @Override
    public RespAbstract run(CommunicatorClient communicatorClient) {
        return getGetConversationMessages(bodyCreator.fillConversationNameBody());
    }

    @Override
    public RespAbstract run(Body body) {
        return getGetConversationMessages(body);
    }

    private RespGetConversationMessages getGetConversationMessages(Body body) {
        return new RespGetConversationMessages(communication(new RequestData("getConversationMessages",
                body)));
    }
}
