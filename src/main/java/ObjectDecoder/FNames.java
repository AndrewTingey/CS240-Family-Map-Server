package ObjectDecoder;

import java.io.*;

public class FNames extends NameGenerator {
    public FNames(){
        super("json" + File.separator + "fnames.json");
        this.setNames();
    }
}

