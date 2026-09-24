import com.demo.spring.security.StringSecurityApplication;
import com.demo.spring.security.dao.User;
import com.demo.spring.security.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


@SpringBootTest(classes = StringSecurityApplication.class)
public class TestDemo {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder; // 推荐使用，它在每次加密时都会生成不同的盐值，且计算成本可调

    @Test
    public void testDatabases() {
        List<User> users = userMapper.queryUserList();
        System.out.println(users);
    }


    @Test
    public void testBCryptPasswordEncoder() {
        //encode 加密操作\matches 判断原密码 密文是否匹配
        String encode1 = passwordEncoder.encode("123456");
        System.out.println(passwordEncoder.matches("123456", encode1));
        String encode2 = passwordEncoder.encode("123456");
        //且每次加密结果不一样,内部有加盐的操作
        System.out.println(encode1);
        System.out.println(encode2);
    }
}
