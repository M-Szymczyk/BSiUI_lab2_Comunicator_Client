package pl.edu.pwr.bezp.communicator2.actions.response;

import org.json.JSONException;
import org.json.JSONObject;
import pl.edu.pwr.bezp.communicator2.actions.response.message.Message;

import java.util.ArrayList;
import java.util.List;

public class RespGetConversationMessages extends RespAbstract {
    List<Message> messages = new ArrayList<>();

    public RespGetConversationMessages(String respText) {
        super(respText);
        try {
            var messages = new JSONObject(respText).getJSONObject("body").getJSONArray("messages");
            if (messages.length() == 0)
                return;
            for (int i = 0; i < messages.length(); i++) {
                var messageItem = messages.getJSONObject(i);

                this.messages.add(new Message(messageItem.getString("author"),
                        messageItem.getString("dateTime"), messageItem.getString("content")));
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
