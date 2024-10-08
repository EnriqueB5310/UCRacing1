package UC.urbancraft.ucracing1.listeners;

import UC.urbancraft.ucracing1.RaceManager;
import UC.urbancraft.ucracing1.RaceSession;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.entity.Player;

public class RaceCheckpointListener implements Listener {

    private final RaceSession raceSession;

    public RaceCheckpointListener(RaceManager raceSession) {
        this.raceSession = raceSession;
    }


    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (raceSession.isParticipant(player)) {
            raceSession.playerReachedCheckpoint(player, player.getLocation());
        }
    }
}