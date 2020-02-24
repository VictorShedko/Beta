package by.victor.beta.repository.specification.impl.verifycode;

import by.victor.beta.repository.RepositoryException;
import by.victor.beta.repository.specification.Specification;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class ClearCodeSpecification implements Specification {
    private static final String clearCode="call delete_deprecated_code()";

    @Override
    public CallableStatement specify(Connection connection) throws SQLException, RepositoryException {
        CallableStatement callableStatement = connection.prepareCall(clearCode);
        return callableStatement;
    }
}
