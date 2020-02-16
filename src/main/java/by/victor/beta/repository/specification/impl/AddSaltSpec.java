package by.victor.beta.repository.specification.impl;

import by.victor.beta.repository.RepositoryException;
import by.victor.beta.repository.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Deprecated
public class AddSaltSpec implements Specification {

    private byte[] salt;
    private long id;

    private String sql = "UPDATE user SET user.hash_pass=? " +
            "WHERE user.global_id=?";

    public AddSaltSpec(byte[] salt, long id) {
        this.salt = salt;
        this.id = id;
    }

    @Override
    public PreparedStatement specify(Connection connection) throws SQLException, RepositoryException {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setBytes(1, salt);
        ps.setLong(2, id);
        return ps;

    }


}
