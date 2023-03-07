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
        FillRequest request = new FillRequest("andrewtingey", 4);
        FillResult result = new FillService().fill(request);
        System.out.println(result.getMessage());
        assertTrue(result.isSuccess());
    }

    @Test
    public void uniqueNames() {
        FillRequest r1 = new FillRequest("Andy", 2);
        FillRequest r2 = new FillRequest("Gran", 2);
        FillResult result1 = new FillService().fill(r1);
        FillResult result2 = new FillService().fill(r2);
        System.out.println(result1.getMessage());
        assertTrue(result1.isSuccess());
        System.out.println(result2.getMessage());
        assertTrue(result2.isSuccess());


    }


}
