package pl.edu.pwr.bezp.communicator2.actions;

import org.springframework.stereotype.Component;
import pl.edu.pwr.bezp.communicator2.actions.body.BodyCreator;
import pl.edu.pwr.bezp.communicator2.actions.body.RequestData;
import pl.edu.pwr.bezp.communicator2.actions.response.RespAbstract;
import pl.edu.pwr.bezp.communicator2.actions.response.RespListUsers;
import pl.edu.pwr.bezp.communicator2.client.CommunicatorClient;
import pl.edu.pwr.bezp.communicator2.client.SocketsConnectionLayer;
import pl.edu.pwr.bezp.communicator2.client.crytoUtilsi.AES;

@Component("listUsers")
public class ActionListUsers extends AbstractAction {
    public ActionListUsers(SocketsConnectionLayer connectionLayer, AES aes, BodyCreator bodyCreator) {
        super(connectionLayer, aes, bodyCreator);
    }

    @Override
    public RespAbstract run(CommunicatorClient communicatorClient) {
        return new RespListUsers(communication(new RequestData("listUsers", null)));
    }
}