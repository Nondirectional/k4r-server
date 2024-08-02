package com.non.k4r.core.entity.base;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class SchedulesBase implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日程记录ID
     */
    @Id( comment = "日程记录ID")
    private BigInteger id;

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

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
