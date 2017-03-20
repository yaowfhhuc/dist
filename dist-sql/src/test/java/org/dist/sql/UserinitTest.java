/*package org.dist.sql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import me.test.dist.sql.app.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class UserinitTest {

	@Autowired
	UserRepository userRepository;
	
	@Test
	public void inituser(){
		
		System.out.println(userRepository.findAll().toString());
	}
}
*/