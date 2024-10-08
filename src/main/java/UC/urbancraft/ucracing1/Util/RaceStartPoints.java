package UC.urbancraft.ucracing1.Util;

import org.bukkit.Location;

public class RaceStartPoints {

    private final Location location;

    public RaceStartPoints(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}