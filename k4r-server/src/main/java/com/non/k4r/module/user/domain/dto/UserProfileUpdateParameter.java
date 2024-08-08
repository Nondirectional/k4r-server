package com.non.k4r.module.user.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

@Data
@Accessors(chain = true)
public class UserProfileUpdateParameter {
    private MultipartFile avatar;

    private String nickname;

    private String email;
}
