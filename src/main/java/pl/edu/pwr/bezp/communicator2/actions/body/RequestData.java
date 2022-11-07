package pl.edu.pwr.bezp.communicator2.actions.body;

public class RequestData {
    private String action;
    private Body body;

    public RequestData(String action, Body body) {
        this.action = action;
        this.body = body;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}