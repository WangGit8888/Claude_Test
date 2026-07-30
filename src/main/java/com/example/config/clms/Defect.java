package com.example.config.clms;

import lombok.Data;

import java.util.Date;

@Data
public class Defect {
    private Integer equipmentType;      // 设备类型
    private String defectDesc;          // 缺陷描述
    private String defectLevel;         // 缺陷等级: URGENT/HIGH/MEDIUM/LOW
    private String location;            // 设备位置（如"大坝左岸第3坝段"）
    private Date reportTime;            // 上报时间
    private Long reporterId;            // 上报人
    private String attachment;          // 附件（照片/视频）
    // 其他字段...
}