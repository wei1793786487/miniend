package com.hqgml.small.service;

import com.hqgml.small.pojo.Persons;

/**
 * @author Devil
 * @date 2020/2/6 20:47
 */
public interface PersonsService {
  Persons selectByid(Integer id);

  Persons selectOneByName(String name);
}


