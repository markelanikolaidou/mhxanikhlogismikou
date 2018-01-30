package com.softeng.athleticsticket.service;

import com.softeng.athleticsticket.model.MyUserDetails;
import com.softeng.athleticsticket.model.User;
import com.softeng.athleticsticket.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    public UserServiceTest() {
        // Default constructor
    }

    @Test
    public void saveAndFindUserTest() throws Exception {
        //Users can be created here safely
        User user = new User();
        user.setActive(true);
        user.setPassword("password");
        user.setEmail("test@user.com");
        user.setName("Mr Test");
        user.setLastName("LastName");
        user = userService.saveUser(user);
        assertNotEquals(0,user.getId());
        MyUserDetails userDetails = (MyUserDetails) userService.loadUserByUsername(user.getEmail());
        assertEquals(user.getId(),userDetails.getId());
        assertEquals(user.getPassword(),userDetails.getPassword());
        assertEquals(user.getEmail(),userDetails.getEmail());
        assertEquals(user.getName(),userDetails.getUsername());
        assertEquals(user.getLastName(),userDetails.getLastName());
        userRepository.delete(user);
    }

}