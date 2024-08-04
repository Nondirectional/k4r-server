package com.non.k4r.core.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data(staticConstructor = "create")
@Accessors(chain = true)
@Table("k4r.tb_memos")
public class Memos extends Model<Memos> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 备忘记录ID
     */
    @Id(comment = "备忘记录ID")
    private Long id;
    /**
     * 记录ID
     */
    @Column(comment = "记录ID")
    private Integer recordId;
    /**
     * 提醒时间
     */
    @Column(comment = "提醒时间")
    private Date reminderTime;

}
