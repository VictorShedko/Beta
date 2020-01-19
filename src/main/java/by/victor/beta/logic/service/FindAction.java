package by.victor.beta.logic.service;

import by.victor.beta.logic.entity.Order;
import by.victor.beta.logic.entity.User;
import by.victor.beta.logic.reposytory.Repository;
import by.victor.beta.logic.reposytory.RepositoryException;
import by.victor.beta.logic.reposytory.specification.impl.find.FindOrderByCustomerSpecification;
import by.victor.beta.logic.reposytory.specification.impl.find.FindOrderByExecutorSpecification;
import by.victor.beta.logic.reposytory.specification.impl.find.FindUserByLoginSpecification;

import java.util.List;

class FindAction {

    public List<User> findUserByLogin(String login){
        FindUserByLoginSpecification specification=new FindUserByLoginSpecification(login);
        try {
            Repository.instance.query(specification);
            return specification.getResult();
        } catch ( RepositoryException e) {
            e.printStackTrace();
            return List.of();
        }

    }
    public List<User> findUserByUsername(String username){
        FindUserByLoginSpecification specification= new FindUserByLoginSpecification(username);
        try {
            Repository.instance.query(specification);
            return specification.getResult();
        } catch ( RepositoryException e) {
            e.printStackTrace();
            return List.of();
        }

    }

    public List<Order> findOrderByCustomer(String customerName){
        FindOrderByCustomerSpecification specification= new FindOrderByCustomerSpecification();
        try {
            Repository.instance.query(specification);
            return specification.getResult();
        } catch ( RepositoryException e) {
            e.printStackTrace();
            return List.of();
        }
    }
    public List<Order> findOrderByExecutor(){
        FindOrderByExecutorSpecification specification=new FindOrderByExecutorSpecification();
        try {
            Repository.instance.query(specification);
            return specification.getResult();
        } catch ( RepositoryException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}

