package com.non.k4r.core.controller;

import com.non.k4r.core.entity.Users;
import com.non.k4r.core.service.UsersService;
import com.non.k4r.framework.base.BaseCurdController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  控制层。
 *
 * @author root
 * @since 2024-08-02
 */
@RestController
@RequestMapping("/users")
public class UsersController extends BaseCurdController<UsersService, Users> {
    public UsersController(UsersService service) {
        super(service);
    }
}