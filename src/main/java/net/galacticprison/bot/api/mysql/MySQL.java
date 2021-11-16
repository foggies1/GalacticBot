package net.galacticprison.bot.api.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class MySQL {

    private static HikariDataSource dataSource;

    public MySQL(String host, String port, String database, String username, String password){
        HikariConfig config = new HikariConfig();
        String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.addDataSourceProperty("cachePrepStmts","true");
        config.addDataSourceProperty("prepStmtCacheSize","250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit","2048");
        dataSource = new HikariDataSource(config);
    }

    public abstract void createTable();

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public synchronized void executeQuery(String sql, Object... replacements) {
        try (Connection c = getConnection(); PreparedStatement statement = c.prepareStatement(sql)) {
            if (replacements != null)
                for (int i = 0; i < replacements.length; i++)
                    statement.setObject(i + 1, replacements[i]);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
