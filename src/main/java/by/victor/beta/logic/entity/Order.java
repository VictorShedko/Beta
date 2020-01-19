package by.victor.beta.logic.entity;

import java.util.Date;
import java.util.Objects;

public class Order {
    private String customer;
    private String executor;//todo можно ли хранить в виде имен (иначе при создании сначало надо найти пользователя)
    private String address;
    private String description;
    private Date startTime;
    private Date endTime;
    private int price;//todo int в топку
    private OrderStatus status;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return price == order.price &&
                Objects.equals(customer, order.customer) &&
                Objects.equals(executor, order.executor) &&
                Objects.equals(address, order.address) &&
                Objects.equals(description, order.description) &&
                Objects.equals(startTime, order.startTime) &&
                Objects.equals(endTime, order.endTime) &&
                status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, executor, address, description, startTime, endTime, price, status);
    }
}
