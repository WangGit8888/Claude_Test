package com.example.config.clms;

import lombok.Data;

// 设备类型常量
@Data
public class EquipmentType {
    public static final int DAM = 1;           // 大坝
    public static final int GENERATOR = 2;     // 发电机组
    public static final int GATE = 3;          // 闸门
    public static final int TRANSMISSION = 4;  // 输电线路
    public static final int HYDROLOGY = 5;     // 水情监测
}
