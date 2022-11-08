package pl.edu.pwr.bezp.communicator2.actions;

import org.springframework.stereotype.Component;
import pl.edu.pwr.bezp.communicator2.actions.body.MessageBody;
import pl.edu.pwr.bezp.communicator2.actions.body.RequestData;
import pl.edu.pwr.bezp.communicator2.actions.response.RespAbstract;
import pl.edu.pwr.bezp.communicator2.client.SocketsConnectionLayer;
import pl.edu.pwr.bezp.communicator2.client.crytoUtilsi.AES;

@Component("message")
public class ActionSendMessage extends AbstractAction{
    public ActionSendMessage(SocketsConnectionLayer connectionLayer, AES aes) {
        super(connectionLayer, aes);
    }

    @Override
    public RespAbstract run() {
        return new RespAbstract(communication(new RequestData("message", new MessageBody()))) {
        };
    }
}
