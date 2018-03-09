package me.test.dist.sql.jpa.d2.repository;


import me.test.dist.sql.jpa.d2.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

}
