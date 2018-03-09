package org.dist.sql;


import me.test.dist.sql.Application;
import me.test.dist.sql.jpa.d1.pojo.UserInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisTest {

    /**
     * 泛型注入 要用 @Resource
     */
    @Resource
    RedisTemplate<String,UserInfo> redisTemplate;

    @Test
    public void test() {
        UserInfo userInfo = new UserInfo("1111","11111",11);
        redisTemplate.opsForValue().set(userInfo.getUserName()+"",userInfo);
        Assert.assertEquals("1111",redisTemplate.opsForValue().get("1111").getUserName());
    }

}
