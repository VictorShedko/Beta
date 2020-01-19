package by.victor.beta.logic.service;

import by.victor.beta.logic.entity.Role;
import by.victor.beta.logic.entity.User;
import by.victor.beta.logic.entity.UserStatus;
import by.victor.beta.logic.reposytory.SQLNameProvider;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Factory {

    public User getUser(String username, String password, String login, Role role){
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setLogin(login);
        user.setRole(role);
        user.setBalance(0);
        switch (role){
            case USER:
            case ADMIN:user.setStatus(UserStatus.VERIFIED);break;//todo так можно?
            case EXECUTOR:user.setStatus(UserStatus.NEW);break;

        }
        return user;
    }
    public User getUser(ResultSet resultSet) throws SQLException {
        User user=new User();
        user.setUsername(resultSet.getNString(SQLNameProvider.USERNAME));
        user.setPassword(resultSet.getNString(SQLNameProvider.PASSWORD));
        user.setLogin(resultSet.getNString(SQLNameProvider.LOGIN));
        user.setRole(Role.values()[resultSet.getInt(SQLNameProvider.ROLE)]);
        user.setBalance(resultSet.getInt(SQLNameProvider.BALANCE));
        user.setStatus(UserStatus.values()[resultSet.getInt(SQLNameProvider.USER_STATUS)]);
        return user;
    }
}
