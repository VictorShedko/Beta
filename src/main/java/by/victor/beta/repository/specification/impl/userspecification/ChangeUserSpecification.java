package by.victor.beta.repository.specification.impl.userspecification;

import by.victor.beta.entity.User;
import by.victor.beta.repository.RepositoryException;
import by.victor.beta.repository.specification.Specification;

import java.sql.*;

public class ChangeUserSpecification implements Specification {

    private User user;

    private String sql = "UPDATE user SET user.login=?," +
            "user.global_id=?,user.role=?,user.username=?,user.registration_time=?," +
            "user.status=?,user.balance=?,user.email=?,user.photo=?,user.hash_pass=? " +
            "WHERE user.global_id=?";

    public ChangeUserSpecification(User user) {
        this.user = user;
    }

    @Override
    public PreparedStatement specify(Connection connection) throws SQLException, RepositoryException {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, user.getLogin());
        ps.setLong(2, user.getId());
        ps.setLong(3, user.getRole().ordinal());
        ps.setString(4, user.getUsername());

        Timestamp registrationTime = new Timestamp(user.getRegistrationTime().getTime());
        ps.setTimestamp(5, registrationTime);
        ps.setLong(6, user.getStatus().ordinal());
        ps.setLong(7, user.getBalance());
        ps.setString(8, user.getEmail());
        ps.setString(9,user.getPhotoPath());
        ps.setBytes(10,user.getPassword());
        ps.setLong(11,user.getId());
        return ps;

    }

}
