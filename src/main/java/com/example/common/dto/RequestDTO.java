package com.example.common.dto;

import lombok.Data;

@Data
public class RequestDTO {
    /**
     * 当前页 （默认第一页）
     */
    private Integer pageNum = 1;
    /**
     * 每页条数（默认每页显示8条数据）
     */
    private Integer pageSize = 10;
    /**
     * 查询条件
     */
    private Condition condition;
}
