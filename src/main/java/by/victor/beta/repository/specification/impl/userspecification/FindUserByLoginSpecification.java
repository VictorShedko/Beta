package by.victor.beta.repository.specification.impl.userspecification;

import by.victor.beta.repository.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindUserByLoginSpecification implements Specification {
    private static final String sql = "SELECT user.login,user.username,user.password,user.role,user.balance,user.status,user.registration_time,user.global_id,user.photo FROM user WHERE user.login=?;";//todo rename
    private String login;

    public FindUserByLoginSpecification(String login) {
        this.login = login;
    }

    @Override
    public PreparedStatement specify(Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, login);
         return ps;
    }


}
