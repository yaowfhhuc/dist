package me.test.dist.sql.jpa.d1.repository;


import me.test.dist.sql.jpa.d1.pojo.UserInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
@CacheConfig(cacheNames = "userInfoCache")
public interface UserInfoRepository extends JpaRepository<UserInfo,Integer>{


    @Cacheable(key = "#p0")
    @Query("from UserInfo where userName=:name")
    UserInfo findByUserName(String name);

    @Cacheable(key="#p0")
    @Query("from UserInfo where userName=:name")
    UserInfo findUser(@Param("name")String name);

    @CachePut(key = "#p0.name")
    UserInfo save(UserInfo userInfo);
}
