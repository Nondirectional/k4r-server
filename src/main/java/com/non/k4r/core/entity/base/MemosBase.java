package com.non.k4r.core.entity.base;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class MemosBase implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 备忘记录ID
     */
    @Id( comment = "备忘记录ID")
    private BigInteger id;

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

    public Date getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(Date reminderTime) {
        this.reminderTime = reminderTime;
    }

}
