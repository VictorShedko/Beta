package by.victor.beta.repository.specification.impl.orderspecification;

import by.victor.beta.repository.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindNearestOrderSpecification implements Specification {
    private String FIND_NEAREST_ORDER="SELECT cleaning_order.start_time as time_before_action FROM cleaning_order" +
            " WHERE cleaning_order.status=0 or cleaning_order.status=1 " +
            "UNION " +
            "SELECT cleaning_order.end_time as time_before_action " +
            "FROM cleaning_order " +
            "WHERE cleaning_order.status=2 " +
            "ORDER BY time_before_action " +
            "LIMIT 1";

    public FindNearestOrderSpecification() {

    }

    @Override
    public PreparedStatement specify(Connection connection) throws SQLException {//return preparestatment
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_NEAREST_ORDER);

        return preparedStatement;
    }
}
