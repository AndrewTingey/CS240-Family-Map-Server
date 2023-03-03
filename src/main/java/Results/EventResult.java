package Results;
import Model.Event;

/**
 * Object to represent responses for searching events
 */
public class EventResult extends Result {
    /**
     * array of event objects
     */
    private Event[] data;

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
    public EventResult( Event[] data, boolean success, String message ) {
        this.data = data;
        this.success = success;
        this.message = message;
    }

    public Event[] getData() {
        return data;
    }

    public void setData( Event[] data ) {
        this.data = data;
    }
}
