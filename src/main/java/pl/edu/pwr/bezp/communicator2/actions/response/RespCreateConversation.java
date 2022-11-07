package pl.edu.pwr.bezp.communicator2.actions.response;

import java.util.ArrayList;
import java.util.List;

public class RespCreateConversation extends RespAbstract{
    private List<String> users = new ArrayList<>();
    public RespCreateConversation(String respText) {
        super(respText);
    }

    public List<String> getUsers() {
        return users;
    }
}
