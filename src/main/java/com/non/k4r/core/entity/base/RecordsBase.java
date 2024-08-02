package com.non.k4r.core.entity.base;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@Data
public class RecordsBase implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录ID
     */
    @Id( comment = "记录ID")
    private BigInteger id;

    /**
     * 用户ID
     */
    @Column(comment = "用户ID")
    private Integer userId;

    /**
     * 记录类型ID
     */
    @Column(comment = "记录类型")
    private Integer recordType;

    /**
     * 标题
     */
    @Column(comment = "标题")
    private String title;

    /**
     * 内容
     */
    @Column(comment = "内容")
    private String content;

    /**
     * 创建日期
     */
    @Column(comment = "创建日期")
    private Date creationDate;


}
