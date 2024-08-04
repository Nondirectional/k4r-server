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
@Table("k4r.tb_schedules")
public class Schedules extends Model<Schedules> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 日程记录ID
     */
    @Id(comment = "日程记录ID")
    private Long id;
    /**
     * 记录ID
     */
    @Column(comment = "记录ID")
    private Integer recordId;
    /**
     * 事件名称
     */
    @Column(comment = "事件名称")
    private String eventName;
    /**
     * 日期
     */
    @Column(comment = "日期")
    private Date date;
    /**
     * 时间
     */
    @Column(comment = "时间")
    private String time;
    /**
     * 地点
     */
    @Column(comment = "地点")
    private String location;
}
