package pl.edu.pwr.bezp.communicator2.actions.response;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public abstract class RespAbstract {
    private final HttpStatus responseStatus;
    private final String responseText;

    protected final ResponseReader responseReader;
    public RespAbstract(String respText) {
        this.responseReader = new TerminalOutput();
        try {
            var resp = new JSONObject(respText);
            responseStatus = HttpStatus.valueOf(resp.getInt("status"));
            responseText = (String) resp.get("response");
            if(responseStatus!=HttpStatus.OK){
                responseReader.unSuccessfulAction(responseText);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public HttpStatus getResponseStatus() {
        return responseStatus;
    }

    public String getResponseText() {
        return responseText;
    }
}
