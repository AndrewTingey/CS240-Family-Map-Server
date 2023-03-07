package ObjectDecoder;

import java.io.File;

public class SNames extends NameGenerator {
    public SNames() {
        super("json" + File.separator + "snames.json");
        this.setNames();
    }
}
