package ObjectDecoder;

import Model.Location;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LocationDeserializer {
    private static class Locations {
        private List<Location> data;
    }
    private String filename = "json" + File.separator + "locations.json";
    private List<Location> locationList = new ArrayList<>();

    public LocationDeserializer() {
        try {
            List<Location> locations = parse(new File(filename));
            for (Location location : locations) {
                locationList.add(location);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<Location> parse( File file ) {
        try(FileReader fr = new FileReader((file));
            BufferedReader br = new BufferedReader(fr)) {

            Gson gson = new Gson();
            Locations locationList = gson.fromJson(br, Locations.class);
            return locationList.data;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Location getRandomLocation() {
        int randomIndex = new Random().nextInt(locationList.size());
        return locationList.get(randomIndex);
    }


}
