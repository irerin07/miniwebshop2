package com.tistory.irerin07.miniwebshop.repository;

import com.tistory.irerin07.miniwebshop.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void initTest() throws Exception {

    }
}

//    @Test
//    public void getUser() throws Exception{
//        List<User> users =userRepository.get("NAME_SEARCH", "민경수");
//
//        for(User user : users){
//            System.out.println(user.getId() + " , " + user.getName() + " , "  + user.getNickName() + " , " + user.getAddress() + " ,  " +  user.getEmail() + " , " + user.getContactNumber() + " , " + user.getRoles());
//        }
//    }
//}
