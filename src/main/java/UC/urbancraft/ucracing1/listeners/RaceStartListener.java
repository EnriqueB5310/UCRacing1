package UC.urbancraft.ucracing1.listeners;


import UC.urbancraft.ucracing1.RaceManager;
import UC.urbancraft.ucracing1.RaceSession;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;

public class RaceStartListener implements Listener {

    private final RaceManager raceManager;
    private final RaceSession raceSession;

    public RaceStartListener(RaceManager raceManager, RaceSession raceSession) {
        this.raceManager = raceManager;
        this.raceSession = raceSession;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Check if the player is part of a race session
        RaceSession raceSession = raceManager.getRaceSessionForPlayer(player);
        if (raceSession != null && raceSession.isStarting()) {
            raceSession.teleportPlayersToStart(player);
            raceSession.startCountdown();
        }
    }
}
