package by.victor.beta.repository.specification.impl.orderspecification;



import by.victor.beta.repository.specification.Specification;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class FindOrderByCustomerSpecification implements Specification {
    private String sql="SELECT customer.username as customer_name,price,executor.username as executor_name,cleaning_order.start_time,cleaning_order.end_time," +
            "cleaning_order.address,cleaning_order.status,cleaning_order.idcleaning_order,cleaning_order.client_id,cleaning_order.executor_id " +
            "FROM cleaning_order " +
            "JOIN user as customer  on cleaning_order.client_id = customer.global_id " +
            "left join user as executor on cleaning_order.executor_id=executor.global_id " +
            "where customer.username=?;";
    private String customerName;

    public FindOrderByCustomerSpecification(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public PreparedStatement specify(Connection connection) throws SQLException {//return preparestatment
       PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customerName);
        return preparedStatement;
    }


}
