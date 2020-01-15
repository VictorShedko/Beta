package by.victor.beta.logic.reposytory.specification.impl;

import by.victor.beta.logic.entity.User;
import by.victor.beta.logic.reposytory.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FindUserByLogin implements Specification {
    private static final  String sql = "SELECT all_users.mainid FROM all_users WHERE login = ?";//todo rename
    private String login;
    private List<User> users;

    public FindUserByLogin(String login) {
        this.login = login;
    }

    @Override
    public void execute(Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, login);
        ResultSet resultSet=ps.executeQuery();
        users=new ArrayList<>();
        while(resultSet.next()){

        }
        ps.close();
    }

    public List<User> getResult(){
        return users;
    }

}
