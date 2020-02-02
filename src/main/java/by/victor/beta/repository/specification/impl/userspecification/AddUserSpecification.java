package by.victor.beta.repository.specification.impl.userspecification;

import by.victor.beta.entity.User;
import by.victor.beta.repository.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class AddUserSpecification implements Specification {
    private static final String sql = "INSERT INTO user (login,username,password,role,balance,status,registration_time,email) VALUES (?,?,?,?,?,?,NOW(),?)";
    private User user;

    public AddUserSpecification(User user) {
        this.user = user;
    }

    @Override
    public PreparedStatement specify(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getRole().ordinal());
            statement.setLong(5, user.getBalance());
            statement.setInt(6, user.getStatus().ordinal());
            statement.setString(7, user.getEmail());
        return statement;
    }
}
