package pl.edu.pwr.bezp.communicator2.actions;

import org.springframework.stereotype.Component;
import pl.edu.pwr.bezp.communicator2.actions.body.Body;
import pl.edu.pwr.bezp.communicator2.actions.body.RequestData;
import pl.edu.pwr.bezp.communicator2.actions.response.RespAbstract;
import pl.edu.pwr.bezp.communicator2.actions.response.RespMessage;
import pl.edu.pwr.bezp.communicator2.client.CommunicatorClient;
import pl.edu.pwr.bezp.communicator2.client.SocketsConnectionLayer;
import pl.edu.pwr.bezp.communicator2.client.crytoUtilsi.AES;

@Component("message")
public class ActionSendMessage extends AbstractAction{
    public ActionSendMessage(SocketsConnectionLayer connectionLayer, AES aes) {
        super(connectionLayer, aes);
    }

    @Override
    public RespAbstract run(CommunicatorClient communicatorClient) {
        return getMessage(bodyCreator.fillMessageBody(communicatorClient.getUserNames()));
    }

    @Override
    public RespAbstract run(Body body) {
        return getMessage(body);
    }

    private RespMessage getMessage(Body body) {
        return new RespMessage(communication(new RequestData("message", body)));
    }
}
