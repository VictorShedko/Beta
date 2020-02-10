package by.victor.beta.repository.impl;

import by.victor.beta.entity.User;
import by.victor.beta.repository.Repository;
import by.victor.beta.service.CleanerEntutyProvider;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;

public class UserRepository extends Repository<User> {
    private static AtomicBoolean created=new AtomicBoolean(false);
    private static UserRepository userRepositoryInstance=null;
    private static final Logger logger = LogManager.getLogger(UserRepository.class);


    private UserRepository() {

    }

    public static UserRepository getInstance(){
        if (created.compareAndSet(false,true)){
            userRepositoryInstance=new UserRepository();
        }
        return userRepositoryInstance;
    }


    @Override
    protected User buildEntity(ResultSet resultSet, CleanerEntutyProvider factory) throws SQLException {
        return factory.getUser(resultSet);
    }
}
