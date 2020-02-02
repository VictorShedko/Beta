package by.victor.beta.repository.specification.impl.userspecification;

import by.victor.beta.entity.Role;
import by.victor.beta.repository.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindUserByRoleSpecification implements Specification {
    private static final String SELECT_USER_BY_ROLE = "SELECT user.login,user.username,user.password,user.role,user.balance,user.status,user.registration_time,user.global_id,user.photo FROM user WHERE role = ?";//todo rename
    private int role;

    public FindUserByRoleSpecification(Role role) {
        this.role = role.ordinal();
    }

    @Override
    public PreparedStatement specify(Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_ROLE);
        ps.setInt(1, role);
        return ps;
    }
}
