package net.galacticprison.bot.impl;

import net.dv8tion.jda.api.JDA;
import net.galacticprison.bot.GalacticBot;
import net.galacticprison.bot.api.mysql.MySQL;
import net.galacticprison.bot.api.guser.GUserDatabaseProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GUserDatabase extends MySQL implements GUserDatabaseProvider {

    private JDA jda;

    public GUserDatabase(JDA jda, String host, String port, String database, String username, String password) {
        super(host, port, database, username, password);
        this.jda = jda;
        createTable();
    }

    @Override
    public void createTable() {
        executeQuery("CREATE TABLE IF NOT EXISTS Users(USERID VARCHAR(20), USERNAME VARCHAR(100), POINTS BIGINT, PRIMARY KEY (USERID))");
    }

    @Override
    public GUser getUser(String userID) {

        try(Connection connection = super.getConnection(); PreparedStatement ps = connection.prepareStatement("SELECT * FROM Users WHERE USERID=?")) {
            ps.setString(1, userID);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet == null) return null;

            return loadGUser(this.jda, resultSet);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    @Override
    public GUser loadGUser(JDA jda, ResultSet resultSet) throws SQLException {
        return new GUser(
                jda.getUserById(resultSet.getString("USERID")),
                resultSet.getLong("POINTS")
        );
    }

    @Override
    public void putGUser(GUser gUser) {
        executeQuery("INSERT IGNORE INTO Users (USERID, USERNAME, POINTS) VALUES(?,?,?)", gUser.getDUserID(), gUser.getDUserName(), gUser.getPoints());
    }
}
