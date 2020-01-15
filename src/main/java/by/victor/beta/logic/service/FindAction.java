package by.victor.beta.logic.service;

import by.victor.beta.logic.dbconection.NoFreeConnectionException;
import by.victor.beta.logic.entity.User;
import by.victor.beta.logic.reposytory.Repository;
import by.victor.beta.logic.reposytory.specification.impl.FindUserByLogin;

import java.sql.SQLException;
import java.util.List;

public class FindAction {

    public List<User> findUser(String login){
        FindUserByLogin specification=new FindUserByLogin(login);

        try {
            Repository.instance.Query(specification);
            return specification.getResult();
        } catch (NoFreeConnectionException | SQLException e) {
            e.printStackTrace();
            return List.of();
        }

    }
}

