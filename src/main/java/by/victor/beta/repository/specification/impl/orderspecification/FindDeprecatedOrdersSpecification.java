package by.victor.beta.repository.specification.impl.orderspecification;

import by.victor.beta.repository.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindDeprecatedOrdersSpecification implements Specification {
    private String sql="SELECT customer.username as customer_name,price,executor.username as executor_name,cleaning_order.start_time,cleaning_order.end_time," +
            "cleaning_order.address,cleaning_order.status,cleaning_order.idcleaning_order,cleaning_order.client_id,cleaning_order.executor_id " +
            "FROM cleaning_order " +
            "JOIN user as customer  on cleaning_order.client_id = customer.global_id " +
            "left join user as executor on cleaning_order.executor_id=executor.global_id " +
            "where (cleaning_order.start_time<NOW() AND (cleaning_order.status=0 OR cleaning_order.status=1))" +
            "OR(cleaning_order.end_time<NOW() AND (cleaning_order.status=2 OR cleaning_order.status=2))";



    public FindDeprecatedOrdersSpecification() {

    }

    @Override
    public PreparedStatement specify(Connection connection) throws SQLException {//return preparestatment
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        return preparedStatement;
    }


}
