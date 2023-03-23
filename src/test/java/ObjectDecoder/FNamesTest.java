package ObjectDecoder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FNamesTest {
    @Test
    public void testRandomName() {
        //this fails and always returns first name
        //System.out.println(new FNames().getNextName());
        //System.out.println(new FNames().getNextName());
        //System.out.println(new FNames().getNextName());
        //System.out.println(new FNames().getNextName());
        FNames fNames = new FNames();
        assertNotEquals(fNames.getRandomName(), fNames.getRandomName());
        assertNotEquals(fNames.getRandomName(), fNames.getRandomName());
        assertNotEquals(fNames.getRandomName(), fNames.getRandomName());
    }

}
