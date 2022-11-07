package pl.edu.pwr.bezp.communicator2.actions.response;

import pl.edu.pwr.bezp.communicator2.actions.response.message.Message;

import java.util.ArrayList;
import java.util.List;

public class RespGetMessages extends RespAbstract {
    List<Message> messages = new ArrayList<>();

    public RespGetMessages(String respText) {
        super(respText);
//       todo poprawic bo trzeba jeszcze mapowac konwersacjie i nazyw try {
//            var messages = new JSONObject(respText).getJSONObject("body").getJSONArray("messages");
//            if (messages.length() == 0)
//                return;
//            for (int i = 0; i < messages.length(); i++) {
//                var messageItem = messages.getJSONObject(i);
//
//                this.messages.add(new Message(messageItem.getString("author"),
//                        messageItem.getString("dateTime"), messageItem.getString("content")));
//            }
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        }
    }
}
