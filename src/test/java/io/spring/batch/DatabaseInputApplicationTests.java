package io.spring.batch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


/*@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitConfig(classes = DatabaseInputApplication.class)*/

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DatabaseInputApplication.class)
@WebAppConfiguration

//@RunWith(SpringRunner.class)
//@SpringBootTest

public class DatabaseInputApplicationTests {

	@Test
	public void contextLoads() {
	}

}
