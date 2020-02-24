package by.victor.beta.repository.specification.impl.userspecification;

import by.victor.beta.repository.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindUserByLoginSpecification implements Specification {
    private static final String FIND_USER_BY_NAME = "SELECT user.login,user.username,user.role,user.balance," +
            "user.status,user.registration_time,user.global_id,user.photo,user.salt,user.hash_pass,user.email " +
            "FROM user WHERE user.login=?;";
    private String login;

    public FindUserByLoginSpecification(String login) {
        this.login = login;
    }

    @Override
    public PreparedStatement specify(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_NAME);
        preparedStatement.setString(1, login);
         return preparedStatement;
    }


}
