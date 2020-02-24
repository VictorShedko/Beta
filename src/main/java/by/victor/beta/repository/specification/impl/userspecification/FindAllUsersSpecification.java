package by.victor.beta.repository.specification.impl.userspecification;

import by.victor.beta.repository.RepositoryException;
import by.victor.beta.repository.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindAllUsersSpecification implements Specification {
    private static final String FIND_USER_BY_NAME = "SELECT user.login,user.username,user.role,user.balance," +
            "user.status,user.registration_time,user.global_id,user.photo,user.salt,user.hash_pass,user.email " +
            "FROM user ;";

    @Override
    public PreparedStatement specify(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_NAME);
        return preparedStatement;
    }

}
