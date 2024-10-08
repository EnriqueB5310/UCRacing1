package UC.urbancraft.ucracing1;

import java.util.HashMap;
import java.util.Map;

public class RaceManager {

    private final Map<String, Race> races = new HashMap<>();

    public void createRace(String name, Race race) {
        races.put(name, race);
    }

    public Race getRace(String name) {
        return races.get(name);
    }

    public void deleteRace(String name) {
        races.remove(name);
    }
}