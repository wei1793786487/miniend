package com.hqgml.small.pojo;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Devil
 * @date 2020/2/7 21:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mini_user")
public class MiniUser {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "p_id")
    private Integer pId;

    @Column(name = "openid")
    private String openid;

    private Persons persons;

    public MiniUser(Integer id,Integer pId, String openid) {
        this.pId = pId;
        this.id = id;
        this.openid = openid;
    }
    public MiniUser(String openid) {

        this.openid = openid;
    }



}