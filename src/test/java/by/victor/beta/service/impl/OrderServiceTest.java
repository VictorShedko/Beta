package by.victor.beta.service.impl;


import by.victor.beta.entity.*;
import by.victor.beta.entity.OrderStatus;
import by.victor.beta.repository.Repository;
import by.victor.beta.repository.RepositoryException;
import by.victor.beta.repository.impl.OrderRepository;
import by.victor.beta.repository.specification.Specification;
import by.victor.beta.repository.specification.impl.orderspecification.FindDeprecatedOrdersSpecification;
import by.victor.beta.repository.specification.impl.orderspecification.FindOrderByExecutorSpecification;
import by.victor.beta.service.CleanerEntityProvider;
import by.victor.beta.service.INotifyService;
import by.victor.beta.service.IUserService;
import by.victor.beta.service.ServiceFacade;
import by.victor.beta.service.util.NotifyMessageBuilder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.powermock.api.support.membermodification.MemberModifier;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class TestReposytory extends Repository<Order>{
    List<Order> result;

    public List<Order> getResult() {
        return result;
    }

    public void setResult(List<Order> result) {
        this.result = result;
    }

    @Override
    protected Order buildEntity(ResultSet resultSet, CleanerEntityProvider factory) throws SQLException, IOException {
        return null;
    }
    @Override
    public List<Order> findQuery(Specification specification){
        return result;
    }

}


@Test(groups = {"static", "mock"})

public class OrderServiceTest {
    private OrderService service;

    private INotifyService notifyService;

    private IUserService userService;
    private NotifyMessageBuilder messageBuilder;


    Order newOrder = new Order();

    Order acceptedOrder = new Order();

    Order inProgressOrder = new Order();


    @BeforeTest
    void setup() {

        newOrder.setStatus(OrderStatus.NEW);
        acceptedOrder.setStatus(OrderStatus.ACCEPTED);
        inProgressOrder.setStatus(OrderStatus.IN_PROGRESS);


        service = new OrderService(messageBuilder, userService, notifyService);
        User testUser = new User();
        testUser.setUsername("testUser");


    }

    @DataProvider(name = "orders")
    public Object[][] orders() {


        return new Object[][]{
                {List.of(newOrder), List.of(OrderStatus.NOT_CLAIMED)},
                {List.of(inProgressOrder), List.of(OrderStatus.COMPLETED)}
        };
    }

    @Test(dataProvider = "orders")
    public void testUpdateOrderStatus(List<Order> orders, List<OrderStatus> newStatus) {
        MemberModifier.replace(MemberModifier.method(ServiceFacade.class, "getInsatnce")).with(MemberModifier.method(TestReposytory.class, "login"));
        //  PowerMockito.mockStatic(OrderRepository.class);


        service.timeUpdate();
        for (int i = 0; i < orders.size(); i++) {

        }


    }


}