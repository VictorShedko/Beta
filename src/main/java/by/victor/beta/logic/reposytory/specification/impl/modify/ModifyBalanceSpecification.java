package by.victor.beta.logic.reposytory.specification.impl.modify;

import by.victor.beta.logic.reposytory.RepositoryException;
import by.victor.beta.logic.reposytory.specification.Specification;
import org.apache.xmlbeans.xml.stream.Space;

import java.sql.Connection;
import java.sql.SQLException;

public class ModifyBalanceSpecification implements Specification {
    private long newBalance;

    public long getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(long newBalance) {
        this.newBalance = newBalance;
    }

    @Override
    public void execute(Connection connection) throws SQLException, RepositoryException {

    }
}
