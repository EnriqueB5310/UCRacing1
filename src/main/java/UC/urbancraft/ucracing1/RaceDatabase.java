package UC.urbancraft.ucracing1;

import UC.urbancraft.ucracing1.Util.RaceCheckpoint;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RaceDatabase {

    private static final String URL = "";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void saveRace(Race race) {
        String query = "INSERT INTO races (id, name, minPlayers, createdAt) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, race.getId().toString());
            statement.setString(2, race.getName());
            statement.setInt(3, race.getMinPlayers());
            statement.setTimestamp(4, java.sql.Timestamp.from(race.getCreatedAt()));
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveRaceCheckpoints(Race race) {
        String query = "INSERT INTO race_checkpoints (race_id, checkpoint_order, x, y, z) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            for (int i = 0; i < race.getCheckpoints().size(); i++) {
                RaceCheckpoint checkpoint = race.getCheckpoints().get(i);
                statement.setString(1, race.getId().toString());
                statement.setInt(2, i);
                statement.setDouble(3, checkpoint.getLocation().getX());
                statement.setDouble(4, checkpoint.getLocation().getY());
                statement.setDouble(5, checkpoint.getLocation().getZ());
                statement.addBatch();
            }
            statement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
