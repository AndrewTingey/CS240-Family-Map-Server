package Results;
import Model.Event;

import java.util.List;

/**
 * Object to represent responses for searching events
 */
public class EventResult extends Result {
    /**
     * array of event objects
     */
    private List<Event> data;

    /**
     * default constructor
     */
    public EventResult(String message, boolean success) {
        super(message, success);
    }

    /**
     * parameterized constructor
     * @param data
     * @param success
     * @param message
     */
    public EventResult( List<Event> data, boolean success, String message ) {
        super(message, success);
        this.data = data;
    }

    public List<Event> getData() {
        return data;
    }

    public void setData( List<Event> data ) {
        this.data = data;
    }
}
