package UC.urbancraft.ucracing1;

import org.bukkit.plugin.java.JavaPlugin;

public final class UCRacing1 extends JavaPlugin {

    private RaceManager raceManager;

    @Override
    public void onEnable() {
      this.raceManager = new RaceManager();
    this.comm


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
