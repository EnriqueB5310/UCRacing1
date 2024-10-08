package UC.urbancraft.ucracing1;

import UC.urbancraft.ucracing1.listeners.RaceCheckpointListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class UCRacing1 extends JavaPlugin {

    private RaceManager raceManager;

    @Override
    public void onEnable() {
      this.raceManager = new RaceManager();
    getServer().getPluginManager().registerEvents(new RaceCheckpointListener(raceManager), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
