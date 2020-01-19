package by.victor.beta.logic.reposytory.specification.impl.add;

import by.victor.beta.logic.entity.Order;
import by.victor.beta.logic.reposytory.specification.Specification;

import java.sql.*;

public class AddOrderSpecification implements Specification {

    private static final  String sql = "INSERT INTO cleaning_order (adress,start_time,passwor,role,balance,status) VALUES (?,?,?,?,?,?)";
    private Order order;

    @Override
    public void execute(Connection connection) throws SQLException {

        try ( PreparedStatement ps=connection.prepareStatement(sql)){
        ps.setString(1,order.getAddress());
        Date sqlStartDate=new Date(order.getStartTime().getTime());
        Date sqlEndDate=new Date(order.getEndTime().getTime());
        ps.setDate(2,sqlStartDate);
        ps.setDate(3,sqlEndDate);
        ps.setInt(4,order.getPrice());
        ps.setString(5,order.getDescription());
        ResultSet resultSet=ps.executeQuery();
        }
    }
}
