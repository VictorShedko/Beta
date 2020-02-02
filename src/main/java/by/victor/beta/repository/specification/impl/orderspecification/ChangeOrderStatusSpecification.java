package by.victor.beta.repository.specification.impl.orderspecification;

import by.victor.beta.repository.RepositoryException;
import by.victor.beta.repository.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChangeOrderStatusSpecification implements Specification {

    private int newStatus;
    private int id;
    private String sql = "UPDATE cleaning_order SET cleaning_order.status=? WHERE cleaning_order.idcleaning_order=?";

    public ChangeOrderStatusSpecification(int newStatus, int id) {
        this.newStatus = newStatus;
        this.id = id;
    }

    @Override
    public PreparedStatement specify(Connection connection) throws SQLException, RepositoryException {
       PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, newStatus);
            ps.setLong(2, id);
        return ps;

    }

}
