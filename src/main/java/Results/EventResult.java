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
    public EventResult() {
    }

    /**
     * parameterized constructor
     * @param data
     * @param success
     * @param message
     */
    public EventResult( List<Event> data, boolean success, String message ) {
        this.data = data;
        this.success = success;
        this.message = message;
    }

    public List<Event> getData() {
        return data;
    }

    public void setData( List<Event> data ) {
        this.data = data;
    }
}
