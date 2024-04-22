package com.openlearnhub.connectionpool.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;


public class ConnectionPool {



    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    private static ConnectionPool connectionPool;

    static {
        config.setJdbcUrl("jdbc:mysql://localhost:3306/learnjava");
        config.setUsername("root");
        config.setPassword("boquang321");
        config.setAutoCommit(false);
        config.setMaximumPoolSize(5);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }

    private ConnectionPool() {
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static ConnectionPool getInstance() {
        if (Objects.isNull(connectionPool)) {
            connectionPool = new ConnectionPool();
        }
        return connectionPool;
    }
}
