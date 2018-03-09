package me.test.dist.sql.jpa.d1.pojo;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USER_INFO")
public class UserInfo implements Serializable{

    @GeneratedValue
    @Id
    private int id ;

    @Column(name = "USER_NAME",nullable = false)
    private String userName;

    @Column(name = "NACK_NAME",nullable = false)
    private String nackName;

    @Column(name="AGE")
    private int age;

    public UserInfo(){

    }

    public UserInfo(String userName,String nackName,int age){
        this.nackName=nackName;
        this.userName = userName;
        this.age=age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNackName() {
        return nackName;
    }

    public void setNackName(String nackName) {
        this.nackName = nackName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
