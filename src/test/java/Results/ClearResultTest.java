package Results;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClearResultTest {
    private ClearResult clearResult;
    @Test
    public void inherits() {
        clearResult = new ClearResult( "Hello", true);
        assertTrue(clearResult.isSuccess());
    }
}
