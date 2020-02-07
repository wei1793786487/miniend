package com.hqgml.small.pojo;

import javax.persistence.*;
import lombok.Data;

/**
 * @author Devil
 * @date 2020/2/6 20:49
 */
@Data
@Table(name = "persons")
public class Persons {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "add_id")
    private Integer addId;

    @Column(name = "url")
    private String url;

    @Column(name = "person_name")
    private String personName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "add_time")
    private String addTime;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "w_uid")
    private Integer wUid;
}