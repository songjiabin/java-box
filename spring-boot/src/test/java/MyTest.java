import com.x.y.Application;
import com.x.y.bean.Dog;
import com.x.y.bean.Person;
import com.x.y.bean.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = Application.class)
public class MyTest {

    @Autowired
    private Dog dog;

    @Autowired
    private Person person;

    @Autowired
    private Student student;

    @Test
    void contextLoadsDog() {
        System.out.println(dog);
    }


    @Test
    void contextLoadPerson() {
        System.out.println(person);
    }

    @Test
    void contextLoadStudent() {
        System.out.println(student);
    }

}
