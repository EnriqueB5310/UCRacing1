package UC.urbancraft.ucracing1;

import UC.urbancraft.ucracing1.Util.RaceCheckpoint;
import UC.urbancraft.ucracing1.Util.RaceStartPoints;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class RaceSession {

    private final Race race;
    private final List<Player> participants;
    private final Map<Player, Integer> playerProgress; // Tracks which checkpoint the player is on
    private final List<Player> finishedPlayers; // List of players who have finished

    private boolean isRunning = false;
    private final Map<Player, Location> originalLocations; // To store original locations of players before race

    public RaceSession(Race race, List<Player> participants) {
        this.race = race;
        this.participants = participants;
        this.playerProgress = new HashMap<>();
        this.finishedPlayers = new ArrayList<>();
        this.originalLocations = new HashMap<>();

        // Initialize all players' progress at 0 (before first checkpoint)
        for (Player player : participants) {
            playerProgress.put(player, 0);
        }
    }

    // Start the race session
    public void start() {
        if (isRunning) return;
        isRunning = true;

        // Teleport players to the spawn location or start points
        teleportPlayersToStart(player);

        // Start the countdown
        startCountdown();
    }

    // Teleport all players to start points
    public void teleportPlayersToStart(Player player) {
        List<RaceStartPoints> startPoints = race.getStartPoints();
        for (int i = 0; i < participants.size(); i++) {
            Player player = participants.get(i);
            originalLocations.put(player, player.getLocation()); // Store original locations
            if (i < startPoints.size()) {
                player.teleport(startPoints.get(i).getLocation());
            } else {
                player.teleport(race.getSpawn()); // Default to spawn if not enough start points
            }
        }
    }

    // Start a countdown before race begins
    public void startCountdown() {
        Plugin MyPlugin = this.Plugin;
        new BukkitRunnable() {
            int countdown = 5; // Countdown from 5 seconds

            @Override
            public void run() {
                if (countdown > 0) {
                    Bukkit.broadcastMessage(ChatColor.GREEN + "Race starting in " + countdown + "...");
                    countdown--;
                } else {
                    Bukkit.broadcastMessage(ChatColor.GREEN + "Go!");
                    runRace();
                    cancel();
                }
            }
        }.runTaskTimer(MyPlugin.getInstance(), 0L, 20L); // 20L = 1 second
    }

    // Start the race (track players as they progress through checkpoints)
    private void runRace() {
        for (Player player : participants) {
            player.sendMessage(ChatColor.GOLD + "Race has begun! Reach all checkpoints in order.");
        }
    }

    // Handle a player reaching a checkpoint
    public void playerReachedCheckpoint(Player player, Location checkpointLocation) {
        int currentProgress = playerProgress.get(player);
        List<RaceCheckpoint> checkpoints = race.getCheckpoints();

        // Check if the player is at the correct checkpoint in sequence
        if (currentProgress < checkpoints.size()) {
            RaceCheckpoint nextCheckpoint = checkpoints.get(currentProgress);
            if (isAtCheckpoint(player, nextCheckpoint.getLocation(), checkpointLocation)) {
                playerProgress.put(player, currentProgress + 1);
                player.sendMessage(ChatColor.AQUA + "Checkpoint " + (currentProgress + 1) + " reached!");

                // If player reached the last checkpoint
                if (currentProgress + 1 == checkpoints.size()) {
                    playerFinishedRace(player);
                }
            }
        }
    }

    // Check if player is at the right checkpoint (within small radius)
    private boolean isAtCheckpoint(Player player, Location checkpoint, Location current) {
        return checkpoint.distance(current) < 2.0; // Within 2 blocks of the checkpoint
    }

    // Handle race finish for a player
    private void playerFinishedRace(Player player) {
        finishedPlayers.add(player);
        player.sendMessage(ChatColor.GOLD + "You have finished the race!");

        int place = finishedPlayers.size();
        announcePlace(player, place);

        // Check if all players have finished the race
        if (finishedPlayers.size() == participants.size()) {
            endRace();
        }
    }

    // Announce player's place (1st, 2nd, 3rd, etc.)
    private void announcePlace(Player player, int place) {
        String placeStr;
        switch (place) {
            case 1:
                placeStr = ChatColor.GOLD + "1st Place!";
                break;
            case 2:
                placeStr = ChatColor.SILVER + "2nd Place!";
                break;
            case 3:
                placeStr = ChatColor.BRONZE + "3rd Place!";
                break;
            default:
                placeStr = place + "th Place!";
        }
        Bukkit.broadcastMessage(player.getDisplayName() + " has finished in " + placeStr);
    }

    // End the race and announce results
    private void endRace() {
        Bukkit.broadcastMessage(ChatColor.GREEN + "The race is over! Thank you for participating!");

        // Optionally teleport players back to their original locations
        for (Player player : participants) {
            player.teleport(originalLocations.get(player));
        }

        // Clean up session
        isRunning = false;
    }

    public boolean isParticipant(Player player) {
    }
}