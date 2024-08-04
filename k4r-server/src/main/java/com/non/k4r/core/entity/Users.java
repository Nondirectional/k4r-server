package com.non.k4r.core.entity;

import com.mybatisflex.annotation.*;
import com.mybatisflex.core.activerecord.Model;
import com.mybatisflex.core.keygen.KeyGenerators;
import com.mybatisflex.core.mask.Masks;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data(staticConstructor = "create")
@Table("k4r.tb_users")
@Accessors(chain = true)
public class Users extends Model<Users> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    @Id( comment = "用户ID",keyType = KeyType.Generator,value = KeyGenerators.flexId)
    private Long id;
    /**
     * 用户名
     */
    @Column(comment = "用户名")
    private String username;
    /**
     * 密码
     */
    @Column(comment = "密码")
    @ColumnMask(Masks.PASSWORD)
    private String password;
    /**
     * 电子邮件
     */
    @Column(comment = "电子邮件")
    private String email;
}
