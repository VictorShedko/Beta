package by.victor.beta.repository.specification.impl.orderspecification;

import by.victor.beta.repository.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindOrderByStatusSpecification implements Specification {
    private String sql="SELECT customer.username as customer_name,price,executor.username as executor_name," +
            "cleaning_order.start_time,cleaning_order.end_time," +
            "cleaning_order.address,cleaning_order.status,cleaning_order.idcleaning_order,cleaning_order.client_id," +
            "cleaning_order.executor_id,cleaning_order.description " +
            "FROM cleaning_order " +
            "JOIN user as customer  on cleaning_order.client_id = customer.global_id " +
            "left join user as executor on cleaning_order.executor_id=executor.global_id " +
            "where cleaning_order.status=?;";

    private int status;

    public FindOrderByStatusSpecification(int status) {
        this.status = status;
    }

    @Override
    public PreparedStatement specify(Connection connection) throws SQLException {
     PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, status);
            return preparedStatement;

    }


}
