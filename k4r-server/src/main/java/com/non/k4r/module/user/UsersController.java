package com.non.k4r.module.user;

import com.mybatisflex.core.mask.MaskManager;
import com.non.k4r.context.RequestContext;
import com.non.k4r.core.entity.Users;
import com.non.k4r.framework.commons.Result;
import com.non.k4r.framework.constant.CustomException;
import com.non.k4r.framework.constant.ErrorCodes;
import com.non.k4r.module.user.domain.dto.UserProfileUpdateParameter;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * 用户
 */
@RestController
public class UsersController {

    @PutMapping("/users/user/profile")
    public Result<Void> updateUserProfile(UserProfileUpdateParameter dto) {
        Users user = Users
                .create()
                .setId(RequestContext.getRequestUserId())
                .oneOpt()
                .orElseThrow(() -> new CustomException(ErrorCodes.UNKNOWN_ERROR, "User not found"));

        Optional.ofNullable(dto.getAvatar())
                .ifPresent(avatar -> {
                    File file = new File(STR."C:/data/files/avatar/\{user.getId()}.jpg");
                    if (!file.getParentFile().exists()) {
                        if (file.mkdirs()) {
                            throw new CustomException(ErrorCodes.UNKNOWN_ERROR, "Failed to create avatar directory");
                        }
                    }
                    try {
                        avatar.transferTo(file);
                    } catch (IOException e) {
                        throw new CustomException(ErrorCodes.UNKNOWN_ERROR, "Failed to save avatar");
                    }
                });
        user
                .setNickname(dto.getNickname())
                .setEmail(dto.getEmail())
                .updateById();
        return Result.success();
    }
}
