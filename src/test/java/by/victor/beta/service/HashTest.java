package by.victor.beta.service;

import by.victor.beta.entity.Role;
import by.victor.beta.repository.RepositoryException;
import by.victor.beta.repository.impl.UserRepository;
import by.victor.beta.repository.specification.impl.AddSaltSpec;
import by.victor.beta.repository.specification.impl.userspecification.FindUserByRoleSpecification;
import by.victor.beta.service.util.HashGenerator;

import java.util.ArrayList;
import java.util.List;
import by.victor.beta.entity.User;
public class HashTest {
    @org.testng.annotations.Test
    public void lol() throws RepositoryException, ServiceException {
        List<User> users=new ArrayList<>();
        for(Role role:Role.values()) {
            FindUserByRoleSpecification specification = new FindUserByRoleSpecification(role);
            users.addAll(UserRepository.getInstance().findQuery(specification));
        }

        for (User user:users) {


            byte[] salt= HashGenerator.instance.getHash(user.getOldPassword(),user.getSalt());
            AddSaltSpec saltSpec=new AddSaltSpec(salt,user.getId());
            try {
                UserRepository.getInstance().updateQuery(saltSpec);
            } catch (RepositoryException e) {
                e.printStackTrace();
            }
        }
        //    String result1=generator.getHash("lol");
          //  String result2=generator.getHash("lol");
           // Assert.assertEquals(result1, result1);



    }
}