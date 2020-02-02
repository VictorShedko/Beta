package by.victor.beta.repository.impl;

import by.victor.beta.entity.Order;
import by.victor.beta.repository.ConnectionProvider;
import by.victor.beta.repository.Repository;
import by.victor.beta.repository.RepositoryException;
import by.victor.beta.repository.specification.Specification;
import by.victor.beta.service.CleanerFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class OrderRepository extends Repository<Order> {
    private static AtomicBoolean created=new AtomicBoolean(false);
    private static OrderRepository userRepositoryInstance=null;
    private static final Logger logger = LogManager.getLogger(OrderRepository.class);


    private OrderRepository() {

    }

    public static OrderRepository getInstance(){
        if (created.compareAndSet(false,true)){
            userRepositoryInstance=new OrderRepository();
        }
        return userRepositoryInstance;
    }


    @Override
    protected Order buildEntity(ResultSet resultSet, CleanerFactory factory) throws SQLException {
        return factory.getOrder(resultSet);
    }
}
