package UC.urbancraft.ucracing1;

import java.util.HashMap;
import java.util.Map;

public class RaceManager {

    private final RaceDatabase database;
    private final Map<String, Race> races;

    public RaceManager(RaceDatabase database) {
        this.database = database;
        this.races = new HashMap<>();
        loadRacesFromDatabase();
    }

    // Add a new race to the manager
    public void addRace(Race race) {
        races.put(race.getName(), race);
        race.saveToDatabase(database);
    }

    // Get a race by name
    public Race getRace(String name) {
        return races.get(name);
    }

    // Load races from the database
    private void loadRacesFromDatabase() {
        // Logic to load races from the MySQL database and populate `races` map
        database.loadRaces(races);
    }
}