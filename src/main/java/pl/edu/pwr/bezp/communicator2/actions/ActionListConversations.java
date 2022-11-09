package pl.edu.pwr.bezp.communicator2.actions;

import org.springframework.stereotype.Component;
import pl.edu.pwr.bezp.communicator2.actions.body.Body;
import pl.edu.pwr.bezp.communicator2.actions.body.RequestData;
import pl.edu.pwr.bezp.communicator2.actions.response.RespAbstract;
import pl.edu.pwr.bezp.communicator2.actions.response.RespListConversations;
import pl.edu.pwr.bezp.communicator2.client.CommunicatorClient;
import pl.edu.pwr.bezp.communicator2.client.SocketsConnectionLayer;
import pl.edu.pwr.bezp.communicator2.client.crytoUtilsi.AES;

@Component("listConversations")
public class ActionListConversations extends AbstractAction {
    public ActionListConversations(SocketsConnectionLayer connectionLayer, AES aes) {
        super(connectionLayer, aes);
    }

    @Override
    public RespAbstract run(CommunicatorClient communicatorClient) {
        return getListConversations();
    }

    @Override
    public RespAbstract run(Body body) {
        return getListConversations();
    }

    private RespListConversations getListConversations() {
        return new RespListConversations(communication(new RequestData("listConversations", null)));
    }
}