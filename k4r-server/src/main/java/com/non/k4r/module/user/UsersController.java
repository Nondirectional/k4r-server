package com.non.k4r.module.user;

import com.non.k4r.core.entity.Users;
import com.non.k4r.framework.commons.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.non.k4r.core.entity.table.UsersTableDef.USERS;

/**
 * 用户
 */
@RestController
public class UsersController {

    /**
     * 检查用户名是否存在
     *
     * 此端点用于验证指定的用户名是否在系统中已存在，无需认证
     * 它通过查询数据库中与给定用户名匹配的记录数来判断用户名是否存在
     *
     * @param username 待检查的用户名
     * @return 包含用户名存在状态的结果对象，true表示存在，false表示不存在
     */
    @GetMapping("/user/user/no-auth/username/exists")
    public Result<Boolean> usernameExists(String username) {
        // 根据提供的用户名查询数据库，并判断是否存在该用户名
        boolean exists = Users
                .create()
                .where(USERS.USERNAME.eq(username))
                .count() > 0;
        // 返回查询结果，表示用户名是否存在
        return Result.success(exists);
    }

    /**
     * 检查邮箱是否存在
     *
     * 此端点用于验证给定的邮箱地址是否已经在系统中存在
     * 它通过查询数据库中与提供的邮箱地址匹配的记录数量来实现
     * 如果存在至少一条匹配记录，则返回true；否则返回false
     *
     * @param email 需要验证的邮箱地址
     * @return 包含验证结果的Result对象，其中Boolean值表示邮箱是否存在
     */
    @GetMapping("/user/user/no-auth/email/exists")
    public Result<Boolean> emailExists(String email) {
        // 根据提供的邮箱地址查询数据库，检查邮箱是否已存在
        boolean exists = Users
                .create()
                .where(USERS.EMAIL.eq(email))
                .count() > 0;
        // 返回查询结果，true表示邮箱已存在，false表示邮箱不存在
        return Result.success(exists);
    }
}
