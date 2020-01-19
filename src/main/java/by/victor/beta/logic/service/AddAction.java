package by.victor.beta.logic.service;

import by.victor.beta.logic.entity.Order;
import by.victor.beta.logic.entity.User;
import by.victor.beta.logic.reposytory.Repository;
import by.victor.beta.logic.reposytory.RepositoryException;
import by.victor.beta.logic.reposytory.specification.impl.add.AddOrderSpecification;
import by.victor.beta.logic.reposytory.specification.impl.add.AddUserSpecification;

class AddAction {
    public void addUser(User user){
        AddUserSpecification specification=new AddUserSpecification(user);
        try {
            Repository.instance.query(specification);
        } catch (RepositoryException e) {
            e.printStackTrace();//todo
        }
    }
    public void addOrder(Order order){
        AddOrderSpecification specification=new AddOrderSpecification();
        try {
            Repository.instance.query(specification);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    public void addNotify(){

    }

}
