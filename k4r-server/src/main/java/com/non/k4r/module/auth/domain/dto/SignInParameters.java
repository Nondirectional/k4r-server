package com.non.k4r.module.auth.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SignInParameters {
    /**
     * 标识符（邮箱、账号、用户名）
     */
    @NotBlank
    private String identifier;

    /**
     * 密码
     */
    @NotBlank
    private String password;
}
