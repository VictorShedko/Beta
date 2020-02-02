package by.victor.beta.repository.specification.impl.orderspecification;

import by.victor.beta.entity.Order;
import by.victor.beta.repository.specification.Specification;

import java.sql.*;

public class AddOrderSpecification implements Specification {
    private static final String INSERT_CLEANING_ORDER = "INSERT INTO cleaning_order (address,start_time,end_time,price,client_id,status,description,executor_id) VALUES (?,?,?,?,?,?,?,NULL)";
    private Order order;


    public AddOrderSpecification(Order order) {
        this.order = order;

    }

    @Override
    public PreparedStatement specify(Connection connection) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(INSERT_CLEANING_ORDER);
        statement.setString(1, order.getAddress());
        Timestamp sqlStartDate = new Timestamp(order.getStartTime().getTime());
        Timestamp sqlEndDate = new Timestamp(order.getEndTime().getTime());
        statement.setTimestamp(2, sqlStartDate);
        statement.setTimestamp(3, sqlEndDate);
        statement.setInt(4, order.getPrice());
        statement.setLong(5, order.getCustomerId());
        statement.setInt(6, order.getStatus().ordinal());
        statement.setString(7, order.getDescription());

        return statement;

    }


}
