package com.non.k4r.core.entity.base;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class DietaryRecordsBase implements Serializable {

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

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

}
