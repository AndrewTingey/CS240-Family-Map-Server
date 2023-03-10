package Requests;

public class FillRequest extends Request{
    private int generations;

    public FillRequest( String username, int generations ) {
        this.username = username;
        this.generations = generations;
    }

    public FillRequest() {
    }
    public int getGenerations() {
        return generations;
    }

    public void setGenerations( int generations ) {
        this.generations = generations;
    }
}
