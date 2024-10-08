package UC.urbancraft.ucracing1.comands;

import UC.urbancraft.ucracing1.Race;
import UC.urbancraft.ucracing1.RaceManager;
import UC.urbancraft.ucracing1.RaceSession;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class StartRaceCommand implements CommandExecutor {

    private final RaceManager raceManager;
    private final List<Player> participants;

    public StartRaceCommand(RaceManager raceManager, List<Player> participants) {
        this.raceManager = raceManager;
        this.participants = participants;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can start races.");
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage("Usage: /startrace <race_name>");
            return true;
        }

        String raceName = args[0];
        Race race = raceManager.getRace(raceName);
        if (race == null) {
            sender.sendMessage("Race not found.");
            return true;
        }

        // Create a new race session
        RaceSession raceSession = new RaceSession(race, participants);
        raceSession.start();

        return true;
    }
}
