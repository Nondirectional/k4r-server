package com.non.k4r.core.entity.base;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class ExpensesBase implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消费记录ID
     */
    @Id( comment = "消费记录ID")
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
