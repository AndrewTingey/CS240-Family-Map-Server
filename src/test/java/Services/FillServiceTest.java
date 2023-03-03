package Services;

import Requests.FillRequest;
import Results.ClearResult;
import Results.FillResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FillServiceTest {

    @BeforeEach
    public void clearPass() {
        //should probably insert something into the db here?
        ClearService clearService = new ClearService();
        ClearResult result = clearService.clear();
        assertTrue(result.isSuccess());
    }

    @Test
    public void fill() {
        FillRequest request = new FillRequest("andrewtingey", 1);
        FillResult result = new FillService().fill(request);
        System.out.println(result.getMessage());
        assertTrue(result.isSuccess());
    }


}
