package Services;

import Results.ClearResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClearServiceTest {
    private ClearService clearService;
    @Test
    public void clearPass() {
        //should probably insert something into the db here?
        clearService = new ClearService();
        ClearResult result = clearService.clear();
        assertTrue(result.isSuccess());
    }
}
