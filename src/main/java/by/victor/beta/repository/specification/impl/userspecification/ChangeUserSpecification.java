package by.victor.beta.repository.specification.impl.userspecification;

import by.victor.beta.entity.User;
import by.victor.beta.repository.RepositoryException;
import by.victor.beta.repository.specification.Specification;

import java.sql.*;

public class ChangeUserSpecification implements Specification {

    private User user;

    private String sql = "UPDATE user SET user.login=?," +
            "user.global_id=?,user.password=?,user.role=?,user.username=?,user.registration_time=?," +
            "user.status=?,user.balance=?,user.email=?,user.photo=? " +
            "WHERE user.global_id=?";

    public ChangeUserSpecification(User user) {
        this.user = user;
    }

    @Override
    public PreparedStatement specify(Connection connection) throws SQLException, RepositoryException {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, user.getLogin());
        ps.setLong(2, user.getId());
        ps.setString(3, user.getPassword());
        ps.setLong(4, user.getRole().ordinal());
        ps.setString(5, user.getUsername());

        Timestamp registrationTime = new Timestamp(user.getRegistrationTime().getTime());
        ps.setTimestamp(6, registrationTime);
        ps.setLong(7, user.getStatus().ordinal());
        ps.setLong(8, user.getBalance());
        ps.setString(9, user.getEmail());
        ps.setString(10,user.getPhotoPath());
        ps.setLong(11,user.getId());
        return ps;

    }

}
