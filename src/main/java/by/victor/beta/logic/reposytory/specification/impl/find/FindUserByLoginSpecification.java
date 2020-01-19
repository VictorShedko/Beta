package by.victor.beta.logic.reposytory.specification.impl.find;

import by.victor.beta.logic.entity.User;
import by.victor.beta.logic.reposytory.specification.Specification;
import by.victor.beta.logic.service.Factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FindUserByLoginSpecification implements Specification {
    private static final  String sql = "SELECT user.login,user.username,user.password,user.role,user.balance,user.status,user.registration_time FROM user WHERE user.login=?;";//todo rename
    private String login;
    private List<User> users;

    public FindUserByLoginSpecification(String login) {
        this.login = login;
    }

    @Override
    public void execute(Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, login);
        try( ResultSet resultSet=ps.executeQuery();) {


            users = new ArrayList<>();
            Factory factory = new Factory();
            while (resultSet.next()) {
                User user = factory.getUser(resultSet);
                users.add(user);
            }
            ps.close();
        }
    }

    public List<User> getResult(){
        return users;
    }

}
