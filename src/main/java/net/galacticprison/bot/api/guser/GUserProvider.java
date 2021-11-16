package net.galacticprison.bot.api.guser;

import net.galacticprison.bot.impl.GUser;

import java.util.List;
import java.util.Optional;

public interface GUserProvider {

    /**
     * Unloads the GUser object from cache.
     * @param userID the ID of the DUser.
     */
    void unloadGUser(String userID);

    /**
     * Checks if the local cache contains the GUser object
     * related the input UserID.
     * @param userID the ID of the DUser.
     * @return whether it contains the GUser object.
     */
    boolean cacheContains(String userID);

    /**
     * Loads the GUser from the database.
     * @param userID the ID of the DUser to load.
     */
    void loadGUser(String userID);

    /**
     * Returns a GUser by their ID.
     * @param userID the ID of the user you want.
     * @return the GUser object.
     */
    Optional<GUser> getGUserByID(String userID);

    /**
     * Returns a GUser by their username.
     * @param username the username of the user you want.
     * @return the GUser object.
     */
    Optional<GUser> getGUserByName(String username);

    /**
     * Returns a List of GUser objects based on
     * how many points they have.
     * @param pointMinimum the amount of points you want to start at.
     * @return the list of GUser objects.
     */
    List<GUser> getGUserListByPoints(long pointMinimum);

    /**
     * Returns a list of GUserObjects.
     * @return the List of Raw GUser objects.
     */
    List<GUser> getGUserList();

}
