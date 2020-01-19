package by.victor.beta.logic.reposytory.specification.impl.find;

import by.victor.beta.logic.entity.Order;
import by.victor.beta.logic.entity.User;
import by.victor.beta.logic.reposytory.specification.Specification;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FindOrderByExecutorSpecification implements Specification {
    private List<Order> orders;

    @Override
    public void execute(Connection connection) throws SQLException {

    }

    public List<Order> getResult(){
        return orders;
    }
}
