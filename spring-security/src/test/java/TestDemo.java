import com.demo.spring.security.StringSecurityApplication;
import com.demo.spring.security.dao.User;
import com.demo.spring.security.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest(classes = StringSecurityApplication.class)
public class TestDemo {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void test() {
        List<User> users = userMapper.queryUserList();
        System.out.println(users);

    }
}
