package UC.urbancraft.ucracing1;

import UC.urbancraft.ucracing1.Util.RaceCheckpoint;
import UC.urbancraft.ucracing1.Util.RaceStartOrder;
import UC.urbancraft.ucracing1.Util.RaceStartPoints;
import org.bukkit.Location;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class Race {

    private final UUID id;
    private final Instant createdAt;
    private final int minPlayers;

    private String name;
    private Location spawn;
    private List<RaceCheckpoint> checkpoints;
    private List<RaceStartPoints> startPoints;
    private RaceStartOrder startOrder;

    // Constructor for creating a race manually
    public Race(String name, int minPlayers) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.minPlayers = minPlayers;
        this.name = name;
        this.spawn = spawn;
        this.checkpoints = checkpoints;
        this.startPoints = startPoints;
        this.startOrder = startOrder;
    }

    // Constructor for loading race from MySQL
    public Race(UUID id, Instant createdAt, int minPlayers, String name, Location spawn, List<RaceCheckpoint> checkpoints, List<RaceStartPoints> startPoints, RaceStartOrder startOrder) {
        this.id = id;
        this.createdAt = createdAt;
        this.minPlayers = minPlayers;
        this.name = name;
        this.spawn = spawn;
        this.checkpoints = checkpoints;
        this.startPoints = startPoints;
        this.startOrder = startOrder;
    }

    // Getters and setters for race properties
    public UUID getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getSpawn() {
        return spawn;
    }

    public void setSpawn(Location spawn) {
        this.spawn = spawn;
    }

    public List<RaceCheckpoint> getCheckpoints() {
        return checkpoints;
    }

    public void setCheckpoints(List<RaceCheckpoint> checkpoints) {
        this.checkpoints = checkpoints;
    }

    public List<RaceStartPoints> getStartPoints() {
        return startPoints;
    }

    public void setStartPoints(List<RaceStartPoints> startPoints) {
        this.startPoints = startPoints;
    }

    public RaceStartOrder getStartOrder() {
        return startOrder;
    }

    public void setStartOrder(RaceStartOrder startOrder) {
        this.startOrder = startOrder;
    }

    public int getMinPlayers() {
        return minPlayers;
    }
}