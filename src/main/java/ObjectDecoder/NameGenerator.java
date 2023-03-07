package ObjectDecoder;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NameGenerator {
    private static class NamesList {
        private List<String> data;
    }
    protected String filename = null;
    private List<String> namesList = new ArrayList<>();

    public NameGenerator(String fileName) {
        this.filename = fileName;
    }

    public void setNames () {
        try {
            List<String> names = parse(new File(filename));
            for (String name : names) {
                namesList.add(name);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private List<String> parse( File file ) {
        try (FileReader fr = new FileReader((file));
             BufferedReader br = new BufferedReader(fr)) {

            Gson gson = new Gson();
            NamesList names = gson.fromJson(br, NamesList.class);
            return names.data;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getRandomName() {
        int randomIndex = new Random().nextInt(namesList.size());
        return namesList.get(randomIndex);
    }
}
