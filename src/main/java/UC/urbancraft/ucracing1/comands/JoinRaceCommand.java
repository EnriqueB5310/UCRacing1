package UC.urbancraft.ucracing1.comands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class JoinRaceCommand implements CommandExecutor {

    private final List<Player> registeredPlayers = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can join races.");
            return true;
        }

        Player player = (Player) sender;
        if (!registeredPlayers.contains(player)) {
            registeredPlayers.add(player);
            player.sendMessage("You have joined the race!");
        } else {
            player.sendMessage("You are already in the race.");
        }

        return true;
    }

    public List<Player> getRegisteredPlayers() {
        return registeredPlayers;
    }
}