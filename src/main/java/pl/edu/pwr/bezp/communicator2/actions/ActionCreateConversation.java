package pl.edu.pwr.bezp.communicator2.actions;

import org.springframework.stereotype.Component;
import pl.edu.pwr.bezp.communicator2.actions.body.ConversationCreatorBody;
import pl.edu.pwr.bezp.communicator2.actions.body.RequestData;
import pl.edu.pwr.bezp.communicator2.actions.response.RespAbstract;
import pl.edu.pwr.bezp.communicator2.actions.response.RespCreateConversation;

@Component("createConversation")
public class ActionCreateConversation extends AbstractAction {
//    public ActionCreateConversation(String conversationName, List<String> users) throws Exception {
//        super("createConversation", makeBody(conversationName, users));
//    }
//
//
//
//    private static JSONObject makeBody(String conversationName,List<String> users) throws Exception {
//
//        try {
//            var usersJSONArray = new JSONArray();
//            for (String user : users) {
//                usersJSONArray.put(user);
//            }
//            JSONObject body = new JSONObject();
//            body.put("name", conversationName);
//            body.put("users", usersJSONArray);
//            return body;
//        } catch (JSONException e) {
//            throw new Exception(e);
//        }
//    }

    @Override
    RespAbstract run() {
        return new RespCreateConversation(
                communication(new RequestData("createConversation", new ConversationCreatorBody())));
    }
}
