package com.non.k4r;

import com.alibaba.fastjson.JSON;
import com.non.k4r.core.entity.Users;
import com.non.k4r.core.service.UsersService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.non.k4r.core.entity.table.UsersTableDef.USERS;

@SpringBootTest
class K4rApplicationTests {
    @Resource
    private UsersService usersService;

    @Test
    public void test() {
        Users
                .create()
                .setUsername("root")
                .setPassword("root")
                .save();

        Users one = Users.create()
                .where(USERS.USERNAME.eq("root"))
                .one();
        System.err.println(JSON.toJSONString(one));
    }
}
