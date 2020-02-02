package by.victor.beta.repository.specification.impl.notifyspecification;

import by.victor.beta.repository.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindNotifyByUsername implements Specification {
    private String SELECT_NOTIFY ="SELECT notify.message_id ,notify.text,notify.receiver,notify.time FROM notify " +
            "JOIN user   on notify.receiver = user.global_id " +
            "where user.username=?;";
    private String customerName;

    public FindNotifyByUsername(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public PreparedStatement specify(Connection connection) throws SQLException {
      PreparedStatement ps = connection.prepareStatement(SELECT_NOTIFY);
            ps.setString(1, customerName);

        return ps;
    }

}
