package by.victor.beta.repository.specification.impl.userspecification;

import by.victor.beta.repository.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindUserByNameSpecification implements Specification {
    private static final String SELECT_BY_NAME = "SELECT user.login,user.username," +
            "user.role,user.balance, user.status,user.registration_time,user.global_id,user.photo,user.salt," +
            "user.hash_pass,user.email " +
            "FROM user " +
            "WHERE username = ?";
    private String name;

    public FindUserByNameSpecification(String name) {
        this.name = name;
    }

    @Override
    public PreparedStatement specify(Connection connection) throws SQLException {
       PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_NAME);
            preparedStatement.setString(1, name);
            return preparedStatement;
    }

}
