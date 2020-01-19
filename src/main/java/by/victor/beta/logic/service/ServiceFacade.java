package by.victor.beta.logic.service;

import by.victor.beta.logic.reposytory.dbconnection.ConnectionProvider;
import by.victor.beta.logic.entity.Order;
import by.victor.beta.logic.entity.Role;
import by.victor.beta.logic.entity.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public enum ServiceFacade {
   instance;

    public void destroy(){
        ConnectionProvider.instance.destroy();
    }

    public Optional<User> login(String login, String password){//todo костыль?
    FindAction findAction=new FindAction();
   List<User> users= findAction.findUserByLogin(login);//todo лучше еще одну спецификацию?
   if(users.size()==1&& users.get(0).getPassword().equals(password)){
       return Optional.of(users.get(0));
   }else{
       return Optional.empty();
   }
    }

    public boolean registerUser(String username,String password,String login,Role role){//todo level
        FindAction findAction=new FindAction();

        List<User> users= findAction.findUserByLogin(login);
        users.addAll(findAction.findUserByUsername(username));
        if(users.isEmpty()){
            AddAction addAction= new AddAction();
            Factory factory=new Factory();
            User user=factory.getUser(username,password,login,role);
            addAction.addUser(user);
            return true;
        }else{
            return false;
        }

    }
    public boolean deleteUser(String username){
            DeleteAction deleteAction=new DeleteAction();
            return true;
    }


    public List<User> showUserByStatus(){
        return null;
    }

    public boolean validateUser(){
        return true;
    }

    public boolean createOrder(String address, String description, String username, Date startTime,Date endTime,int price){
       ModifyAction modifyAction=new ModifyAction();



        AddAction addAction= new AddAction();
        Order order= new Order();
        order.setAddress(address);
        order.setDescription(description);
        order.setPrice(price);
        addAction.addOrder(order);
        return true;
    }

    public boolean acceptOrder(){

        return true;
    }

    public boolean cancelOrder(){
        return true;
    }

    public List<Order> showOrderHistory(String username){
        List<Order> orders;
        FindAction findAction=new FindAction();
        orders=findAction.findOrderByCustomer(username);
       return orders;
    }

    public boolean payOrder(int sum,String fromUser,String toUser){
        return true;
    }

    public boolean uploadPhoto(){

        return true;
    }
    public void init(){
        ConnectionProvider.instance.ordinal();//todo или добавить метод инит
    }
}
