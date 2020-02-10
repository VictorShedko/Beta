package by.victor.beta.repository.specification.impl.notifyspecification;

import by.victor.beta.entity.Notification;
import by.victor.beta.repository.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddNotifySpecification implements Specification {
    private static final String INSERT_INTO_NOTIFY = "INSERT INTO notify (text,receiver,time,type) VALUES (?,?,NOW(),?)";

    private Notification notification;

    public AddNotifySpecification(Notification notification) {
        this.notification = notification;
    }

    @Override
    public PreparedStatement specify(Connection connection) throws SQLException {
       PreparedStatement ps = connection.prepareStatement(INSERT_INTO_NOTIFY);
            ps.setString(1, notification.getValuesAsString());
            ps.setLong(2, notification.getUserId());
            ps.setInt(3, notification.getType().ordinal());
      return ps;

    }


}
