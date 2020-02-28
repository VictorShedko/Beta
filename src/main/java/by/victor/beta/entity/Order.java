package by.victor.beta.entity;

import java.util.Date;
import java.util.Objects;

public class Order implements Entity{
    private long orderId;
    private String customer;
    private String executor;
    private long customerId;
    private long executorId;
    private String address;
    private String description;
    private Date startTime;
    private Date endTime;
    private int price;
    private OrderStatus status;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getExecutorId() {
        return executorId;
    }

    public void setExecutorId(long executorId) {
        this.executorId = executorId;
    }

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
        this.status=status;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (customerId != order.customerId) return false;
        if (executorId != order.executorId) return false;
        if (price != order.price) return false;
        if (customer != null ? !customer.equals(order.customer) : order.customer != null) return false;
        if (executor != null ? !executor.equals(order.executor) : order.executor != null) return false;
        if (address != null ? !address.equals(order.address) : order.address != null) return false;
        if (description != null ? !description.equals(order.description) : order.description != null) return false;
        if (startTime != null ? !startTime.equals(order.startTime) : order.startTime != null) return false;
        if (endTime != null ? !endTime.equals(order.endTime) : order.endTime != null) return false;
        return status == order.status;
    }

    @Override
    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (executor != null ? executor.hashCode() : 0);
        result = 31 * result + (int) (customerId ^ (customerId >>> 32));
        result = 31 * result + (int) (executorId ^ (executorId >>> 32));
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Order");
        builder.append("orderId=");
        builder.append(orderId);
        builder.append(", customer=");
        builder.append(customer);
        builder.append(", executor=");
        builder.append(executor);
        builder.append(", customerId=");
        builder.append(customerId);
        builder.append(", executorId=");
        builder.append(executorId);
        builder.append("  , address=");
        builder.append(address);
        builder.append("  , startTime=");
        builder.append(startTime);
        builder.append(", endTime=");
        builder.append(endTime);
        builder.append("  , price=");
        builder.append(price);
        builder.append("  , status=");
        builder.append(status);
        return builder.toString();
    }
}
