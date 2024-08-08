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

@EqualsAndHashCode(callSuper = true)
@Data(staticConstructor = "create")
@Accessors(chain = true)
@Table("k4r.tb_files")
public class Files extends Model<Files> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 文件ID
     */
    @Id(comment = "文件ID")
    private Long id;
    /**
     * 文件名
     */
    @Column(comment = "文件名")
    private String filename;
    /**
     * 扩展名
     */
    @Column(comment = "扩展名")
    private String extension;
    /**
     * 保存路径
     */
    @Column(comment = "保存路径")
    private String savePath;

}