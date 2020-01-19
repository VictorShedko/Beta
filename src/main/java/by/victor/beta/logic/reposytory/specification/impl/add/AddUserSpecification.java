package by.victor.beta.logic.reposytory.specification.impl.add;

import by.victor.beta.logic.entity.User;
import by.victor.beta.logic.reposytory.Repository;
import by.victor.beta.logic.reposytory.RepositoryException;
import by.victor.beta.logic.reposytory.specification.Specification;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AddUserSpecification implements Specification {
    private static final  String sql = "INSERT INTO user (login,username,passwor,role,balance,status,registration_time) VALUES (?,?,?,?,?,?,NOW())";
    private User user;

    public AddUserSpecification(User user) {
        this.user = user;
    }

    @Override
    public void execute(Connection connection) throws SQLException {
       try( PreparedStatement ps = connection.prepareStatement(sql)) {
           ps.setString(1, user.getLogin());
           ps.setString(2, user.getUsername());
           ps.setString(3, user.getPassword());
           ps.setInt(4, user.getRole().ordinal());
           ps.setLong(5, user.getBalance());
           ps.setInt(6, user.getStatus().ordinal());
           ResultSet resultSet = ps.executeQuery();


       }
    }
}
