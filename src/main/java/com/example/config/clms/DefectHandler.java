package com.example.config.clms;

public interface DefectHandler {

    // 支持的设备类型
    Integer getEquipmentType();

    // 1. 缺陷等级判定（各类型有自己的判定逻辑）
    String determineLevel(Defect defect);

    // 2. 生成工单（各类型工单格式不同）
    String createWorkOrder(Defect defect);

    // 3. 执行处理（各类型处理逻辑不同）
    void execute(Defect defect);

    // 4. 是否需要上报省调（各类型不同）
    boolean needReportToProvince(Defect defect);
}
