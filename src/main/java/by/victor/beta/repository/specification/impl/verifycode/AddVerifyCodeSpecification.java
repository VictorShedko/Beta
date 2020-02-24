package by.victor.beta.repository.specification.impl.verifycode;

import by.victor.beta.entity.VerifyCode;
import by.victor.beta.repository.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddVerifyCodeSpecification implements Specification {
    private static final String INSERT_INTO_TOKEN = "INSERT INTO verify_code (code, user_id, time) VALUES (?,?,NOW())";

    private VerifyCode verifyCode;

    public AddVerifyCodeSpecification(VerifyCode verifyCode) {
        this.verifyCode = verifyCode;
    }

    @Override
    public PreparedStatement specify(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_TOKEN);
        preparedStatement.setString(1, verifyCode.getUuidAsString());
        preparedStatement.setLong(2, verifyCode.getUserId() );

        return preparedStatement;

    }


}
