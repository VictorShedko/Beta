package by.victor.beta.repository.specification.impl.orderspecification;

import by.victor.beta.entity.Order;
import by.victor.beta.repository.RepositoryException;
import by.victor.beta.repository.specification.Specification;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            Date sqlStartDate = new Date(order.getStartTime().getTime());
            Date sqlEndDate = new Date(order.getEndTime().getTime());
            statement.setDate(4, sqlStartDate);
            statement.setDate(5, sqlEndDate);
            statement.setLong(6, order.getExecutorId());
            statement.setLong(7, order.getPrice());
            statement.setLong(8, order.getStatus().ordinal());
            statement.setString(9, order.getDescription());
            statement.setInt(10,order.getOrderId());
        return statement;
    }

}
