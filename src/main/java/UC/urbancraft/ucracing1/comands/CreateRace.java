package UC.urbancraft.ucracing1.comands;

import UC.urbancraft.ucracing1.Race;
import UC.urbancraft.ucracing1.RaceDatabase;
import UC.urbancraft.ucracing1.RaceManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateRace implements CommandExecutor {

    private final RaceManager raceManager;
    private final RaceDatabase database;

    public CreateRace(RaceManager raceManager, RaceDatabase database) {
        this.raceManager = raceManager;
        this.database = database;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only players can create races.");
            return false;
        }

        if (strings.length < 2) {
            commandSender.sendMessage("Usage: /createrace <name> <minPlayers>");
            return false;
        }

        String raceName = strings[0];
        int minPlayers;

        try {
            minPlayers = Integer.parseInt(strings[1]);
        } catch (NumberFormatException e) {
            commandSender.sendMessage("Invalid number for minPlayers.");
            return false;
        }

        // Create a new race and add it to the race manager
        Race race = new Race(raceName, minPlayers);
        raceManager.addRace(race);

        // Save race to the database
        database.saveRace(race);

        commandSender.sendMessage("Race " + raceName + " created with a minimum of " + minPlayers + " players.");
        return true;
    }
}