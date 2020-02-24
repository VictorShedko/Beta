package by.victor.beta.repository.impl;

import by.victor.beta.entity.Order;
import by.victor.beta.repository.Repository;
import by.victor.beta.service.CleanerEntityProvider;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;

public class OrderRepository extends Repository<Order> {
    private static final OrderRepository userRepositoryInstance=new OrderRepository();


    private OrderRepository() {

    }

    public static OrderRepository getInstance(){
        return userRepositoryInstance;
    }


    @Override
    protected Order buildEntity(ResultSet resultSet, CleanerEntityProvider factory) throws SQLException {
        return factory.getOrder(resultSet);
    }
}
