package Requests;

public class Request {
    //parent class for all requests
    protected String username;

    public Request( ) {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }
}
