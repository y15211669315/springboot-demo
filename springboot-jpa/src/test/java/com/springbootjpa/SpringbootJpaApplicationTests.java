package com.springbootjpa;

import com.springbootjpa.entity.User;
import com.springbootjpa.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJpaApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void contextLoads() {
        long time = System.currentTimeMillis();
        List<User> list = userRepository.findList();
        for (User user : list) {
            System.err.println(user.toString());
        }
        System.err.println("使用时间：" + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();
        Stream<User> stream = userRepository.findStream();
        Iterator<User> it = stream.iterator();
        while (it.hasNext()) {
            System.err.println(it.next().toString());
        }
        System.err.println("使用时间：" + (System.currentTimeMillis() - time));
    }

}
