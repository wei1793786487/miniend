package com.hqgml.small.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.hqgml.small.mapper.PersonsMapper;
import com.hqgml.small.service.PersonsService;

/**
 * @author Devil
 * @date 2020/2/6 20:47
 */
@Service
public class PersonsServiceImpl implements PersonsService {

    @Resource
    private PersonsMapper personsMapper;

}

