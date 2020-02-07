package com.hqgml.small.pojo;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Devil
 * @date 2020/2/6 23:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mini_user")
public class MiniUser {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "`name`")
    private String name;

    @Column(name = "openid")
    private String openid;


    private Persons persons;



    public MiniUser(Integer id, String openid) {
        this.id=id;
        this.openid = openid;
    }
    public MiniUser(String openid) {
        this.openid = openid;
    }

    public MiniUser(Integer id,String name, String openid) {
        this.id=id;
        this.name = name;
        this.openid = openid;
    }
}