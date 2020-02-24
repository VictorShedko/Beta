package by.victor.beta.repository.impl;

import by.victor.beta.entity.User;
import by.victor.beta.repository.Repository;
import by.victor.beta.service.CleanerEntityProvider;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;

public class UserRepository extends Repository<User> {
    private static final UserRepository userRepositoryInstance=new UserRepository();

    private UserRepository() {

    }

    public static UserRepository getInstance(){
        return userRepositoryInstance;
    }

    @Override
    protected User buildEntity(ResultSet resultSet, CleanerEntityProvider factory) throws SQLException, IOException {
        return factory.getUser(resultSet);
    }
}
