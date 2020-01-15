package by.victor.beta.logic.reposytory.specification.impl;

import by.victor.beta.logic.entity.User;
import by.victor.beta.logic.reposytory.specification.Specification;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AddUser implements Specification {
    private static final  String sql = "SELECT FROM allusers WHERE (login) VALUES(?)";
    private User user;

    @Override
    public void execute(Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql);

        ResultSet resultSet=ps.executeQuery();

        ps.close();
    }
}
