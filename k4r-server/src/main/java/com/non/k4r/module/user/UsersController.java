package com.non.k4r.module.user;

import com.non.k4r.context.RequestContext;
import com.non.k4r.core.entity.Users;
import com.non.k4r.framework.commons.Result;
import com.non.k4r.framework.constant.ErrorCodes;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * 用户
 */
@RestController
public class UsersController {
    /**
     * 更新用户昵称
     *
     * @param nickname 新的昵称
     * @return 结果对象，成功或失败
     *
     * 通过PUT请求更新用户昵称。首先检查昵称是否为空或长度为零，
     * 如果是，则返回参数错误的失败结果。然后，使用从请求上下文中获取的用户ID，
     * 创建一个新的用户实例并设置新的昵称，最后更新用户信息。
     */
    @PutMapping("/user/users/nickname")
    public Result<Void> updateNickname(String nickname) {
        // 检查昵称是否为空或长度为零
        if (nickname == null || nickname.isEmpty()) {
            // 如果昵称为空或长度为零，则返回参数错误的失败结果
            return Result.fail(ErrorCodes.PARAMETER_ERROR.getErrorCode(), "Nickname cannot be empty");
        }
        // 使用从请求上下文中获取的用户ID更新用户昵称
        Optional.ofNullable(RequestContext.getRequestUserId())
                .ifPresent(id -> {
                    // 创建一个新的用户实例并设置新的昵称
                    Users.create()
                            .setId(id)
                            .setNickname(nickname)
                            .updateById();
                });
        // 更新成功，返回成功结果
        return Result.success();
    }
}
