package com.non.k4r.module.auth.service;

import com.mybatisflex.core.mask.MaskManager;
import com.non.k4r.core.entity.Users;
import com.non.k4r.core.service.UsersService;
import com.non.k4r.framework.constant.CustomException;
import com.non.k4r.framework.constant.ErrorCodes;
import com.non.k4r.manager.AccessTokenManager;
import com.non.k4r.module.auth.domain.SignUpParameters;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

import static com.non.k4r.core.entity.table.UsersTableDef.USERS;

@Service
@Transactional
public class AuthenticationService {
    @Resource
    private AccessTokenManager accessTokenManager;

    public String login(String identifier, String password) {
        Optional<Users> userOptional = MaskManager.execWithoutMask(
                () -> Users
                        .create()
                        .select()
                        .where(USERS.USERNAME.eq(identifier).or(USERS.EMAIL.eq(identifier)))
                        .oneOpt());
        Users user = userOptional.orElseThrow(() -> new CustomException(ErrorCodes.NO_SUCH_ELEMENT, "用户不存在"));

        // 固定时长Equals方法，防止计时攻击
        if (constantTimeEquals(user.getPassword(), password)) {
            return accessTokenManager.generateAndStoreAccessToken(user.getId());
        } else {
            throw new CustomException(ErrorCodes.INCORRECT_PASSWORD, "密码错误");
        }
    }

    public String signUp(SignUpParameters dto) {
        Users existingUser = Users.create()
                .where(USERS.USERNAME.eq(dto.getUsername()).or(USERS.EMAIL.eq(dto.getEmail())))
                .one();
        if (existingUser != null) {
            throw new CustomException(ErrorCodes.ALREADY_EXISTS, "用户已存在");
        }

        Users saveUser = Users.create()
                .setUsername(dto.getUsername())
                .setPassword(dto.getPassword())
                .setEmail(dto.getEmail())
                .saveOpt()
                .orElseThrow(() -> new CustomException(ErrorCodes.UNKNOWN_ERROR, "用户注册失败"));
        return accessTokenManager.generateAndStoreAccessToken(saveUser.getId());
    }

    private boolean constantTimeEquals(String a, String b) {
        if (a == null || b == null) {
            return false;
        }

        if (a.length() != b.length()) {
            return false;
        }

        int result = 0;
        for (int i = 0; i < a.length(); i++) {
            result |= a.charAt(i) ^ b.charAt(i);
        }
        return result == 0;
    }
}
