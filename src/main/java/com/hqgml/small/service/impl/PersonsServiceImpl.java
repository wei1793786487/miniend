package com.hqgml.small.service.impl;

import com.hqgml.small.exception.ExceptionEnums;
import com.hqgml.small.exception.XxException;
import com.hqgml.small.pojo.Persons;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.hqgml.small.mapper.PersonsMapper;
import com.hqgml.small.service.PersonsService;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * @author Devil
 * @date 2020/2/6 20:47
 */
@Service
public class PersonsServiceImpl implements PersonsService {

    @Resource
    private PersonsMapper personsMapper;

    @Override
    public Persons selectByid(Integer id) {

      return personsMapper.selectOneById(id);

    }

    @Override
    public Persons selectOneByName(String name) {
        Persons persons = personsMapper.selectOneByPersonName(name);
        if (persons==null){
            throw new XxException(ExceptionEnums.PSERSON_NOt_FIND);
        }
        return persons;
    }
}


