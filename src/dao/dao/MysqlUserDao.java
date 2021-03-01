package dao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.model.User;
import dao.util.ConnectionProvider;
import dao.util.DbOperationException;


public class MysqlUserDao implements UserDao {
    
    private final static String CREATE = "INSERT INTO user(pesel, firstname, lastname) VALUES(?, ?, ?);";
    private final static String READ = "SELECT pesel, firstname, lastname FROM user WHERE pesel = ?;";
    private final static String UPDATE = "UPDATE user SET pesel=?, firstname=?, lastname=? WHERE pesel = ?;";
    private final static String DELETE = "DELETE FROM user WHERE pesel=?;";

    @Override
    public void create(User user) {
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement prepStmt = conn.prepareStatement(CREATE);) {
            prepStmt.setString(1, user.getPesel());
            prepStmt.setString(2, user.getFirstName());
            prepStmt.setString(3, user.getLastName());
            prepStmt.executeUpdate();
        } catch(SQLException e) {
            throw new DbOperationException(e);
        }
    }

    @Override
    public User read(String pesel) {
        User resultUser = null;
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement prepStmt = conn.prepareStatement(READ);) {
            prepStmt.setString(1, pesel);
            ResultSet resultSet = prepStmt.executeQuery();
            if(resultSet.next()) {
                resultUser = new User();
                resultUser.setPesel(resultSet.getString("pesel"));
                resultUser.setFirstName(resultSet.getString("firstname"));
                resultUser.setLastName(resultSet.getString("lastname"));
            }
        } catch(SQLException e) {
            throw new DbOperationException(e);
        }
        return resultUser;
    }

    @Override
    public void update(User user) {
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement prepStmt = conn.prepareStatement(UPDATE);) {
            prepStmt.setString(1, user.getPesel());
            prepStmt.setString(2, user.getFirstName());
            prepStmt.setString(3, user.getLastName());
            prepStmt.setString(4, user.getPesel());
            prepStmt.executeUpdate();
        } catch(SQLException e) {
            throw new DbOperationException(e);
        }
    }

    @Override
    public void delete(User user) {
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement prepStmt = conn.prepareStatement(DELETE);) {
            prepStmt.setString(1, user.getPesel());
            prepStmt.executeUpdate();
        } catch(SQLException e) {
            throw new DbOperationException(e);
        }
    }
}