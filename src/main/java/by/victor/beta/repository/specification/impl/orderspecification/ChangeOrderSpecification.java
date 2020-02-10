package by.victor.beta.repository.specification.impl.orderspecification;

import by.victor.beta.entity.Order;
import by.victor.beta.repository.RepositoryException;
import by.victor.beta.repository.specification.Specification;

import java.sql.*;

public class ChangeOrderSpecification implements Specification {

    private Order order;

    private String sql = "UPDATE cleaning_order SET cleaning_order.idcleaning_order=?," +
            "cleaning_order.address=?,cleaning_order.client_id=?,cleaning_order.start_time=?," +
            "cleaning_order.end_time=?,cleaning_order.executor_id=?,cleaning_order.price=?," +
            "cleaning_order.status=?,cleaning_order.description=? " +
            "WHERE cleaning_order.idcleaning_order=?";

    public ChangeOrderSpecification(Order order) {
      this.order=order;
    }

    @Override
    public PreparedStatement specify(Connection connection) throws SQLException, RepositoryException {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1,order.getOrderId());
            statement.setString(2,order.getAddress());
            statement.setLong(3, order.getCustomerId());
            Timestamp sqlStartDate = new Timestamp(order.getStartTime().getTime());
            Timestamp sqlEndDate = new Timestamp(order.getEndTime().getTime());
            statement.setTimestamp(4, sqlStartDate);
            statement.setTimestamp(5, sqlEndDate);
            statement.setLong(6, order.getExecutorId());
            statement.setLong(7, order.getPrice());
            statement.setLong(8, order.getStatus().ordinal());
            statement.setString(9, order.getDescription());
            statement.setLong(10,order.getOrderId());
        return statement;
    }

}
