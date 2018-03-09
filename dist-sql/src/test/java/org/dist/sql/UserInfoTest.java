package org.dist.sql;

import me.test.dist.sql.jpa.d1.pojo.UserInfo;
import me.test.dist.sql.jpa.d1.repository.UserInfoRepository;
import me.test.dist.sql.jpa.d2.pojo.User;
import me.test.dist.sql.jpa.d2.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import me.test.dist.sql.Application;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest( classes = Application.class)
//@Transactional(transactionManager = "transactionManagerd1")
public class UserInfoTest {

	@Autowired
	UserInfoRepository userInfoRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired @Qualifier("d2JdbcTemplate")
	JdbcTemplate d2JdbcTemplate;

	@Test
	//@Transactional
	public void save(){
        UserInfo userInfo = new UserInfo("1111","11111",11);
		userInfoRepository.saveAndFlush(userInfo);
		Assert.assertEquals("1111", userInfoRepository.findAll().get(0).getUserName());


	/*	User user = new User("2222222222","2222",22);
		userRepository.save(user);
		Assert.assertEquals("2222222222", userRepository.findAll().get(0).getUserName());*/
	}

	@Test
	public void JdbcTemplateTest(){
		jdbcTemplate.execute("delete from user_info");
		d2JdbcTemplate.execute("delete from user");
	}


	@Test
	public void cacheTest(){
		Assert.assertEquals("1111",userInfoRepository.findUser("1111").getUserName());
		Assert.assertEquals("1111",userInfoRepository.findUser("1111").getUserName());
	}
}
