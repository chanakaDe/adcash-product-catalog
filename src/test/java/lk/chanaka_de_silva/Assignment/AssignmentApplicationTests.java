package lk.chanaka_de_silva.Assignment;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssignmentApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AssignmentApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("++++++++++ SpringBoot Test - JUnit Testing ++++++++++");
    }
}
