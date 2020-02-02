package by.victor.beta.repository.specification.impl.userspecification;

import by.victor.beta.repository.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChangeBalanceSpecification implements Specification {
    private long newBalance;
    private String username;
    private String sql = "UPDATE user SET user.balance=? WHERE user.username=?";

    public ChangeBalanceSpecification(long newBalance, String username) {
        this.newBalance = newBalance;
        this.username = username;
    }


    @Override
    public PreparedStatement specify(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, newBalance);
            statement.setString(2, username);
        return statement;
    }
}
