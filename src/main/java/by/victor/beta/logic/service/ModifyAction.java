package by.victor.beta.logic.service;

import by.victor.beta.logic.entity.Order;
import by.victor.beta.logic.entity.User;
import by.victor.beta.logic.entity.UserStatus;
import by.victor.beta.logic.reposytory.Repository;
import by.victor.beta.logic.reposytory.RepositoryException;
import by.victor.beta.logic.reposytory.specification.impl.find.FindUserByNameSpecification;
import by.victor.beta.logic.reposytory.specification.impl.modify.ModifyBalanceSpecification;

import java.util.List;

class ModifyAction {
    boolean setUserStatus(UserStatus status, User user){
return true;
    }

    boolean setOrderStatus(Order order) {
return true;
    }

    boolean creditUser(User user,int sum){
        long startSum=user.getBalance();
        ModifyBalanceSpecification modifySpecification= new ModifyBalanceSpecification();
        modifySpecification.setNewBalance(startSum+sum);
        return true;//todo убрать
    }

    boolean debitUser(User user,int sum){
        long startSum=user.getBalance();
        if (user.getBalance()>sum) {
            ModifyBalanceSpecification modifySpecification = new ModifyBalanceSpecification();
            modifySpecification.setNewBalance(startSum + sum);
            return true;
        }else {
            return false;
        }
    }


}
