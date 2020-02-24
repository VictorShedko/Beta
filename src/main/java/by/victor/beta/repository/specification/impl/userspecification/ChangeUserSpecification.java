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
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setLong(2, user.getId());
        preparedStatement.setLong(3, user.getRole().ordinal());
        preparedStatement.setString(4, user.getUsername());

        Timestamp registrationTime = new Timestamp(user.getRegistrationTime().getTime());
        preparedStatement.setTimestamp(5, registrationTime);
        preparedStatement.setLong(6, user.getStatus().ordinal());
        preparedStatement.setLong(7, user.getBalance());
        preparedStatement.setString(8, user.getEmail());
        preparedStatement.setString(9,user.getPhotoPath());
        preparedStatement.setBytes(10,user.getPassword());
        preparedStatement.setLong(11,user.getId());
        return preparedStatement;

    }

}
