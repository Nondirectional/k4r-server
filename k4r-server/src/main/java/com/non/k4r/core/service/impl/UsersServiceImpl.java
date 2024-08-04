package com.non.k4r.core.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.non.k4r.core.entity.Users;
import com.non.k4r.core.service.UsersService;
import com.non.k4r.core.mapper.UsersMapper;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author root
 * @since 2024-08-02
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

}
