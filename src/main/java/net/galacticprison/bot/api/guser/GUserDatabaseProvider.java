package net.galacticprison.bot.api.guser;

import net.dv8tion.jda.api.JDA;
import net.galacticprison.bot.impl.GUser;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface GUserDatabaseProvider {

    /**
     * Gets the user from the database by their ID.
     * @param userID the ID of the user in discord.
     * @return the GUser object.
     */
    GUser getUser(String userID);

    /**
     * Loads the GUser by fetching data from the result set.
     * @param resultSet the data retrieved from the database.
     * @param jda the Java Discord API.
     * @return the GUser object.
     */
    GUser loadGUser(JDA jda, ResultSet resultSet) throws SQLException;

    /**
     * Load a GUser object into the database.
     * @param gUser the GUser object target.
     */
    void putGUser(GUser gUser);

}
