package by.victor.beta.repository.specification.impl.userspecification;

import by.victor.beta.entity.UserStatus;
import by.victor.beta.repository.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindUserByStatusSpecification implements Specification {
    private static final String SELECT_USER_BY_STATUS = "SELECT user.login,user.username," +
            "user.role,user.balance,user.status,user.registration_time,user.global_id," +
            "user.photo,user.salt,user.hash_pass,user.email " +
            "FROM user WHERE status = ?";
    private int state;

    public FindUserByStatusSpecification(UserStatus status) {
        this.state = status.ordinal();
    }

    @Override
    public PreparedStatement specify(Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_STATUS);
        ps.setInt(1, state);
        return ps;
    }
}
