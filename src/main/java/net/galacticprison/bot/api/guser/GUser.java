package net.galacticprison.bot.api.guser;

public interface GUser {

    /**
     * Add points to a GUser.
     * @param amount the amount you want to add.
     */
    void addPoints(long amount);

    /**
     * Remove points from a GUser.
     * @param amount the amount you want to remove.
     */
    void removePoints(long amount);

    /**
     * Gets the DUser ID.
     * @return the ID in String format.
     */
    String getDUserID();


    /**
     * Gets the DUser Name.
     * @return the Name in String format.
     */
    String getDUserName();

}
