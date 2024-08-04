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
@Table("k4r.tb_expenses")
public class Expenses extends Model<Expenses> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 消费记录ID
     */
    @Id(comment = "消费记录ID")
    private BigInteger id;
    /**
     * 记录ID
     */
    @Column(comment = "记录ID")
    private Integer recordId;
    /**
     * 商品名称
     */
    @Column(comment = "商品名称")
    private String itemName;
    /**
     * 金额
     */
    @Column(comment = "金额")
    private String amount;
    /**
     * 日期
     */
    @Column(comment = "日期")
    private Date date;
    /**
     * 备注
     */
    @Column(comment = "备注")
    private String notes;
}
