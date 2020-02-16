package by.victor.beta.repository.specification.impl.verifycode;

import by.victor.beta.repository.RepositoryException;
import by.victor.beta.repository.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindVerifyCodeByUuidSpecification implements Specification {
    private static final String sql="SELECT verify_code.code,verify_code.user_id,verify_code.time,user.username " +
            "FROM verify_code JOIN user ON user_id=global_id " +
            "WHERE verify_code.code=?";
    private String uuid;

    public FindVerifyCodeByUuidSpecification(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public PreparedStatement specify(Connection connection) throws SQLException, RepositoryException {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,uuid);
        return ps;
    }
}
