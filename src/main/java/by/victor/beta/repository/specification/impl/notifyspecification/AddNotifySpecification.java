package by.victor.beta.repository.specification.impl.notifyspecification;

import by.victor.beta.entity.Notify;
import by.victor.beta.repository.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddNotifySpecification implements Specification {
    private static final String INSERT_INTO_NOTIFY = "INSERT INTO notify (text,receiver,time) VALUES (?,?,NOW())";

    private Notify notify;

    public AddNotifySpecification(Notify notify) {
        this.notify = notify;
    }

    @Override
    public PreparedStatement specify(Connection connection) throws SQLException {
       PreparedStatement ps = connection.prepareStatement(INSERT_INTO_NOTIFY);
            ps.setString(1, notify.getValuesAsString());
            ps.setLong(2,notify.getUserId());
      return ps;

    }


}
