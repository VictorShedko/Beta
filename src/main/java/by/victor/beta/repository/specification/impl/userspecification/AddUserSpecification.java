package by.victor.beta.repository.specification.impl.userspecification;

import by.victor.beta.entity.User;
import by.victor.beta.repository.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class AddUserSpecification implements Specification {
    private static final String sql = "INSERT INTO user (login,username,role,balance,status,registration_time,email,salt,hash_pass) VALUES (?,?,?,?,?,NOW(),?,?,?)";
    private User user;

    public AddUserSpecification(User user) {
        this.user = user;
    }

    @Override
    public PreparedStatement specify(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getUsername());

        statement.setInt(3, user.getRole().ordinal());
        statement.setLong(4, user.getBalance());
        statement.setInt(5, user.getStatus().ordinal());
        statement.setString(6, user.getEmail());
        statement.setBytes(7, user.getSalt());
        statement.setBytes(8, user.getPassword());
        return statement;
    }
}
