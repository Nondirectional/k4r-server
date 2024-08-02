package com.non.k4r.core.entity.base;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import java.io.Serializable;
import java.math.BigInteger;

public class UsersBase implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @Id( comment = "用户ID")
    private BigInteger id;

    /**
     * 用户名
     */
    @Column(comment = "用户名")
    private String username;

    /**
     * 密码
     */
    @Column(comment = "密码")
    private String password;

    /**
     * 电子邮件
     */
    @Column(comment = "电子邮件")
    private String email;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
