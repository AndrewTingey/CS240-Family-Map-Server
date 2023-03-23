package Services;

import Results.ClearResult;
import org.junit.jupiter.api.Test;

public class ClearServiceTest {
    private ClearService clearService;
    @Test
    public void clearPass() {
        clearService = new ClearService();
        ClearResult result = clearService.clear();
        assertTrue(result.isSuccess());
    }
}
