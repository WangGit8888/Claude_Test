package com.example.common.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
public class BaseClass {

    @ExcelIgnore
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ExcelIgnore
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Long createBy;

    @ExcelIgnore
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ExcelIgnore
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @ExcelIgnore
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ExcelIgnore
    @TableField(value = "dele_flag")
    @TableLogic
    private String delFlag;
}
