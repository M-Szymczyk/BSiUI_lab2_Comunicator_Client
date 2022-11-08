package pl.edu.pwr.bezp.communicator2.actions;

import org.springframework.stereotype.Component;
import pl.edu.pwr.bezp.communicator2.actions.body.RequestData;
import pl.edu.pwr.bezp.communicator2.actions.response.RespAbstract;
import pl.edu.pwr.bezp.communicator2.actions.response.RespGetMessages;
import pl.edu.pwr.bezp.communicator2.client.SocketsConnectionLayer;
import pl.edu.pwr.bezp.communicator2.client.crytoUtilsi.AES;

@Component("getMessages")
public class ActionGetUnreadMessages extends AbstractAction {
    public ActionGetUnreadMessages(SocketsConnectionLayer connectionLayer, AES aes) {
        super(connectionLayer, aes);
    }

    @Override
    public RespAbstract run() {
        return new RespGetMessages(communication(new RequestData("getMessages", null)));
    }
}
