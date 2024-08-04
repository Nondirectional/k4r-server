package com.non.k4r.core.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data(staticConstructor = "create")
@Accessors(chain = true)
@Table("k4r.tb_dietary_records")
public class DietaryRecords extends Model<DietaryRecords> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 饮食记录ID
     */
    @Id( comment = "饮食记录ID")
    private BigInteger id;
    /**
     * 记录ID
     */
    @Column(comment = "记录ID")
    private Integer recordId;
    /**
     * 餐点名称
     */
    @Column(comment = "餐点名称")
    private String mealName;
    /**
     * 时间
     */
    @Column(comment = "时间")
    private Date time;
    /**
     * 卡路里
     */
    @Column(comment = "卡路里")
    private Integer calories;

}
