package UC.urbancraft.ucracing1.Util;

import org.bukkit.Location;

public class RaceCheckpoint {

    private final Location location;
    private final int order;

    public RaceCheckpoint(Location location, int order) {
        this.location = location;
        this.order = order;
    }

    public Location getLocation() {
        return location;
    }

    public int getOrder() {
        return order;
    }
}