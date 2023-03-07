package ObjectDecoder;

import org.junit.jupiter.api.Test;

public class FNamesTest {
    @Test
    public void testNames() {
        //This works, must keep same fnames
        FNames fNames = new FNames();
    }
    @Test
    public void testRandom() {
        //this fails and always returns first name
        //System.out.println(new FNames().getNextName());
        //System.out.println(new FNames().getNextName());
        //System.out.println(new FNames().getNextName());
        //System.out.println(new FNames().getNextName());
    }

}
