package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.jdbc.PreparedStatement;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLUsersDao implements Users {
    private Connection connection = null;
    public MySQLUsersDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public User findByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ? ";
        try {
            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(query);
            ResultSet rs = stmt.getResultSet();
            stmt.setString(1, username);

            return extractUser(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a user by username", e);
        }
    }
//


    private User extractUser(ResultSet rs) throws SQLException {
        if (! rs.next()) {
            return null;
        }
        return new User(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password")
        );
    }
    @Override
    public Long insert(User user) {
            String createInsertQuery = "INSERT INTO ads(username, email, password) VALUES(?, ?, ?) ";
        try {
            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(
                    createInsertQuery, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            stmt.setString(1, user.getUsername());
            stmt.setString(2,  user.getEmail());
            stmt.setString(3, user.getPassword() );
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }
}
