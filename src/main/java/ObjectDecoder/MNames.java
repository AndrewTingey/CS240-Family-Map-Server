package ObjectDecoder;

import java.io.File;
public class MNames extends NameGenerator {
    public MNames(){
        super("json" + File.separator + "mnames.json");
        this.setNames();
    }
}
